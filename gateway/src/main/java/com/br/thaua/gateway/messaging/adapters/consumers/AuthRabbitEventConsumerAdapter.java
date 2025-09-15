package com.br.thaua.gateway.messaging.adapters.consumers;

import com.br.thaua.gateway.core.cache.GatewayCachePort;
import com.br.thaua.gateway.core.messaging.consumers.AuthEventConsumerPort;
import com.br.thaua.gateway.domain.AuthEventType;
import com.br.thaua.gateway.messaging.dto.AuthEvent;
import com.br.thaua.gateway.messaging.dto.AuthUpdatedEvent;
import com.br.thaua.gateway.messaging.mapper.AuthEventMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitEventConsumerAdapter implements AuthEventConsumerPort {
    private final GatewayCachePort gatewayCachePort;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"gateway.auth.queue"})
    @Override
    public void fetchAuthForMessage(String event) {
        try
        {
            JsonNode node = fetchJsonNode(event);
            AuthEventType eventType = AuthEventType.valueOf(node.get("eventType").asText());

            switch (eventType) {
                case AUTH_DELETED:
                    AuthEvent authDeleted = objectMapper.treeToValue(node, AuthEvent.class);
                    authDeletedEvent(authDeleted);
                    break;

                case AUTH_UPDATED:
                    AuthUpdatedEvent authUpdated = objectMapper.treeToValue(node, AuthUpdatedEvent.class);
                    authUpdatedEvent(authUpdated);
                    break;
            }
        } catch (Exception e) {
            return;
        }

    }

    private void authDeletedEvent(AuthEvent authDeleted) {
        gatewayCachePort.putCacheGatewayIdKey(authDeleted.authId(), authDeleted);
    }

    private void authUpdatedEvent(AuthUpdatedEvent authUpdated) {
        gatewayCachePort.putCacheGatewayEmailKey(authUpdated.oldEmail(), authUpdated);
    }
    private JsonNode fetchJsonNode(String event) throws JsonProcessingException {
        return objectMapper.readTree(event);
    }
}
