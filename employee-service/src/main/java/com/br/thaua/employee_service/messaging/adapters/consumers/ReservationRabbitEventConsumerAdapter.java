package com.br.thaua.employee_service.messaging.adapters.consumers;

import com.br.thaua.employee_service.core.messaging.consumers.ReservationEventConsumerPort;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import com.br.thaua.employee_service.domain.EventType;
import com.br.thaua.employee_service.domain.ReservationLog;
import com.br.thaua.employee_service.domain.TypeParticipant;
import com.br.thaua.employee_service.messaging.dto.ReservationAuthEventPresentNotPresent;
import com.br.thaua.employee_service.messaging.dto.ReservationEvent;
import com.br.thaua.employee_service.messaging.dto.ReservationEventUpdated;
import com.br.thaua.employee_service.messaging.mappers.ReservationEventMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationRabbitEventConsumerAdapter implements ReservationEventConsumerPort {
    private final EmployeeServicePort employeeServicePort;
    private final EventPayloadConverter eventPayloadConverter;
    private final ReservationEventMapper mapper;

    @RabbitListener(queues = "employee.reservation.queue")
    @Override
    public void consumerEvents(String event) {
        JsonNode node = eventPayloadConverter.fetchJsonNode(event);
        if (node == null) return;
        EventType eventType = eventPayloadConverter.fetchEventType(node);
        ReservationLog reservationLog = null;

        if(eventType.equals(EventType.RESERVATION_CREATED) || eventType.equals(EventType.RESERVATION_DELETED)) {
            ReservationEvent reservationEvent = eventPayloadConverter.fetchEventClass(ReservationEvent.class, node);
            reservationLog = mapper.map(reservationEvent);
        }

        ReservationEventUpdated reservationEventUpdated = null;
        if(eventType.equals(EventType.RESERVATION_UPDATED)) {
            reservationEventUpdated = eventPayloadConverter.fetchEventClass(ReservationEventUpdated.class, node);
            reservationLog = mapper.map(reservationEventUpdated);
        }

        ReservationAuthEventPresentNotPresent authEventPresentNotPresent = null;
        if(eventType.equals(EventType.RESERVATION_EMPLOYEE_PRESENT) || eventType.equals(EventType.RESERVATION_EMPLOYEE_NOT_PRESENT)) {
            authEventPresentNotPresent = eventPayloadConverter.fetchEventClass(ReservationAuthEventPresentNotPresent.class, node);
        }

        switch (eventType) {
            case RESERVATION_CREATED -> reservationCreated(reservationLog);
            case RESERVATION_UPDATED -> reservationUpdated(reservationLog, reservationEventUpdated.oldYear(), reservationEventUpdated.oldMoth());
            case RESERVATION_DELETED -> reservationDeleted(reservationLog.getYear(), reservationLog.getMoth(), reservationLog.getEmployee().getId());
            case RESERVATION_EMPLOYEE_PRESENT -> employeePresent(authEventPresentNotPresent.authId(), authEventPresentNotPresent.typeParticipant());
            case RESERVATION_EMPLOYEE_NOT_PRESENT -> employeeNotPresent(authEventPresentNotPresent.authId(), authEventPresentNotPresent.typeParticipant());

            }
        }

    private void reservationCreated(ReservationLog reservationLog) {
        employeeServicePort.increaseReservation(reservationLog);
        employeeServicePort.increaseEmployeeScore(reservationLog.getEmployee().getId(), 10);
    }

    private void reservationUpdated(ReservationLog reservationLog, Integer oldYear, Integer oldMoth) {
        employeeServicePort.deCreaseReservation(oldYear, oldMoth);
        employeeServicePort.increaseReservation(reservationLog);
    }

    private void reservationDeleted(Integer year, Integer moth, Long employeeId) {
        employeeServicePort.deCreaseReservation(year, moth);
        employeeServicePort.deCreaseEmployeeScore(employeeId, 10);
    }

    private void employeePresent(Long employeeId, TypeParticipant typeParticipant) {
        switch (typeParticipant) {
            case HOST -> employeeServicePort.increaseEmployeeScore(employeeId, 20);
            case GUEST -> employeeServicePort.increaseEmployeeScore(employeeId, 10);
        }

    }

    private void employeeNotPresent(Long employeeId, TypeParticipant typeParticipant) {
        switch (typeParticipant) {
            case HOST -> employeeServicePort.deCreaseEmployeeScore(employeeId, 25);
            case GUEST -> employeeServicePort.deCreaseEmployeeScore(employeeId, 10);
        }
    }

}

