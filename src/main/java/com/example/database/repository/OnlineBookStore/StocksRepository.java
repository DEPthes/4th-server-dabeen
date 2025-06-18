package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.StocksEntity;
import com.example.database.domain.OnlineBookStore.StocksID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StocksRepository extends JpaRepository<StocksEntity, StocksID> {
    @Override
    @Query(
            value = "SELECT * FROM STOCKS",
            nativeQuery = true
    )
    List<StocksEntity> findAll();



    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO STOCKS(ISBN, CODE, NUM) VALUES (:isbn, :code, :num)",
            nativeQuery = true
    )
    void saveOne(
            @Param("isbn") int isbn,
            @Param("code") String code,
            @Param("num") int num
    );
}
