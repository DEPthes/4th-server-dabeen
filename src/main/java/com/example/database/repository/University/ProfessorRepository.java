package com.example.database.repository.University;

import com.example.database.domain.University.ProfessorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, String> {
    // JPA의 기본 findAll() 메서드를 사용

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO PROFESSOR(PNO, PNAME, PMAJOR, PDEPT) VALUES (:pno, :pname, :pmajor, :pdept)",
            nativeQuery = true
    )
    void saveOne(
            @Param("pno") String pno,
            @Param("pname") String pname,
            @Param("pmajor") String pmajor,
            @Param("pdept") String pdept
    );
}
