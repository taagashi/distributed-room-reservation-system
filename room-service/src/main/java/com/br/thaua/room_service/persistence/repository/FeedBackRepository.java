    package com.br.thaua.room_service.persistence.repository;

    import com.br.thaua.room_service.domain.FeedBack;
    import com.br.thaua.room_service.domain.FeedBackType;
    import com.br.thaua.room_service.domain.RoomStat;
    import com.br.thaua.room_service.persistence.models.FeedBackEntity;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;

    import java.util.List;

    public interface FeedBackRepository extends JpaRepository<FeedBackEntity, Long> {
        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                AND fb.feedBackType = ?2
                """)
        List<FeedBackEntity> listFeedBacksByFeedBackType(Long roomId, FeedBackType feedBackType);

        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                """)
        List<FeedBackEntity> findAllByRoomId(Long roomId);

        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                AND fb.feedBackType = ?2
                AND fb.author = ?3
                """)
        List<FeedBackEntity> listFeedBacksByFeedBackTypeAndAuthor(Long roomId, FeedBackType feedBackType, String author);

        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                AND fb.feedBackType = ?2
                AND fb.authorId = ?3
                """)
        List<FeedBackEntity> listFeedBacksByFeedBackTypeAndAuthorId(Long roomId, FeedBackType feedBackType, Long authorId);


        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                AND fb.author = ?2
                """)
        List<FeedBackEntity> listFeedBacksByAuthor(Long roomId, String author);

        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                AND fb.authorId = ?2
                """)
        List<FeedBackEntity> listFeedBacksByAuthorId(Long roomId, Long authorId);

        @Query("""
                SELECT fb FROM FeedBackEntity fb
                WHERE fb.room.id = ?1
                AND fb.id = ?2
                """)
        FeedBackEntity findByIdAndRoomId(Long roomId, Long feedBackId);

        @Query("""
                DELETE FROM FeedBackEntity fb
                WHERE fb.id = ?1
                AND fb.authorId = ?2
                """)
        void deleteById(Long feedBackId, Long authorId);
    }
