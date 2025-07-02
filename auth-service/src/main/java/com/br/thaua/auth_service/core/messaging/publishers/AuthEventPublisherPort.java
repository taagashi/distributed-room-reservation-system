package com.br.thaua.auth_service.core.messaging.publishers;

public interface AuthEventPublisherPort {
    void createdAuth(Object payload);
    void updatedAuth(Object payload);
    void deletedAuth(Object payload);
}
