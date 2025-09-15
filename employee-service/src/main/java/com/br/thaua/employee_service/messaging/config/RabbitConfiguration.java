package com.br.thaua.employee_service.messaging.config;

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
public class RabbitConfiguration {
    @Value("${exchange.auth.name}")
    private String exchangeAuth;

    @Value("${exchange.reservation.name}")
    private String exchangeReservation;

    @Value("${exchange.room.name}")
    private String exchangeRoom;

    @Value("${routing.key.employee}")
    private String routingKeyEmployee;

    @Value("${routing.key.auth}")
    private String routingKeyAuth;

    @Value("${routing.key.reservation}")
    private String routingKeyReservation;

    @Value("${routing.key.room}")
    private String routingKeyRoom;

    @Bean
    public DirectExchange authExchange() {
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public DirectExchange reservationExchange() {
        return new DirectExchange(exchangeReservation);
    }

    @Bean
    public DirectExchange roomExchange() {
        return new DirectExchange(exchangeRoom);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(simpleMessageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MessageConverter simpleMessageConverter() {
        return new SimpleMessageConverter();
    }


    @Bean
    public Queue authQueue() {
        return new Queue("employee.auth.queue");
    }

    @Bean
    public Queue reservationQueue() {
        return new Queue("employee.reservation.queue");
    }

    @Bean
    public Queue roomQueue() {
        return new Queue("employee.room.queue");
    }

    @Bean
    public Binding authBinding() {
        return BindingBuilder.bind(authQueue()).to(authExchange()).with(routingKeyAuth);
    }

    @Bean
    public Binding reservationBinding() {
        return BindingBuilder.bind(reservationQueue()).to(reservationExchange()).with(routingKeyReservation);
    }

    @Bean
    public Binding roomBinding() {
        return BindingBuilder.bind(roomQueue()).to(roomExchange()).with(routingKeyRoom);
    }

}
