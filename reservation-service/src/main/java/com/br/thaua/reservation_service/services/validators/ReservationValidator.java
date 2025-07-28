package com.br.thaua.reservation_service.services.validators;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservationValidator {
    public boolean validateDateTimeReservation(LocalDate date, LocalTime startAt, LocalTime end) {
        return validateDateReservation(date) && validateTimeReservation(startAt, end);
    }

    private boolean validateDateReservation(LocalDate date) {
        LocalDate timeNow = LocalDate.now();
        return ((timeNow.isBefore(date) || timeNow.isEqual(date)));
    }

    private boolean validateTimeReservation(LocalTime startAt, LocalTime end) {
        LocalTime timeNow = LocalTime.now();
        return (timeNow.isBefore(startAt) || timeNow.equals(startAt)) && (timeNow.isBefore(end) || timeNow.equals(end)) && validateEndTimeIsGreaterThanStartAt(startAt, end) ;
    }

    private boolean validateEndTimeIsGreaterThanStartAt(LocalTime startAt, LocalTime end) {
        return end.isAfter(startAt);
    }
}
