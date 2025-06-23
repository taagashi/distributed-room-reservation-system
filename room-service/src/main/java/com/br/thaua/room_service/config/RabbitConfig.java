package com.br.thaua.room_service.config;

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
    @Value("${routing.key.room.created}")
    private String routingKeyRoomCreated;

    @Value("${routing.key.room.update}")
    private String routingKeyRoomUpdate;

    @Value("${routing.key.room.deleted}")
    private String routingKeyRoomDeleted;

    @Value("${exchange.room.name}")
    private String exchangeRoomName;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeRoomName);
    }

    @Bean
    public Queue createdRoomQueue() {
        return new Queue("room.created.queue");
    }

    @Bean
    public Queue updateRoomQueue() {
        return new Queue("room.update.queue");
    }

    @Bean
    public Queue deletedRoomQueue() {
        return new Queue("room.deleted.queue");
    }

    @Bean
    public Binding createdRoomBinding() {
        return BindingBuilder.bind(createdRoomQueue()).to(directExchange()).with(routingKeyRoomCreated);
    }

    @Bean
    public Binding updateRoomBinding() {
        return BindingBuilder.bind(updateRoomQueue()).to(directExchange()).with(routingKeyRoomUpdate);
    }

    @Bean
    public Binding deletedRoomBinding() {
        return BindingBuilder.bind(deletedRoomQueue()).to(directExchange()).with(routingKeyRoomDeleted);
    }
}
