package com.br.thaua.room_service.core.repository;


import com.br.thaua.room_service.domain.FeedBack;
import com.br.thaua.room_service.domain.FeedBackType;
import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.domain.RoomStat;

import java.util.List;

public interface FeedBackRepositoryPort {
    FeedBack save(FeedBack feedBack, Long roomId);
    FeedBack update(FeedBack feedBack);
    FeedBack findById(Long id);
    FeedBack findByIdAndRoomId(Long roomId, Long feedBackId);
    List<FeedBack> findAll();
    List<FeedBack> listFeedBacksByFeedBackTypeAndAuthor(Long roomId, FeedBackType feedBackType, String author);
    List<FeedBack> listFeedBacksByFeedBackTypeAndAuthorId(Long roomId, FeedBackType feedBackType, Long authorId);
    List<FeedBack> listFeedBacksByFeedBackType(Long roomId, FeedBackType feedBackType);
    List<FeedBack> listFeedBacksByAuthor(Long roomId, String author);
    List<FeedBack> listFeedBacksByAuthorId(Long roomId, Long authorId);
    void deleteById(Long feedBackId, Long authorId);
    List<FeedBack> findAllByRoomId(Long roomId);
}
