package com.br.thaua.employee_service.messaging.config;

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
    @Value("${routing.key.employee.created}")
    private String routingKeyEmployeeCreated;

    @Value("${routing.key.employee.update}")
    private String routingKeyEmployeeUpdate;

    @Value("${routing.key.employee.deleted}")
    private String routingKeyEmployeeDeleted;

    @Value("${exchange.employee.name}")
    private String exchangeEmployeeName;

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
    public DirectExchange directExchange() {
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
        return BindingBuilder.bind(createdEmployeeQueue()).to(directExchange()).with(routingKeyEmployeeCreated);
    }

    @Bean
    public Binding updateEmployeeBinding() {
        return BindingBuilder.bind(updateEmployeeQueue()).to(directExchange()).with(routingKeyEmployeeUpdate);
    }

    @Bean
    public Binding deletedEmployeeBinding() {
        return BindingBuilder.bind(deleteEmployeeQueue()).to(directExchange()).with(routingKeyEmployeeDeleted);
    }
}
