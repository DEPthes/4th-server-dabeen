package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.BasketOfEntity;
import com.example.database.domain.OnlineBookStore.BasketOfID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketOfRepository extends JpaRepository<BasketOfEntity, BasketOfID> {
    @Override
    @Query(
            value = "SELECT * FROM BASKET_OF",
            nativeQuery = true
    )
    List<BasketOfEntity> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO BASKET_OF(EMAIL, BASKETID) VALUES (:email, :basketid)",
            nativeQuery = true
    )
    void saveOne(
            @Param("email") String email,
            @Param("basketid") String basketid
    );
}
