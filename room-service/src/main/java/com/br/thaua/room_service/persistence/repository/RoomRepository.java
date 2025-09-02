package com.br.thaua.room_service.persistence.repository;

import com.br.thaua.room_service.domain.FeedBackType;
import com.br.thaua.room_service.domain.RoomStat;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("""
            SELECT r FROM RoomEntity r
            WHERE r.stat = ?1
            """)
    List<RoomEntity> findAllByStat(RoomStat roomStat);

    @Query("""
            SELECT DISTINCT r FROM RoomEntity r
            JOIN r.feedBacks fb
            JOIN fb.feedBackType fbt
            WHERE fbt = ?1
            """)
    List<RoomEntity> findAllByFeedBackFeedBackType(FeedBackType feedBackType);

    @Query("""
            SELECT DISTINCT r FROM RoomEntity r
            JOIN r.roomEquipments req
            JOIN req.equipment eq
            JOIN r.feedBacks f
            WHERE r.stat = ?1
            AND eq.name IN ?2
            AND f.feedBackType = ?3
            """)
    List <RoomEntity> findAllRoomEquipment(RoomStat roomStat, List<String> equipments, FeedBackType feedBackType);

    @Query("""
            SELECT r FROM RoomEntity r
            JOIN  r.roomEquipments req
            JOIN  req.equipment eq
            WHERE r.stat = ?1
            AND eq.name IN ?2
            """)
    List<RoomEntity> findAllRoomEquipment(RoomStat roomStat, List<String> equipments);

    @Query("""
            SELECT r FROM RoomEntity r
            JOIN  r.feedBacks f
            WHERE r.stat = ?1
            AND f.feedBackType = ?2
            """)
    List<RoomEntity> findAllRoomEquipment(RoomStat roomStat, FeedBackType feedBackType);

    @Query("""
            SELECT r FROM RoomEntity r
            JOIN r.roomEquipments req
            JOIN req.equipment eq
            JOIN r.feedBacks f
            WHERE eq.name IN ?1
            AND f.feedBackType = ?2
            """)
    List<RoomEntity> findAllRoomEquipment(List<String> equipments, FeedBackType feedBackType);

    @Query("""
            SELECT r FROM RoomEntity r
            JOIN r.roomEquipments req
            JOIN req.equipment eq
            WHERE eq.name IN ?1
            GROUP BY r.id
            HAVING COUNT(eq.id) = ?2
            """)
    List<RoomEntity> findAllRoomEquipment(List<String> equipments, int size);

    @Query("""
            SELECT r FROM RoomEntity r
            WHERE r.stat = ?1
            """)
    List<RoomEntity> findAllRoomEquipment(RoomStat roomStat);

    @Query("""
            SELECT r FROM RoomEntity r
            JOIN r.feedBacks f
            WHERE f.feedBackType = ?1
            """)
    List<RoomEntity> findAllRoomEquipment(FeedBackType feedBackType);

    @Transactional
    @Modifying
    @Query("""
            UPDATE RoomEntity r SET r.stat = ?2
            WHERE r.id = ?1
            """)
    int updateRoomStat(Long roomId, RoomStat roomStat);
}
