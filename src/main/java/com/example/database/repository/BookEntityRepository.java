package com.example.database.repository;

import com.example.database.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    
    List<BookEntity> findByTitleContainingIgnoreCase(String title);
    
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);
    
    List<BookEntity> findByAvailableTrue();
    
    @Query("SELECT b FROM BookEntity b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword%")
    List<BookEntity> searchByKeyword(@Param("keyword") String keyword);
    
    Optional<BookEntity> findByIsbn(Long isbn);
    
    List<BookEntity> findByYear(int year);
    
    List<BookEntity> findByYearBetween(int startYear, int endYear);
} 