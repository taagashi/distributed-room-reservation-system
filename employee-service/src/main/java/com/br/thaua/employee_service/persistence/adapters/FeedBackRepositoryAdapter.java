package com.br.thaua.employee_service.persistence.adapters;

import com.br.thaua.employee_service.core.repository.FeedBackRepositoryPort;
import com.br.thaua.employee_service.domain.FeedBack;
import com.br.thaua.employee_service.persistence.mappers.FeedBackMapper;
import com.br.thaua.employee_service.persistence.models.FeedBackEntity;
import com.br.thaua.employee_service.persistence.repositories.FeedBackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedBackRepositoryAdapter implements FeedBackRepositoryPort {
    private final FeedBackRepository feedBackRepository;
    private final FeedBackMapper mapper;

    @Override
    public FeedBack save(FeedBack feedBack) {
        FeedBackEntity saved = mapper.map(feedBack);
        return mapper.map(feedBackRepository.save(saved));
    }

    @Override
    public List<FeedBack> listFeedBacksOfEmployee(Long employeeId) {
        return feedBackRepository.listFeedBacksOfEmployee(employeeId).stream().map(mapper::map).toList();
    }

    @Override
    public List<FeedBack> listFeedBacksReceivedOfEmployee(Long employeeId) {
        return feedBackRepository.listFeedBacksReceivedOfEmployee(employeeId).stream().map(mapper::map).toList();
    }

    @Override
    public FeedBack findById(Long feedBackId) {
        return mapper.map(feedBackRepository.findById(feedBackId).get());
    }

    @Override
    public void deleteById(Long feedBackId) {
        feedBackRepository.deleteById(feedBackId);
    }
}
