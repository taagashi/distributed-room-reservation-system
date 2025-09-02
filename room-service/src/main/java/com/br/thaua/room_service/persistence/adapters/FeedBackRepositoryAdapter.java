package com.br.thaua.room_service.persistence.adapters;

import com.br.thaua.room_service.core.repository.FeedBackRepositoryPort;
import com.br.thaua.room_service.domain.FeedBack;
import com.br.thaua.room_service.domain.FeedBackType;
import com.br.thaua.room_service.domain.RoomStat;
import com.br.thaua.room_service.persistence.mappers.FeedBackMapper;
import com.br.thaua.room_service.persistence.mappers.RoomMapper;
import com.br.thaua.room_service.persistence.models.FeedBackEntity;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import com.br.thaua.room_service.persistence.repository.FeedBackRepository;
import com.br.thaua.room_service.persistence.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedBackRepositoryAdapter implements FeedBackRepositoryPort {
    private final FeedBackRepository feedBackRepository;
    private final RoomRepository roomRepository;
    private final FeedBackMapper mapper;
    private final RoomMapper roomMapper;

    @Override
    public FeedBack save(FeedBack feedBack, Long roomId) {
        FeedBackEntity saved = mapper.map(feedBack);
        RoomEntity room = roomRepository.findById(roomId).get();
        saved.setRoom(room);

        return mapper.map(feedBackRepository.save(saved));
    }

    @Override
    public FeedBack update(FeedBack feedBack) {
        FeedBackEntity saved = feedBackRepository.save(mapper.map(feedBack));
        return mapper.map(saved);
    }

    @Override
    public FeedBack findById(Long id) {
        return mapper.map(feedBackRepository.findById(id).get());
    }

    @Override
    public FeedBack findByIdAndRoomId(Long roomId, Long feedBackId) {
        return mapper.map(feedBackRepository.findByIdAndRoomId(roomId, feedBackId));
    }

    @Override
    public List<FeedBack> findAll() {
        return feedBackRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<FeedBack> listFeedBacksByFeedBackType(Long roomId, FeedBackType feedBackType) {
        return feedBackRepository.listFeedBacksByFeedBackType(roomId, feedBackType)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<FeedBack> listFeedBacksByAuthor(Long roomId, String author) {
        return feedBackRepository.listFeedBacksByAuthor(roomId, author).stream().map(mapper::map).toList();
    }

    @Override
    public List<FeedBack> listFeedBacksByAuthorId(Long roomId, Long authorId) {
        return feedBackRepository.listFeedBacksByAuthorId(roomId, authorId).stream().map(mapper::map).toList();
    }

    @Override
    public List<FeedBack> listFeedBacksByFeedBackTypeAndAuthor(Long roomId, FeedBackType feedBackType, String author) {
        return feedBackRepository.listFeedBacksByFeedBackTypeAndAuthor(roomId, feedBackType, author).stream().map(mapper::map).toList();
    }

    @Override
    public List<FeedBack> listFeedBacksByFeedBackTypeAndAuthorId(Long roomId, FeedBackType feedBackType, Long authorId) {
        return feedBackRepository.listFeedBacksByFeedBackTypeAndAuthorId(roomId, feedBackType, authorId).stream().map(mapper::map).toList();
    }

    @Override
    public void deleteById(Long feedBackId, Long authorId) {
        feedBackRepository.deleteById(feedBackId, authorId);
    }

    @Override
    public List<FeedBack> findAllByRoomId(Long roomId) {
        return feedBackRepository.findAllByRoomId(roomId)
                .stream()
                .map(mapper::map)
                .toList();
    }

}
