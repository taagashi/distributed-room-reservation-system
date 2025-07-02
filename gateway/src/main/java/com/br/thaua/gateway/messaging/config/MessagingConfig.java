package com.br.thaua.gateway.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Value("${exchange.auth.name}")
    private String exchangeAuth;

    @Value("${routing.key.auth.deleted}")
    private String routingKeyDeletedAuth;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListener(ConnectionFactory connectionFactory) {
            SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
            containerFactory.setConnectionFactory(connectionFactory);
            containerFactory.setMessageConverter(messageConverter());
            return containerFactory;
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
    public Binding deletedAuthBinding() {
        return BindingBuilder.bind(deletedAuthQueue()).to(directExchange()).with(routingKeyDeletedAuth);
    }
}
