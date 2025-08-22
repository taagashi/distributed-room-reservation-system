package com.br.thaua.reservation_service.http.controllers;

import com.br.thaua.reservation_service.core.services.ParticipantServicePort;
import com.br.thaua.reservation_service.core.services.ReservationServicePort;
import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.domain.TypeParticipant;
import com.br.thaua.reservation_service.http.dto.*;
import com.br.thaua.reservation_service.http.mappers.ParticipantDtoMapper;
import com.br.thaua.reservation_service.http.mappers.ReservationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationServicePort reservationServicePort;
    private final ParticipantServicePort participantServicePort;
    private final ReservationDtoMapper reservationDtoMapper;
    private final ParticipantDtoMapper participantDtoMapper;

    @PostMapping
    public ResponseEntity<ReservationResponse> addNewReservation(@RequestBody ReservationRequest reservationRequest) {
        GatewayRequest currentUser = fetchCurrentUser();
        Reservation reservation = reservationDtoMapper.map(reservationRequest);
        ReservationResponse reservationResponse = reservationDtoMapper.map(reservationServicePort.addNewReservation(reservation, currentUser.id(), currentUser.email()));
        return ResponseEntity.ok(reservationResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationResponse> fetchReservationById(@PathVariable Long id) {
        ReservationResponse reservationResponse = reservationDtoMapper.map(reservationServicePort.fetchReservationById(id));
        return ResponseEntity.ok(reservationResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable Long id, @RequestBody ReservationRequest reservationRequest) {
        GatewayRequest currentUser = fetchCurrentUser();
        Reservation reservation = reservationDtoMapper.map(reservationRequest);
        ReservationResponse reservationResponse = reservationDtoMapper.map(reservationServicePort.updateReservation(id, currentUser.id(), reservation));
        return ResponseEntity.ok(reservationResponse);
    }

    @PostMapping("/{reservationId}/participants")
    public ResponseEntity<List<ParticipantResponse>> addNewParticipantForReservation(@PathVariable Long reservationId, @RequestBody List<ParticipantRequest> participantRequests) {
        GatewayRequest currentUser = fetchCurrentUser();
        List<Participant> participants = participantRequests.stream().map(participantDtoMapper::map).toList();
        return ResponseEntity.ok(participantServicePort.addNewParticipantsForReservation(participants, reservationId, currentUser.id()).stream().map(participantDtoMapper::map).toList());
    }

    @GetMapping("/{reservationId}/participants")
    public ResponseEntity<List<ParticipantResponse>> listParticipantsByReservationId(@PathVariable Long reservationId) {
        List<Participant> participants = participantServicePort.listParticipantsByReservationId(reservationId);
        return ResponseEntity.ok(participants.stream().map(participantDtoMapper::map).toList());
    }

    @GetMapping("/{reservationId}/participants/type")
    public ResponseEntity<List<ParticipantResponse>> listParticipantsOfOneTypeByReservationId(@PathVariable Long reservationId, @RequestParam String type) {
        List<Participant> participants = participantServicePort.listParticipantsOfOneTypeByReservationId(TypeParticipant.valueOf(type), reservationId);
        return ResponseEntity.ok(participants.stream().map(participantDtoMapper::map).toList());
    }

    @GetMapping("/{reservationId}/participants/cheackin")
    public ResponseEntity<List<ParticipantResponse>> listParticipantsWithSpecificCheackinByReservationId(@PathVariable Long reservationId, @RequestParam boolean cheackin) {
        List<Participant> participants = participantServicePort.listParticipantsWithSpecificCheackinByReservationId(cheackin, reservationId);
        return ResponseEntity.ok(participants.stream().map(participantDtoMapper::map).toList());
    }

    @GetMapping("/{reservationId}/participants/{participantId}")
    public ResponseEntity<ParticipantResponse> fetchParticipant(@PathVariable Long reservationId, @PathVariable Long participantId) {
        return ResponseEntity.ok(participantDtoMapper.map(participantServicePort.fetchParticipantOfReservation(participantId, reservationId)));
    }

    @GetMapping
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
        GatewayRequest currentUser = fetchCurrentUser();
        reservationServicePort.deleteReservationById(id, currentUser.id());
        return ResponseEntity.ok().build();
    }

    private GatewayRequest fetchCurrentUser() {
        return (GatewayRequest) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
