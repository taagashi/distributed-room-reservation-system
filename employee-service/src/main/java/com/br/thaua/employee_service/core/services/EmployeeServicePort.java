package com.br.thaua.employee_service.core.services;

import com.br.thaua.employee_service.domain.*;
import com.br.thaua.employee_service.http.dto.EmployeeRequest;
import com.br.thaua.employee_service.http.dto.FeedBackResponse;

import java.util.List;

public interface EmployeeServicePort {
    Employee addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void increaseEmployeeScore(Long employeeId, int score);
    void deCreaseEmployeeScore(Long employeeId, int score);
    void addFavRoom(FavRoom favRoom);
    void deleteFavRoom(Long roomId);
    void increaseReservation(ReservationLog reservationLog);
    void deCreaseReservation(Integer year, Integer moth);
    void deleteEmployee(Employee employee);
    FeedBack addFeedBack(FeedBack feedBack, Long authorId, Long receiverId);
    List<FeedBack> listFeedBacksOfEmployee(Long employeeId);
    List<FeedBack> listFeedBacksReceived(Long employeeId);
    FeedBack updateFeedBack(Long feedBackId, Long authorId, FeedBack newFeedBack);
    void deleteFeedBack(Long feedBackId, Long userId);
    Employee fetchEmployee(Long employeeId);
}
