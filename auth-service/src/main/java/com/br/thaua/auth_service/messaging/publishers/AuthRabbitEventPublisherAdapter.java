package com.br.thaua.auth_service.messaging.publishers;

import com.br.thaua.auth_service.core.messaging.publishers.AuthEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitEventPublisherAdapter implements AuthEventPublisherPort {
    private final RabbitTemplate rabbittemplate;

    @Override
    public void sendToAuthExchange(Object payLoad) {
        rabbittemplate.convertAndSend(payLoad);
    }
}
