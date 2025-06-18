package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Override
    @Query(
            value = "SELECT * FROM BOOK",
            nativeQuery = true
    )
    List<BookEntity> findAll();

    // 2-(e)
    @Transactional
    @Query(
            value = "SELECT AVG(NVL(PRICE, 0)) as AVG " +
                    "FROM BOOK",
            nativeQuery = true
    ) double getAvgPriceYear();


    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO BOOK(ISBN, TITLE, YEAR, PRICE) VALUES (:isbn, :title, :year, :price)",
            nativeQuery = true
    )
    void saveOne(
            @Param("isbn") int isbn,
            @Param("title") String title,
            @Param("year") int year,
            @Param("price") int price
    );

    // 2- (g)
    // 재고가 특정 수 이상인 도서를 검색하는 쿼리
    @Query(value = """
            SELECT b.ISBN as isbn, b.TITLE as title, b.YEAR as year, 
                   b.PRICE as price, TO_NUMBER(NVL(SUM(s.NUM), 0)) as stock
            FROM BOOK b
            LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
            GROUP BY b.ISBN, b.TITLE, b.YEAR, b.PRICE
            HAVING TO_NUMBER(NVL(SUM(s.NUM), 0)) >= :minStock
            ORDER BY b.ISBN
            """, nativeQuery = true)
    List<BookStockInterface> findBooksByStock(@Param("minStock") int minStock);

    interface BookStockInterface {
        int getIsbn();
        String getTitle();
        int getYear();
        int getPrice();
        int getStock();
    }

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE BOOK b
            SET b.PRICE = ROUND(b.PRICE * (1 - :discountRate / 100))
            WHERE b.ISBN IN (
                SELECT DISTINCT b2.ISBN
                FROM BOOK b2
                LEFT JOIN STOCKS s ON b2.ISBN = s.ISBN
                GROUP BY b2.ISBN
                HAVING TO_NUMBER(NVL(SUM(s.NUM), 0)) >= :minStock
            )
            """, nativeQuery = true)
    void updatePricesByStockAndDiscount(
            @Param("discountRate") double discountRate,
            @Param("minStock") int minStock
    );
}
