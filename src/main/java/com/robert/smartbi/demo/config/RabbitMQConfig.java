package com.robert.smartbi.demo.config;


import com.robert.smartbi.demo.bimq.BiMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue() {
        return new Queue(BiMqConstant.QUEUE_NAME, true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(BiMqConstant.EXCHANGE_NAME, true, false);
    }

    @Bean
    public Declarables declarables() {
        return new Declarables(new Binding(BiMqConstant.QUEUE_NAME, Binding.DestinationType.QUEUE, BiMqConstant.EXCHANGE_NAME, BiMqConstant.ROUTING_KEY, null));
    }
}
