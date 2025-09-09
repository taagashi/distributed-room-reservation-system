package com.br.thaua.room_service.http.controllers;

import com.br.thaua.room_service.domain.*;
import com.br.thaua.room_service.http.dto.*;
import com.br.thaua.room_service.http.mappers.EquipmentDtoMapper;
import com.br.thaua.room_service.http.mappers.EquipmentDtoSimpleMapper;
import com.br.thaua.room_service.http.mappers.FeedBackDtoMapper;
import com.br.thaua.room_service.http.mappers.RoomDtoMapper;
import com.br.thaua.room_service.core.services.RoomServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomServicePort roomServicePort;
    private final RoomDtoMapper roomDtoMapper;
    private final EquipmentDtoMapper equipmentDtoMapper;
    private final EquipmentDtoSimpleMapper equipmentDtoSimpleMapper;
    private final FeedBackDtoMapper feedBackDtoMapper;

    @PostMapping
    public ResponseEntity<RoomResponse> addNewRoom(@RequestBody RoomRequest roomRequest) {
        Room room = roomDtoMapper.map(roomRequest);
        RoomResponse roomResponse = roomDtoMapper.map(roomServicePort.addNewRoom(room));
        return ResponseEntity.ok(roomResponse);
    }

    @PostMapping("/{roomId}/like")
    public ResponseEntity<String> likeRoom(@PathVariable Long roomId) {
        GatewayRequest currentUser = currentUser();
//        GatewayRequest currentUser = new GatewayRequest(1L, "thaua", "gabrielthaua@gmail.com", List.of("ADMIN"));
        Room room = roomServicePort.likeRoom(currentUser.id(), roomId);
        return ResponseEntity.ok("sala de número " + room.getRoomNumber() + " foi adicionada aos seus favoritos");
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentResponseSimple> addNewEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        Equipment equipment = roomServicePort.addNewEquipment(equipmentDtoMapper.map(equipmentRequest));
        return ResponseEntity.ok(equipmentDtoSimpleMapper.map(equipment));
    }

    @PostMapping("{roomId}/equipment/{equipmentId}/associate")
    public ResponseEntity<RoomResponse> associateEquipmentToRoom(@PathVariable Long roomId, @PathVariable Long equipmentId, @RequestParam Integer quantity) {
        Room room = roomServicePort.addEquipmentsInRoom(equipmentId, roomId, quantity);
        return ResponseEntity.ok(roomDtoMapper.map(room));
    }

    @PostMapping("{roomId}/feedBack")
    public ResponseEntity<FeedBackResponse> addNewFeedBack(@PathVariable Long roomId, @RequestBody FeedBackRequest feedBackRequest) {
//        GatewayRequest currentUser = currentUser();
        GatewayRequest currentUser = new GatewayRequest(1L, "thaua", "gabrielthaua@gmail.com", List.of("ADMIN"));
        FeedBack feedBack = roomServicePort.addFeedBackForRoom(feedBackDtoMapper.map(feedBackRequest), roomId, currentUser.id(), currentUser.email());
        return ResponseEntity.ok(feedBackDtoMapper.map(feedBack));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> listRoom(@RequestParam(required = false) String stat, @RequestParam(required = false) String feedBackType, @RequestBody(required = false) List<String> equipmentRequest) {
        RoomStat roomStat = null;
        FeedBackType feedBackType1 = null;
        if(stat != null) {
            roomStat = RoomStat.valueOf(stat.toUpperCase());
        }

        if(feedBackType != null) {
            feedBackType1 = FeedBackType.valueOf(feedBackType.toUpperCase());
        }

        List<RoomResponse> roomResponses = roomServicePort.listRoom(roomStat, feedBackType1, equipmentRequest).stream().map(roomDtoMapper::map).toList();
        return ResponseEntity.ok(roomResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> fetchRoomById(@PathVariable Long id) {
        RoomResponse roomResponse = roomDtoMapper.map(roomServicePort.fetchRoomById(id));
        return ResponseEntity.ok(roomResponse);
    }

    @GetMapping("/equipment")
    public ResponseEntity<List<EquipmentResponseSimple>> listEquipments() {
        List<EquipmentResponseSimple> equipments = roomServicePort.listEquipments().stream().map(equipmentDtoSimpleMapper::map).toList();
        return ResponseEntity.ok(equipments);
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<EquipmentResponseSimple> fetchEquipmentById(@PathVariable Long equipmentId) {
        Equipment equipment = roomServicePort.fetchEquipmentById(equipmentId);
        return ResponseEntity.ok(equipmentDtoSimpleMapper.map(equipment));
    }


    @GetMapping("/{roomId}/feedBack")
    public ResponseEntity<List<FeedBackResponse>> listFeedBacks(@PathVariable Long roomId, @RequestParam(required = false) String type, @RequestParam(required = false) String author, @RequestParam(required = false) Long authorId) {
        FeedBackType feedBackTypeEnum = null;
        if(type != null) {
            feedBackTypeEnum = FeedBackType.valueOf(type.toUpperCase());
        }

        List<FeedBackResponse> feedbacks = roomServicePort.listFeedBackOfRoom(roomId, feedBackTypeEnum, author, authorId).stream().map(feedBackDtoMapper::map).toList();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{roomId}/feedBack{feedBackId}")
    public ResponseEntity<FeedBackResponse> fetchFeedBackById(@PathVariable Long roomId, @PathVariable Long feedBackId) {
        FeedBackResponse feedBackResponse = feedBackDtoMapper.map(roomServicePort.fetchFeedBackById(roomId, feedBackId));
        return ResponseEntity.ok(feedBackResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomResponse> updateRoomById(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        Room room = roomDtoMapper.map(roomRequest);
        RoomResponse roomResponse = roomDtoMapper.map(roomServicePort.updateRoomById(id, room));
        return ResponseEntity.ok(roomResponse);
    }

    @PutMapping("/feedBack/{feedBackId}/update")
    public ResponseEntity<FeedBackResponse> updateFeedBackById(@PathVariable Long feedBackId, @RequestBody FeedBackRequest feedBackRequest) {
        GatewayRequest currentUser = currentUser();
        FeedBackResponse feedBack = feedBackDtoMapper.map(roomServicePort.updateFeedBackById(feedBackId, feedBackDtoMapper.map(feedBackRequest), currentUser.id(), currentUser.email()));
        return ResponseEntity.ok(feedBack);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomServicePort.deleteRoomById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("feedBack/{feedBackId}/delete/")
    public ResponseEntity<Void> deleteFeedBackById(@PathVariable Long feedBackId) {
        GatewayRequest currentUser = currentUser();
        roomServicePort.deleteFeedBackById(feedBackId, currentUser.id());
        return ResponseEntity.ok().build();
    }

    private GatewayRequest currentUser() {
        return (GatewayRequest) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
