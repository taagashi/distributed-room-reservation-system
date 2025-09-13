package com.br.thaua.employee_service.services.adapters;

import com.br.thaua.employee_service.core.repository.FavRoomRepositoryPort;
import com.br.thaua.employee_service.core.repository.FeedBackRepositoryPort;
import com.br.thaua.employee_service.core.repository.ReservationLogRepositoryPort;
import com.br.thaua.employee_service.domain.*;
import com.br.thaua.employee_service.core.repository.EmployeeRepositoryPort;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceAdapter implements EmployeeServicePort {
    private final EmployeeRepositoryPort employeeRepositoryPort;
    private final FavRoomRepositoryPort favRoomRepositoryPort;
    private final ReservationLogRepositoryPort reservationLogRepositoryPort;
    private final FeedBackRepositoryPort feedBackRepositoryPort;

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setSalary(new BigDecimal("2400"));
        employee.setScore(100);
        employee.setEmployeeState(EmployeeState.COMMITTED);
        return employeeRepositoryPort.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepositoryPort.update(employee.getId(), employee.getEmail());
    }

    @Override
    public void increaseEmployeeScore(Long employeeId, int score) {
        employeeRepositoryPort.increaseEmployeeScore(employeeId, score);
        executeVerifyEmployeeScoreAndState(employeeId);
    }

    @Override
    public void deCreaseEmployeeScore(Long employeeId, int score) {
        employeeRepositoryPort.deCreaseEmployeeScore(employeeId, score);
        executeVerifyEmployeeScoreAndState(employeeId);
    }

    @Override
    public void addFavRoom(FavRoom favRoom) {
        favRoomRepositoryPort.save(favRoom);
    }

    @Override
    public void deleteFavRoom(Long roomId) {
        favRoomRepositoryPort.deleteById(roomId);
    }

    @Override
    public void increaseReservation(ReservationLog reservationLog) {
        if(reservationLogRepositoryPort.reservationExists(reservationLog.getYear(), reservationLog.getMoth())) {
            reservationLogRepositoryPort.increaseReservation(reservationLog.getYear(), reservationLog.getMoth());
            return;
        }

        reservationLog.setQuantityReservations(1L);
        reservationLogRepositoryPort.save(reservationLog);
    }

    @Transactional
    @Override
    public void deCreaseReservation(Integer year, Integer moth) {
        if(reservationLogRepositoryPort.oneReservationExists(year, moth)) {
            reservationLogRepositoryPort.deleteReservation(year, moth);
            return;
        }
        reservationLogRepositoryPort.deCreaseReservation(year, moth);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepositoryPort.deleteById(employee.getId());
    }

    @Override
    public FeedBack addFeedBack(FeedBack feedBack, Long authorId, Long receiverId) {
        Employee author = employeeRepositoryPort.findById(authorId).orElseThrow(() -> new RuntimeException("author not found"));
        Employee receiver = employeeRepositoryPort.findById(receiverId).orElseThrow(() -> new RuntimeException("receiver not found"));

        feedBack.defineAuthor(author);
        feedBack.defineReceiver(receiver);
        feedBack.verifyAuthorReceiver();
        employeeRepositoryPort.increaseEmployeeScore(author.getId(), 15);

        EmployeeState oldState = author.getEmployeeState();
        author.verifyScore();
        EmployeeState currentState = author.getEmployeeState();

        if(!currentState.equals(oldState)) {
            employeeRepositoryPort.update(author);
        }

        switch (feedBack.getFeedBackType()) {
            case POSITIVE -> employeeRepositoryPort.increaseEmployeeScore(receiver.getId(), 8);
            case MODERATE -> employeeRepositoryPort.increaseEmployeeScore(receiver.getId(), 2);
            case NEGATIVE -> employeeRepositoryPort.deCreaseEmployeeScore(receiver.getId(), 4);
        }
        return feedBackRepositoryPort.save(feedBack);
    }

    @Override
    public List<FeedBack> listFeedBacksOfEmployee(Long employeeId) {
        return feedBackRepositoryPort.listFeedBacksOfEmployee(employeeId);
    }

    @Override
    public List<FeedBack> listFeedBacksReceived(Long employeeId) {
        return feedBackRepositoryPort.listFeedBacksReceivedOfEmployee(employeeId);
    }

    @Override
    public FeedBack updateFeedBack(Long feedBackId, Long authorId, FeedBack newFeedBack) {
        FeedBack oldFeedBack = feedBackRepositoryPort.findById(feedBackId);

        if(!oldFeedBack.getAuthor().getId().equals(authorId)) {
            throw new RuntimeException();
        }

        oldFeedBack.setFeedBack(newFeedBack.getFeedBack());

        if(!newFeedBack.getFeedBackType().equals(oldFeedBack.getFeedBackType())) {
            oldFeedBack.setFeedBackType(newFeedBack.getFeedBackType());
        }
        return feedBackRepositoryPort.save(oldFeedBack);
    }

    @Override
    public void deleteFeedBack(Long feedBackId, Long userId) {
        FeedBack deleted = feedBackRepositoryPort.findById(feedBackId);

        if(!deleted.getAuthor().getId().equals(userId)) {
            throw new RuntimeException();
        }

        feedBackRepositoryPort.deleteById(feedBackId);
    }

    @Override
    public Employee fetchEmployee(Long employeeId) {
        return employeeRepositoryPort.findById(employeeId).orElseThrow(() -> new RuntimeException("employee not found"));
    }

    private void executeVerifyEmployeeScoreAndState(Long employeeId) {
        Employee employee = employeeRepositoryPort.findById(employeeId).orElseThrow(() -> new RuntimeException("employee not found"));

        EmployeeState oldState = employee.getEmployeeState();
        employee.verifyScore();
        EmployeeState newState = employee.getEmployeeState();

        if(!newState.equals(oldState)) {
            employeeRepositoryPort.update(employee);
        }
    }
}
