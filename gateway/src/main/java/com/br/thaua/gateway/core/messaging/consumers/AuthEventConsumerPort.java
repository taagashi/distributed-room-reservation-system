package com.br.thaua.gateway.core.messaging.consumers;

import com.br.thaua.gateway.domain.Auth;
import com.br.thaua.gateway.messaging.dto.AuthEvent;

public interface AuthEventConsumerPort {
    void fetchAuthForMessage(AuthEvent payload);
}
