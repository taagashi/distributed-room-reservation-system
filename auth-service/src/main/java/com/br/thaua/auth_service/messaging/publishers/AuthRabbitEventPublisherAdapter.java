package com.br.thaua.auth_service.messaging.publishers;

import com.br.thaua.auth_service.core.messaging.publishers.AuthEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitEventPublisherAdapter implements AuthEventPublisherPort {
    @Value("${exchange.auth.name}")
    private String exchangeAuth;

    @Value("${routing.key.auth.deleted}")
    private String routingKeyDeletedAuth;

    @Value("${routing.key.auth.created}")
    private String routingKeyCreatedAuth;

    @Value("${routing.key.auth.updated}")
    private String routingKeyUpdatedAuth;

    private final RabbitTemplate rabbittemplate;

    @Override
    public void createdAuth(Object payload) {
        rabbittemplate.convertAndSend(exchangeAuth, routingKeyCreatedAuth, payload);
    }

    @Override
    public void deletedAuth(Object payload) {
        rabbittemplate.convertAndSend(exchangeAuth, routingKeyDeletedAuth, payload);
    }

    @Override
    public void updatedAuth(Object payload) {
        rabbittemplate.convertAndSend(exchangeAuth, routingKeyUpdatedAuth, payload);
    }
}
