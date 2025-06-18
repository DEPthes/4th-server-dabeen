package com.example.database.repository.University;

import com.example.database.domain.University.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    // JPA의 기본 findAll() 메서드를 사용

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO STUDENT(SNO, PNO, SNAME, YEAR, DEPT) VALUES (:sno, :pno, :sname, :year, :dept)",
            nativeQuery = true
    )
    void saveOne(
            @Param("sno") String sno,
            @Param("pno") String pno,
            @Param("sname") String sname,
            @Param("year") Integer year,
            @Param("dept") String dept
    );
}
