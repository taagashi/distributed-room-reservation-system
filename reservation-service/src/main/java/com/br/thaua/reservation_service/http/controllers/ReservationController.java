package com.br.thaua.reservation_service.http.controllers;

import com.br.thaua.reservation_service.core.services.ReservationServicePort;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.http.dto.ReservationRequest;
import com.br.thaua.reservation_service.http.dto.ReservationResponse;
import com.br.thaua.reservation_service.http.mappers.ReservationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationServicePort reservationServicePort;
    private final ReservationDtoMapper reservationDtoMapper;

    @PostMapping
    public ResponseEntity<ReservationResponse> addNewReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationDtoMapper.map(reservationRequest);
        ReservationResponse reservationResponse = reservationDtoMapper.map(reservationServicePort.addNewReservation(reservation));
        return ResponseEntity.ok(reservationResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationResponse> fetchReservationById(@PathVariable Long id) {
        ReservationResponse reservationResponse = reservationDtoMapper.map(reservationServicePort.fetchReservationById(id));
        return ResponseEntity.ok(reservationResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable Long id, @RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationDtoMapper.map(reservationRequest);
        ReservationResponse reservationResponse = reservationDtoMapper.map(reservationServicePort.updateReservation(id, reservation));
        return ResponseEntity.ok(reservationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReservationResponse>> fetchAllReservation() {
        List<ReservationResponse> reservationResponseList = reservationServicePort.fetchAllReservation().stream().map(reservationDtoMapper::map).toList();
        return ResponseEntity.ok(reservationResponseList);
    }

    @GetMapping("/list/employee/{employeeId}")
    public ResponseEntity<List<ReservationResponse>> fetchAllReservationByEmployee(@PathVariable Long employeeId) {
        List<ReservationResponse> reservationResponseList = reservationServicePort.fetchAllReservationByEmployee(employeeId).stream().map(reservationDtoMapper::map).toList();
        return ResponseEntity.ok(reservationResponseList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable Long id) {
        reservationServicePort.deleteReservationById(id);
        return ResponseEntity.ok().build();
    }
}
