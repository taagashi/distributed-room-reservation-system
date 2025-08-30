package com.br.thaua.employee_service.messaging.adapters.consumers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPayloadConverter {
    private final ObjectMapper objectMapper;

    public JsonNode fetchJsonNode(String content) {
        try {
            return objectMapper.readTree(content);
        }catch (Exception e) {
            return null;
        }
    }

    public <T> T fetchEventClass(Class <T> eventClazz, JsonNode node) {
        try {
            objectMapper.treeToValue(node, eventClazz);
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
