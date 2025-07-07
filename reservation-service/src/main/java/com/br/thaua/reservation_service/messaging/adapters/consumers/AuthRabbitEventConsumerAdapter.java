package com.br.thaua.reservation_service.messaging.adapters.consumers;

import com.br.thaua.reservation_service.core.cache.AuthCachePort;
import com.br.thaua.reservation_service.core.messaging.consumers.AuthEventConsumerPort;
import com.br.thaua.reservation_service.core.repository.ParticipantRepositoryPort;
import com.br.thaua.reservation_service.messaging.dto.AuthEvent;
import com.br.thaua.reservation_service.messaging.dto.AuthUpdatedEvent;
import com.br.thaua.reservation_service.messaging.mappers.AuthEventMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitEventConsumerAdapter implements AuthEventConsumerPort {
    private final AuthCachePort authCachePort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final ObjectMapper objectMapper;
    private final AuthEventMapper authEventMapper;

    @RabbitListener(queues = {"reservation.auth.queue"})
    @Override
    public void consumerAuthEvent(String event) {
        try {
            JsonNode node = fetchJsonNode(event);
            String eventType = node.get("eventType").asText();

            switch (eventType) {
                case "auth.created":
                    AuthEvent authCreated = objectMapper.treeToValue(node, AuthEvent.class);
                    authCreatedEvent(authCreated);
                    break;

                case "auth.updated":
                    AuthUpdatedEvent authUpdated = objectMapper.treeToValue(node, AuthUpdatedEvent.class);
                    authUpdatedEvent(authUpdated);
                    break;

                case "auth.deleted":
                    AuthEvent authDeleted = objectMapper.treeToValue(node, AuthEvent.class);
                    authDeletedEvent(authDeleted);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void authCreatedEvent(AuthEvent authCreated) {
        authCachePort.putCacheAuthEmailKey(authEventMapper.map(authCreated));
    }

    private void authUpdatedEvent(AuthUpdatedEvent authUpdated) {
        authCachePort.evictAuthEmailKey(authUpdated.oldEmail());
        authCachePort.updateCacheAuthEmailKey(authEventMapper.map(authUpdated));

        participantRepositoryPort.updateEmailByAuthId(authUpdated.id(), authUpdated.email());
    }

    private void authDeletedEvent(AuthEvent authDeleted) {
        authCachePort.evictAuthEmailKey(authDeleted.email());

        participantRepositoryPort.deleteAllByAuthId(authDeleted.id());
    }

    private JsonNode fetchJsonNode(String event) throws JsonProcessingException {
        return objectMapper.readTree(event);
    }
}
