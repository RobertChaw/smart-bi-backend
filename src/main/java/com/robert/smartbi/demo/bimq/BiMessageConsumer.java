package com.robert.smartbi.demo.bimq;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.esotericsoftware.minlog.Log;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.rabbitmq.client.Channel;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.manager.COSManager;
import com.robert.smartbi.demo.manager.RedisLimiterManager;
import com.robert.smartbi.demo.model.entity.Chart;
import com.robert.smartbi.demo.service.ChartService;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Header;


import java.lang.reflect.Type;
import java.util.*;

@Component
@Slf4j
public class BiMessageConsumer {

    @Resource
    private ChartService chartService;
    @Resource
    private OpenAiService openAiService;

    @Resource
    private COSManager cosManager;

    @Resource
    private RedisLimiterManager redisLimiterManager;

    @SneakyThrows
    @RabbitListener(queues = {BiMqConstant.QUEUE_NAME}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        Log.info("接收到图表任务 id: " + message);
        Chart chart = chartService.getById(message);
        ThrowUtils.throwIf(chart == null, ErrorCode.OPERATION_ERROR);
        chart.setStatus("running");
        String userId = Long.toString(chart.getUserId());
        redisLimiterManager.doRateLimit(userId);

        // 从数据库读取相关消息，用于生成 ChatGPT 对话
        String goal = chart.getGoal();
        String data = chart.getData();
        if (goal == null) {
            handleUpdateChartError(chart.getId(), "目标不能为空");
            channel.basicNack(deliveryTag, false, false);
            return;
        }

        if (data == null) {
            handleUpdateChartError(chart.getId(), "分析数据不能为空");
            channel.basicNack(deliveryTag, false, false);
            return;
        }


        // OpenAi 生成代码
        String openAiResponse = doRequest(goal, data);
        // 转换结果
        JSONObject obj = JSON.parseObject(openAiResponse);
        String summary = obj.getString("summary");
        String chartOption = obj.getString("chartOption");

        // 写入数据库
        Chart newChart = new Chart();
        newChart.setId(chart.getId());
        newChart.setSummary(summary);
        newChart.setChartOption(chartOption);
        newChart.setReason("");
        newChart.setStatus("succeeded");
        boolean isUpdated = chartService.updateById(newChart);

        if (!isUpdated) {
            handleUpdateChartError(chart.getId(), "插入数据到数据库失败");
            channel.basicNack(deliveryTag, false, false);
            return;
        }
        Log.info("执行成功图表任务 id: " + message);
        channel.basicAck(deliveryTag, false);
    }

    public String doRequest(String goal, String data) {
        // 请求 ChatGPT 生成代码
        String prmoptionString = "你是一个资深数据分析师，精通数据分析、Echarts 配置代码编写。\n" + "\n" + "- 请根据一下提供的数据、及分析目标生成对应的 Echarts 配置代码及摘要。\n" + "\n" + "- 数据将会用以下格式呈现：\n" + "<<<数据：<<<\n" + "{数据在这里}\n" + "<<<<<<\n" + "\n" + "- 分析目标将会用以下格式呈现：\n" + "<<<分析目标：<<<\n" + "{分析目标}\n" + "<<<<<<\n" + "\n" + "- 你输出的代码必须始终保持以下 JSON 格式\n" + "{\n" + "  \"chartOption\":{Echarts 图表代码},\n" + "  \"summary\":{摘要文本}\n" + "}";
        ChatMessage promotion = new ChatMessage(ChatMessageRole.SYSTEM.value(), prmoptionString);
        String userMessageString = "<<<数据：<<<\n" + data + "\n" + "<<<<<<\n" + "<<<分析目标：<<<\n" + goal + "\n" + "<<<<<<\n";
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessageString);
        List<ChatMessage> messages = Arrays.asList(promotion, userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo-1106").messages(messages).build();
        ChatCompletionResult chatCompletionResult = openAiService.createChatCompletion(chatCompletionRequest);
        ChatCompletionChoice choice = chatCompletionResult.getChoices().getFirst();
        String result = choice.getMessage().getContent();
        log.info("openai response: " + result);
        return result;
    }

    public void handleUpdateChartError(Long chartId, String reason) {
        Chart newChart = new Chart();
        newChart.setId(chartId);
        newChart.setStatus("failed");
        newChart.setReason("执行失败: " + reason);

        chartService.updateById(newChart);
    }
}
