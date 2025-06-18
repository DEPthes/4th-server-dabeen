package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.BookEntity;
import com.example.database.repository.OnlineBookStore.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 도서 정보 관리를 위한 서비스 클래스
 * 도서 등록, 검색, 가격 관리 등을 처리
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 모든 도서 정보 조회
     */
    public List<BookEntity> findAll() { 
        return bookRepository.findAll(); 
    }

    /**
     * 새로운 도서 등록
     * @param bookEntity 등록할 도서 정보
     * @return 등록된 도서 정보
     */
    public BookEntity save(BookEntity bookEntity) {
        bookRepository.saveOne(
                bookEntity.getIsbn(),
                bookEntity.getTitle(),
                bookEntity.getYear(),
                bookEntity.getPrice()
        ); 
        return bookEntity;
    }

    /**
     * 2-(g) 특정 재고량 이상인 도서 검색
     * @param stock 최소 재고량
     * @return 조건을 만족하는 도서 목록
     */
    public List<BookRepository.BookStockInterface> getBooksByStock(int stock) {
        return bookRepository.findBooksByStock(stock);
    }

    /**
     * 2-(g) 특정 재고량 이상인 도서의 가격 할인 적용
     * @param stock 최소 재고량
     * @param discountRate 할인율 (%)
     */
    @Transactional
    public void applyDiscountToBooks(int stock, double discountRate) {
        bookRepository.updatePricesByStockAndDiscount(discountRate, stock);
    }
}
