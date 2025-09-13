package com.br.thaua.gateway.core.messaging.consumers;

public interface AuthEventConsumerPort {
    void fetchAuthForMessage(String event);
}
