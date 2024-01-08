package com.robert.smartbi.demo.config;

import com.baomidou.mybatisplus.core.toolkit.Assert;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ConfigTest {
    @Resource
    private OpenAiService openAiService;

    @Test
    void testChatGPTConfig() {
        System.out.println("----- testChatGPTConfig method test ------");
        List<ChatMessage> messages = new ArrayList<ChatMessage>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "我是一个 MySQL 数据库专家," + "精通 TSQL 语句编写,我输出代码将严格遵从以下格式,不要有多余注释:\n" + "<<<<<<\n" + "{sql代码}\n" + "<<<<<<");
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), "帮我生成一个用户数据表，有用户id、角色及一些常用字段");
        messages.add(systemMessage);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo-1106").messages(messages).build();

        ChatCompletionChoice choice = openAiService.createChatCompletion(chatCompletionRequest).getChoices().getFirst();
        Assert.isTrue(choice != null, "成功");
        String content = choice.getMessage().getContent();

        System.out.println("来自ChatGPT的回复: " + content);
    }
}
