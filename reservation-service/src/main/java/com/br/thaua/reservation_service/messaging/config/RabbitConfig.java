package com.br.thaua.reservation_service.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${exchange.auth.name}")
    private String exchangeAuth;

    @Value("${exchange.room.name}")
    private String exchangeRoom;

    @Value("${exchange.reservation.name}")
    private String exchangeReservation;

    @Value("${routing.key.reservation}")
    private String routingKeyReservation;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setExchange(exchangeReservation);
        rabbitTemplate.setRoutingKey(routingKeyReservation);
        rabbitTemplate.setMessageConverter(messageConverterJson());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(messageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public MessageConverter messageConverterJson() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicAuthExchange() {
        return new TopicExchange(exchangeAuth);
    }

    @Bean
    public Queue authQueue() {
        return new Queue("reservation.auth.queue");
    }

    @Bean
    public Binding authBinding() {
        return BindingBuilder.bind(authQueue()).to(topicAuthExchange()).with("auth.*");
    }

    @Bean
    public TopicExchange topicRoomExchange() {
        return new TopicExchange(exchangeRoom);
    }

    @Bean
    public Queue roomQueue() {
        return new Queue("reservation.room.queue");
    }

    @Bean
    public Binding createdRoomBinding() {
        return BindingBuilder.bind(roomQueue()).to(topicRoomExchange()).with("room.*");
    }

}
