package com.br.thaua.gateway.messaging.adapters.consumers;

import com.br.thaua.gateway.core.cache.GatewayCachePort;
import com.br.thaua.gateway.core.messaging.consumers.AuthEventConsumerPort;
import com.br.thaua.gateway.domain.Auth;
import com.br.thaua.gateway.messaging.dto.AuthEvent;
import com.br.thaua.gateway.messaging.mapper.AuthEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitEventConsumerAdapter implements AuthEventConsumerPort {
    private final GatewayCachePort gatewayCachePort;
    private final AuthEventMapper authEventMapper;

    @RabbitListener(queues = {"gateway.auth.deleted.queue"})
    @Override
    public void fetchAuthForMessage(AuthEvent authEvent) {
        gatewayCachePort.putCacheGateway("revoked:" + authEvent.id(), authEvent);
    }

}
