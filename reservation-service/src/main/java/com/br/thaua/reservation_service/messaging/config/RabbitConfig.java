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

    @Value("${routing.key.room}")
    private String routingKeyRoom;

    @Value("${routing.key.auth}")
    private String routingKeyAuth;

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
    public DirectExchange directAuthExchange() {
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public Queue authQueue() {
        return new Queue("reservation.auth.queue");
    }

    @Bean
    public Binding authBinding() {
        return BindingBuilder.bind(authQueue()).to(directAuthExchange()).with(routingKeyAuth);
    }

    @Bean
    public DirectExchange directRoomExchange() {
        return new DirectExchange(exchangeRoom);
    }

    @Bean
    public Queue roomQueue() {
        return new Queue("reservation.room.queue");
    }

    @Bean
    public Binding createdRoomBinding() {
        return BindingBuilder.bind(roomQueue()).to(directRoomExchange()).with(routingKeyRoom);
    }

}
