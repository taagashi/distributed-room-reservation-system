package com.br.thaua.reservation_service.services.adapters;

import com.br.thaua.reservation_service.core.cache.EmployeeCachePort;
import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.core.cache.validators.EmployeeCacheValidatorPort;
import com.br.thaua.reservation_service.core.cache.validators.RoomCacheValidatorPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.core.services.ReservationServicePort;
import com.br.thaua.reservation_service.domain.Employee;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceAdapter implements ReservationServicePort {
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final EmployeeCachePort employeeCachePort;
    private final RoomCachePort roomCachePort;
    private final EmployeeCacheValidatorPort employeeCacheValidatorPort;
    private final RoomCacheValidatorPort roomCacheValidatorPort;

    @Override
    public Reservation addNewReservation(Reservation reservation) {
        Employee employee = employeeCachePort.getCacheEmployee(employeeKey(reservation.getEmployeeId()));
        Room room = roomCachePort.getCacheRoom(roomKey(reservation.getRoomId()));

        employeeCacheValidatorPort.validateEmployeeCache(reservation.getEmployeeId(), employee);
        roomCacheValidatorPort.validateRoomCache(reservation.getRoomId(), room);

        return reservationRepositoryPort.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation updated = reservationRepositoryPort.findById(id);

        if(updated == null) {
            throw new RuntimeException("Reservation not found");
        }

        Employee employee = employeeCachePort.getCacheEmployee(employeeKey(reservation.getEmployeeId()));
        Room room = roomCachePort.getCacheRoom(roomKey(reservation.getRoomId()));

        employeeCacheValidatorPort.validateEmployeeCache(reservation.getEmployeeId(), employee);
        roomCacheValidatorPort.validateRoomCache(reservation.getRoomId(), room);

        updated.setEmployeeId(reservation.getEmployeeId());
        updated.setRoomId(reservation.getRoomId());
        updated.setReservedAt(reservation.getReservedAt());
        return reservationRepositoryPort.save(updated);
    }

    @Override
    public Reservation fetchReservationById(Long id) {
        return reservationRepositoryPort.findById(id);
    }

    @Override
    public List<Reservation> fetchAllReservation() {
        return reservationRepositoryPort.findAll();
    }

    @Override
    public List<Reservation> fetchAllReservationByEmployee(Long employeeId) {
        return reservationRepositoryPort.findAllByEmployeeId(employeeId);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepositoryPort.deleteById(id);
    }

    private String employeeKey(Long employeeId) {
        return "employee:" + employeeId;
    }

    private String roomKey(Long roomKey) {
        return "room:" + roomKey;
    }
}
