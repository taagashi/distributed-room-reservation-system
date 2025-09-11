package com.br.thaua.employee_service.core.repository;

import com.br.thaua.employee_service.domain.FeedBack;

import java.util.List;

public interface FeedBackRepositoryPort {
    FeedBack save(FeedBack feedBack);
    List<FeedBack> listFeedBacksOfEmployee(Long employeeId);
    List<FeedBack> listFeedBacksReceivedOfEmployee(Long employeeId);
    FeedBack findById(Long feedBackId);
    void deleteById(Long feedBackId);
}
