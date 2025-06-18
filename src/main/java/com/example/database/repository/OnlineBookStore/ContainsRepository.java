package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.ContainsEntity;
import com.example.database.domain.OnlineBookStore.ContainsID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ContainsRepository extends JpaRepository<ContainsEntity, ContainsID> {
    @Override
    @Query(
            value = "SELECT * FROM CONTAINS",
            nativeQuery = true
    )
    List<ContainsEntity> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO CONTAINS(BASKETID, ISBN, NUM) VALUES (:basketid, :isbn, :num)",
            nativeQuery = true
    )
    void saveOne(
            @Param("basketid") String basketid,
            @Param("isbn") int isbn,
            @Param("num") int num
    );
}
