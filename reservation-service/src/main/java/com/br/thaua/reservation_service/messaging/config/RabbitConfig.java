package com.br.thaua.reservation_service.messaging.config;

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
public class RabbitConfig {
    @Value("${routing.key.employee.created}")
    private String routingKeyEmployeeCreated;

    @Value("${routing.key.employee.update}")
    private String routingKeyEmployeeUpdate;

    @Value("${routing.key.employee.deleted}")
    private String routingKeyEmployeeDeleted;

    @Value("${exchange.employee.name}")
    private String exchangeEmployeeName;

    @Value("${routing.key.room.created}")
    private String routingKeyRoomCreated;

    @Value("${routing.key.room.update}")
    private String routingKeyRoomUpdate;

    @Value("${routing.key.room.deleted}")
    private String routingKeyRoomDeleted;

    @Value("${exchange.room.name}")
    private String exchangeRoomName;

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(messageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public DirectExchange directEmployeeExchange() {
        return new DirectExchange(exchangeEmployeeName);
    }

    @Bean
    public Queue createdEmployeeQueue() {
        return new Queue("employee.created.queue");
    }

    @Bean
    public Queue updateEmployeeQueue() {
        return new Queue("employee.update.queue");
    }

    @Bean
    public Queue deleteEmployeeQueue() {
        return new Queue("employee.deleted.queue");
    }

    @Bean
    public Binding createdEmployeeBinding() {
        return BindingBuilder.bind(createdEmployeeQueue()).to(directEmployeeExchange()).with(routingKeyEmployeeCreated);
    }

    @Bean
    public Binding updateEmployeeBinding() {
        return BindingBuilder.bind(updateEmployeeQueue()).to(directEmployeeExchange()).with(routingKeyEmployeeUpdate);
    }

    @Bean
    public Binding deletedEmployeeBinding() {
        return BindingBuilder.bind(deleteEmployeeQueue()).to(directEmployeeExchange()).with(routingKeyEmployeeDeleted);
    }


    @Bean
    public DirectExchange directRoomExchange() {
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
        return BindingBuilder.bind(createdRoomQueue()).to(directRoomExchange()).with(routingKeyRoomCreated);
    }

    @Bean
    public Binding updateRoomBinding() {
        return BindingBuilder.bind(updateRoomQueue()).to(directRoomExchange()).with(routingKeyRoomUpdate);
    }

    @Bean
    public Binding deletedRoomBinding() {
        return BindingBuilder.bind(deletedRoomQueue()).to(directRoomExchange()).with(routingKeyRoomDeleted);
    }
}
