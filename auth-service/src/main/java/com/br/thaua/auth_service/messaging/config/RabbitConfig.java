package com.br.thaua.auth_service.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public Queue deletedAuthQueue() {
        return new Queue("auth.deleted.queue");
    }

    @Bean
    public Queue createdAuthQueue() {
        return new Queue("auth.created.queue");
    }

    @Bean
    public Queue updatedAuthQueue() {
        return new Queue("auth.updated.queue");
    }

    @Bean
    public Binding deletedAuthBinding() {
        return BindingBuilder.bind(deletedAuthQueue()).to(directExchange()).with(routingKeyDeletedAuth);
    }

    @Bean
    public Binding createdAuthBinding() {
        return BindingBuilder.bind(createdAuthQueue()).to(directExchange()).with(routingKeyCreatedAuth);
    }

    @Bean
    public Binding updatedAuthBinding() {
        return BindingBuilder.bind(updatedAuthQueue()).to(directExchange()).with(routingKeyUpdatedAuth);
    }
}
