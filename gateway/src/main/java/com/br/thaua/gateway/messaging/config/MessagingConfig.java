package com.br.thaua.gateway.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Value("${exchange.auth.name}")
    private String exchangeAuth;

    @Value("${routing.key.auth}")
    private String routingKetAuth;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListener(ConnectionFactory connectionFactory) {
            SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
            containerFactory.setConnectionFactory(connectionFactory);
            containerFactory.setMessageConverter(messageConverter());
            return containerFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public Queue authQueue() {
        return new Queue("gateway.auth.queue");
    }

    @Bean
    public Binding deletedAuthBinding() {
        return BindingBuilder.bind(authQueue()).to(directExchange()).with(routingKetAuth);
    }
}
