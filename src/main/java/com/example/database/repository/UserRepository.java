package com.example.database.repository;

import com.example.database.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByKakaoId(String kakaoId);
    
    boolean existsByEmail(String email);
    
    boolean existsByKakaoId(String kakaoId);
} 