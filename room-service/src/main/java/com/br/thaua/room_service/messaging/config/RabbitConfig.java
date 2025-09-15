package com.br.thaua.room_service.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${exchange.room.name}")
    private String exchangeRoomName;

    @Value("${exchange.reservation.name}")
    private String exchangeReservationName;

    @Value("${routing.key.room}")
    private String routingKeyRoom;

    @Value("${routing.key.reservation}")
    public String routingKeyReservation;

    @Bean
    public DirectExchange roomExchange() {
        return new DirectExchange(exchangeRoomName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverterJson());
        rabbitTemplate.setRoutingKey(routingKeyRoom);
        rabbitTemplate.setExchange(exchangeRoomName);
        return rabbitTemplate;
    }

    public MessageConverter messageConverterJson() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(messageConverterJson());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public Queue reservationQueue() {
        return new Queue("room.reservation.queue");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeReservationName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(reservationQueue()).to(directExchange()).with(routingKeyReservation);
    }
}
