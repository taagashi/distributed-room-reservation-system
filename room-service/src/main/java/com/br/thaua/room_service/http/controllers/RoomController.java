package com.br.thaua.room_service.http.controllers;

import com.br.thaua.room_service.http.dto.RoomRequest;
import com.br.thaua.room_service.http.dto.RoomResponse;
import com.br.thaua.room_service.http.mappers.RoomDtoMapper;
import com.br.thaua.room_service.core.services.RoomServicePort;
import com.br.thaua.room_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomServicePort roomServicePort;
    private final RoomDtoMapper roomDtoMapper;

    @PostMapping
    public ResponseEntity<RoomResponse> addNewRoom(@RequestBody RoomRequest roomRequest) {
        Room room = roomDtoMapper.map(roomRequest);
        RoomResponse roomResponse = roomDtoMapper.map(roomServicePort.addNewRoom(room));
        return ResponseEntity.ok(roomResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> fetchRoomById(@PathVariable Long id) {
        RoomResponse roomResponse = roomDtoMapper.map(roomServicePort.fetchRoomById(id));
        return ResponseEntity.ok(roomResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomResponse> updateRoomById(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        Room room = roomDtoMapper.map(roomRequest);
        RoomResponse roomResponse = roomDtoMapper.map(roomServicePort.updateRoomById(id, room));
        return ResponseEntity.ok(roomResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomServicePort.deleteRoomById(id);
        return ResponseEntity.ok().build();
    }
}
