package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.AuthorEntity;
import com.example.database.domain.OnlineBookStore.AuthorID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, AuthorID> {
    @Override
    @Query(
            value = "SELECT * FROM AUTHOR",
            nativeQuery = true
    )
    List<AuthorEntity> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO ENROLL(NAME, ADDRESS, URL) VALUES (:name, :address, :url)",
            nativeQuery = true
    )
    void saveOne(
            @Param("name") String name,
            @Param("address") String address,
            @Param("url") String url
    );
}
