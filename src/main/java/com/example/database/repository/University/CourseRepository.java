package com.example.database.repository.University;

import com.example.database.domain.University.CourseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    // 1-(a)
    @Override
    @Query(
            value = "SELECT * FROM COURSE",
            nativeQuery = true
    )
    List<CourseEntity> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO COURSE(CNO, CNAME, CREDIT, SESSIONS) VALUES (:cno, :cname, :credit, :sessions)",
            nativeQuery = true
    )
    void saveOne(
            @Param("cno") String cno,
            @Param("cname") String cname,
            @Param("credit") int credit,
            @Param("sessions") int sessions
    );
}
