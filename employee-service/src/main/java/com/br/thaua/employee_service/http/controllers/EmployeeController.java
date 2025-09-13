package com.br.thaua.employee_service.http.controllers;

import com.br.thaua.employee_service.http.dto.*;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import com.br.thaua.employee_service.http.mappers.EmployeeDtoMapper;
import com.br.thaua.employee_service.http.mappers.FeedbackDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServicePort employeeServicePort;
    private final EmployeeDtoMapper employeeDtoMapper;
    private final FeedbackDtoMapper feedbackDtoMapper;

    @PostMapping("/{employeeId}/feedBack")
    public ResponseEntity<FeedBackResponse> addFeedBackForEmployeeId(@PathVariable Long employeeId, @RequestBody FeedBackRequest feedbackRequest) {
        GatewayRequest currentUser = fetchCurrentUser();
        FeedBackResponse feedBack = feedbackDtoMapper.map(employeeServicePort.addFeedBack(feedbackDtoMapper.map(feedbackRequest), currentUser.id(), employeeId));
        return ResponseEntity.ok(feedBack);
    }

    @GetMapping("/view-profile")
    public ResponseEntity<EmployeeResponse> viewProfile() {
        GatewayRequest currentUser = fetchCurrentUser();
        EmployeeResponse employee = employeeDtoMapper.map(employeeServicePort.fetchEmployee(currentUser.id()));
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/feedBack")
    public ResponseEntity<List<FeedBackResponse>> getFeedBacksReceived() {
        GatewayRequest currentUser = fetchCurrentUser();
        List<FeedBackResponse> feedBack = employeeServicePort.listFeedBacksReceived(currentUser.id()).stream().map(feedbackDtoMapper::map).toList();
        return ResponseEntity.ok(feedBack);
    }

    @GetMapping("/{employeeId}/feedBack")
    public ResponseEntity<List<FeedBackResponse>> getFeedBacksOfEmployee(@PathVariable Long employeeId) {
        List<FeedBackResponse> feedBack = employeeServicePort.listFeedBacksOfEmployee(employeeId).stream().map(feedbackDtoMapper::map).toList();
        return ResponseEntity.ok(feedBack);
    }

    @GetMapping("/{employeeId}/feedBack/received")
    public ResponseEntity<List<FeedBackResponse>> getFeedBacksReceivedOfEmployee(@PathVariable Long employeeId) {
        List<FeedBackResponse> feedBack = employeeServicePort.listFeedBacksReceived(employeeId).stream().map(feedbackDtoMapper::map).toList();
        return ResponseEntity.ok(feedBack);
    }

    @PutMapping("/feedBack/{feedBackId}")
    public ResponseEntity<FeedBackResponse> updateFeedBack(@PathVariable Long feedBackId, @RequestBody FeedBackRequest feedBackRequest) {
        GatewayRequest currentUser = fetchCurrentUser();
        FeedBackResponse feedBack = feedbackDtoMapper.map(employeeServicePort.updateFeedBack(feedBackId, currentUser.id(), feedbackDtoMapper.map(feedBackRequest)));
        return ResponseEntity.ok(feedBack);
    }

    @DeleteMapping("/feedBack/{feedBackId}")
    public void deleteFeedBack(@PathVariable  Long feedBackId) {
        GatewayRequest currentUser = fetchCurrentUser();
        employeeServicePort.deleteFeedBack(feedBackId, currentUser.id());
    }

    private GatewayRequest fetchCurrentUser() {
        return new GatewayRequest(1L, "thaua", "gabrielthaua13@gmail.com", List.of());
    }

}
