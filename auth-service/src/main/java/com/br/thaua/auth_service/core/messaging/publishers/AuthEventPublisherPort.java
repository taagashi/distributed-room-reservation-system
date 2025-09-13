package com.br.thaua.auth_service.core.messaging.publishers;

public interface AuthEventPublisherPort {
    void sendToAuthExchange(Object payLoad);
}
