package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.PublisherEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublisherRepository extends JpaRepository<PublisherEntity, String> {
    @Override
    @Query(
            value = "SELECT * FROM PUBLISHER",
            nativeQuery = true
    )
    List<PublisherEntity> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO PUBLISHER(NAME, ADDRESS, PHONE, URL) VALUES (:name, :address, :phone, :url)",
            nativeQuery = true
    )
    void saveOne(
            @Param("name") String name,
            @Param("address") String address,
            @Param("phone") String phone,
            @Param("url") String url
    );
}

