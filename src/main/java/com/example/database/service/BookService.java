package com.example.database.service;

import com.example.database.domain.BookEntity;
import com.example.database.domain.BookRental;
import com.example.database.domain.User;
import com.example.database.dto.BookDto;
import com.example.database.repository.BookEntityRepository;
import com.example.database.repository.BookRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    
    private final BookEntityRepository bookRepository;
    private final BookRentalRepository rentalRepository;
    
    @Autowired
    public BookService(BookEntityRepository bookRepository, BookRentalRepository rentalRepository) {
        this.bookRepository = bookRepository;
        this.rentalRepository = rentalRepository;
    }
    
    // 책 검색 기능
    public List<BookEntity> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepository.findByAvailableTrue();
        }
        return bookRepository.searchByKeyword(keyword.trim());
    }
    
    public List<BookEntity> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<BookEntity> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<BookEntity> searchByYear(int year) {
        return bookRepository.findByYear(year);
    }
    
    public List<BookEntity> searchByYearRange(int startYear, int endYear) {
        return bookRepository.findByYearBetween(startYear, endYear);
    }
    
    public List<BookEntity> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }
    
    public Optional<BookEntity> getBookByIsbn(Long isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    // 책 대출 기능
    public BookRental rentBook(User user, Long bookIsbn) {
        Optional<BookEntity> bookOpt = bookRepository.findByIsbn(bookIsbn);
        if (bookOpt.isEmpty()) {
            throw new RuntimeException("책을 찾을 수 없습니다.");
        }
        
        BookEntity book = bookOpt.get();
        if (!book.isAvailable()) {
            throw new RuntimeException("이미 대출 중인 책입니다.");
        }
        
        // 현재 대출 중인지 확인
        List<BookRental> existingRentals = rentalRepository.findByUserAndStatus(user, BookRental.RentalStatus.RENTED);
        for (BookRental rental : existingRentals) {
            if (rental.getBookIsbn().equals(bookIsbn)) {
                throw new RuntimeException("이미 대출 중인 책입니다.");
            }
        }
        
        // 대출 기간 설정 (14일)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueDate = now.plusDays(14);
        
        // 책을 대출 불가능으로 설정
        book.setAvailable(false);
        bookRepository.save(book);
        
        // 대출 기록 생성
        BookRental rental = BookRental.builder()
                .user(user)
                .bookIsbn(bookIsbn.intValue())
                .rentalDate(now)
                .dueDate(dueDate)
                .status(BookRental.RentalStatus.RENTED)
                .build();
        
        return rentalRepository.save(rental);
    }
    
    // 책 반납 기능
    public BookRental returnBook(User user, Long bookIsbn) {
        List<BookRental> rentals = rentalRepository.findByUserAndStatus(user, BookRental.RentalStatus.RENTED);
        
        BookRental rental = null;
        for (BookRental r : rentals) {
            if (r.getBookIsbn().equals(bookIsbn.intValue())) {
                rental = r;
                break;
            }
        }
        
        if (rental == null) {
            throw new RuntimeException("대출 중인 책이 아닙니다.");
        }
        
        // 반납 처리
        rental.setReturnDate(LocalDateTime.now());
        rental.setStatus(BookRental.RentalStatus.RETURNED);
        
        // 책을 대출 가능으로 설정
        Optional<BookEntity> bookOpt = bookRepository.findByIsbn(bookIsbn);
        if (bookOpt.isPresent()) {
            BookEntity book = bookOpt.get();
            book.setAvailable(true);
            bookRepository.save(book);
        }
        
        return rentalRepository.save(rental);
    }
    
    // 사용자의 대출 현황 조회
    public List<BookRental> getUserRentals(User user) {
        return rentalRepository.findByUser(user);
    }
    
    public List<BookRental> getUserActiveRentals(User user) {
        return rentalRepository.findByUserAndStatus(user, BookRental.RentalStatus.RENTED);
    }
    
    // 연체 확인 및 업데이트
    public void checkAndUpdateOverdueRentals() {
        LocalDateTime now = LocalDateTime.now();
        List<BookRental> overdueRentals = rentalRepository.findByDueDateBeforeAndStatus(now, BookRental.RentalStatus.RENTED);
        
        for (BookRental rental : overdueRentals) {
            rental.setStatus(BookRental.RentalStatus.OVERDUE);
            rentalRepository.save(rental);
        }
    }
    
    // 책 등록 기능
    public BookEntity registerBook(BookDto bookDto) {
        BookEntity book = BookEntity.builder()
                .isbn(bookDto.getIsbn().longValue())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .year(bookDto.getYear())
                .price(bookDto.getPrice())
                .available(true)
                .build();
        
        return bookRepository.save(book);
    }
} 