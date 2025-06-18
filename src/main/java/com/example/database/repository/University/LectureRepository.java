package com.example.database.repository.University;

import com.example.database.domain.University.LectureEntity;
import com.example.database.domain.University.LectureID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<LectureEntity, LectureID> {
    // JPA의 기본 findAll() 메서드를 사용

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO LECTURE(CNO, PNO, LEC_TIME, ROOM) VALUES (:cno, :pno, :lec_time, :room)",
            nativeQuery = true
    )
    void saveOne(
            @Param("cno") String cno,
            @Param("pno") String pno,
            @Param("lec_time") String lec_time,
            @Param("room") String room
    );
}
