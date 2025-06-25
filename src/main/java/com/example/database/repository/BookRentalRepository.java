package com.example.database.repository;

import com.example.database.domain.BookRental;
import com.example.database.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
    
    List<BookRental> findByUser(User user);
    
    List<BookRental> findByUserAndStatus(User user, BookRental.RentalStatus status);
    
    List<BookRental> findByBookIsbnAndStatus(Integer bookIsbn, BookRental.RentalStatus status);
    
    List<BookRental> findByDueDateBeforeAndStatus(LocalDateTime date, BookRental.RentalStatus status);
} 