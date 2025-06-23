package com.br.thaua.room_service.controllers;

import com.br.thaua.room_service.controllers.dto.RoomRequest;
import com.br.thaua.room_service.controllers.dto.RoomResponse;
import com.br.thaua.room_service.services.ports.RoomServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomServicePort roomServicePort;

    @PostMapping
    public ResponseEntity<RoomResponse> addNewRoom(@RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(roomServicePort.addNewRoom(roomRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> fetchRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomServicePort.fetchRoomById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomResponse> updateRoomById(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(roomServicePort.updateRoomById(id, roomRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomServicePort.deleteRoomById(id);
        return ResponseEntity.ok().build();
    }
}
