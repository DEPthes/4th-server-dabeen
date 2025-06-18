package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.WarehouseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity,String> {
    @Override
    @Query(
            value = "SELECT * FROM WAREHOUSE",
            nativeQuery = true
    )
    List<WarehouseEntity> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO WAREHOUSE(CODE, ADDRESS, PHONE) VALUES (:code, :address, :phone)",
            nativeQuery = true
    )
    void saveOne(
            @Param("code") String code,
            @Param("address") String address,
            @Param("phone") String phone
    );
}
