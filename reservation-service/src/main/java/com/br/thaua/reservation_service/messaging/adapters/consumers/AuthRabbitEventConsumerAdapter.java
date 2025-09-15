package com.br.thaua.reservation_service.messaging.adapters.consumers;

import com.br.thaua.reservation_service.core.cache.AuthCachePort;
import com.br.thaua.reservation_service.core.messaging.consumers.AuthEventConsumerPort;
import com.br.thaua.reservation_service.core.repository.ParticipantRepositoryPort;
import com.br.thaua.reservation_service.domain.AuthEventType;
import com.br.thaua.reservation_service.messaging.dto.consumer.AuthEventConsumer;
import com.br.thaua.reservation_service.messaging.dto.consumer.AuthUpdatedEventConsumer;
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
            AuthEventType eventType = AuthEventType.valueOf(node.get("eventType").asText());

            switch (eventType) {
                case AUTH_CREATED:
                    AuthEventConsumer authCreated = objectMapper.treeToValue(node, AuthEventConsumer.class);
                    authCreatedEvent(authCreated);
                    break;

                case AUTH_UPDATED:
                    AuthUpdatedEventConsumer authUpdated = objectMapper.treeToValue(node, AuthUpdatedEventConsumer.class);
                    authUpdatedEvent(authUpdated);
                    break;

                case AUTH_DELETED:
                    AuthEventConsumer authDeleted = objectMapper.treeToValue(node, AuthEventConsumer.class);
                    authDeletedEvent(authDeleted);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void authCreatedEvent(AuthEventConsumer authCreated) {
        authCachePort.putCacheAuthEmailKey(authEventMapper.map(authCreated));
    }

    private void authUpdatedEvent(AuthUpdatedEventConsumer authUpdated) {
        authCachePort.evictAuthEmailKey(authUpdated.oldEmail());
        authCachePort.updateCacheAuthEmailKey(authEventMapper.map(authUpdated));

        participantRepositoryPort.updateEmailByAuthId(authUpdated.authId(), authUpdated.email());
    }

    private void authDeletedEvent(AuthEventConsumer authDeleted) {
        authCachePort.evictAuthEmailKey(authDeleted.email());

        participantRepositoryPort.deleteAllByAuthId(authDeleted.authId());
    }

    private JsonNode fetchJsonNode(String event) throws JsonProcessingException {
        return objectMapper.readTree(event);
    }
}
