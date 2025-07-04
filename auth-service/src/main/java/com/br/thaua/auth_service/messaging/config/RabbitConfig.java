package com.br.thaua.auth_service.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${exchange.auth.name}")
    private String exchangeAuth;

    @Value("${routing.key.auth.deleted}")
    private String routingKeyDeletedAuth;

    @Value("${routing.key.auth.created}")
    private String routingKeyCreatedAuth;

    @Value("${routing.key.auth.updated}")
    private String routingKeyUpdatedAuth;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange directExchange() {
        return new TopicExchange(exchangeAuth);
    }

}
