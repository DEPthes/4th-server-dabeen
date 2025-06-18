package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.PublishedByEntity;
import com.example.database.domain.OnlineBookStore.PublishedByID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublishedByRepository extends JpaRepository<PublishedByEntity, PublishedByID> {
    @Override
    @Query(
            value = "SELECT * FROM PUBLISHED_BY",
            nativeQuery = true
    )
    List<PublishedByEntity> findAll();

    // 2-(b)
    // 출판사 이름으로 검색
    // 해당 출판사에서 출판된 도서의 제목과 가격, 재고량을 확인하는 쿼리
    // 재고량이 0인 경우 '재고없음' 을 띄워주고 0이 아닌 경우 재고량의 데이터 타입을 CHAR로 변경한 후 출력
    @Query(
            value = """
                    SELECT pb.NAME, b.TITLE, b.PRICE,
                           CASE WHEN SUM(s.NUM) IS NULL OR SUM(s.NUM) = 0 
                                THEN '재고없음' 
                                ELSE TO_CHAR(SUM(s.NUM)) 
                           END AS STOCK
                    FROM PUBLISHED_BY pb
                    JOIN BOOK b ON pb.ISBN = b.ISBN
                    LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
                    WHERE pb.NAME = :NAME
                    GROUP BY pb.NAME, b.TITLE, b.PRICE
                    ORDER BY b.TITLE
                    """,
            nativeQuery = true
    )
    List<SearchPublisherInterface> searchPublisher(@Param("NAME") String name);

    interface SearchPublisherInterface {
        String getName();
        String getTitle();
        int getPrice();
        String getStock();
    }

    // 2-(d)
    // 새로운 도서가 등록될 경우에는 저자와 출판사 창고별 재고량이 함께 등록되어야 한다.

    // 1. BOOK INSERT
    @Modifying
    @Transactional
    @Query(
            value = "MERGE INTO BOOK USING DUAL " +
                    "ON (ISBN =:isbn) " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (ISBN, TITLE, YEAR, PRICE) VALUES (:isbn, :title, :year, :price)",
            nativeQuery = true
    )
    void insertBook(
            @Param("isbn") int isbn,
            @Param("title") String title,
            @Param("year") int year,
            @Param("price") int price
    );

    // 2. AUTHOR, WRITTEN_BY INSERT
    @Modifying
    @Transactional
    @Query(
            value = "MERGE INTO AUTHOR USING DUAL " +
                    "ON (NAME =:authorName AND ADDRESS =:authorAddress) " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (NAME, ADDRESS, URL) VALUES (:authorName, :authorAddress, :authorUrl)",
            nativeQuery = true
    )
    void insertAuthor(
            @Param("authorName") String authorName,
            @Param("authorAddress") String authorAddress,
            @Param("authorUrl") String authorUrl
    );

    @Modifying
    @Transactional
    @Query(
            value = "MERGE INTO WRITTEN_BY USING DUAL " +
                    "ON (NAME =:authorName AND ADDRESS =:authorAddress AND ISBN=:isbn) " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (NAME, ADDRESS, ISBN) VALUES (:authorName, :authorAddress, :isbn)",
            nativeQuery = true
    )
    void insertWrittenBy(
            @Param("authorName") String authorName,
            @Param("authorAddress") String authorAddress,
            @Param("isbn") int isbn
    );

    // 3. WAREHOUSE, STOCKS INSERT
    @Modifying
    @Transactional
    @Query(
            value = "MERGE INTO WAREHOUSE USING DUAL " +
                    "ON (CODE=:code) " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (CODE, ADDRESS, PHONE) VALUES (:code, :wareAddress, :warePhone)",
            nativeQuery = true
    )
    void insertWarehouse(
            @Param("code") String code,
            @Param("wareAddress") String wareAddress,
            @Param("warePhone") String warePhone
    );

    @Modifying
    @Transactional
    @Query(
            value = "MERGE INTO STOCKS USING DUAL " +
                    "ON (ISBN =:isbn AND CODE=:code) " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (ISBN, CODE, NUM) VALUES (:isbn, :code, :num)",
            nativeQuery = true
    )
    void insertStocks(
            @Param("isbn") int isbn,
            @Param("code") String code,
            @Param("num") int num
    );

    // 4. PUBLISHED_BY INSERT
    @Modifying
    @Transactional
    @Query(
            value = "MERGE INTO PUBLISHED_BY USING DUAL " +
                    "ON (NAME =:publisherName AND ISBN=:isbn) " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (NAME, ISBN) VALUES (:publisherName, :isbn)",
            nativeQuery = true
    )
    void insertPublishedBy(
            @Param("publisherName") String publisherName,
            @Param("isbn") int isbn
    );

    // 2-(e)
    // 전체 도서의 평균 가격을 출력하는 쿼리
    @Query(
            value = "SELECT AVG(PRICE) as avgPrice FROM BOOK",
            nativeQuery = true
    ) List<AvgPriceBookInterface> avgPriceBook();

    interface AvgPriceBookInterface {
        float getAvgPrice();
    }

    // 도서의 년도별 평균 가격을 출력하는 쿼리
    @Query(
            value = "SELECT YEAR, AVG(PRICE) AS avgPrice FROM BOOK GROUP BY YEAR",
            nativeQuery = true
    ) List<AvgPriceBookByYearInterface> avgPriceBookByYear();

    interface AvgPriceBookByYearInterface {
        int getYear();
        float getAvgPrice();
    }

    // 2-(f)
    // 작가별 도서의 총 개수, 최고, 최저, 평균 가격을 확인하는 쿼리
    // 작가의 이름으로 GROUP BY
    @Query(
            value = "SELECT NAME, COUNT(ISBN) AS countBook, MAX(PRICE) AS maxPrice, MIN(PRICE) AS minPrice, AVG(PRICE) AS avgPrice " +
                    "FROM WRITTEN_BY NATURAL JOIN BOOK " +
                    "GROUP BY NAME ",
            nativeQuery = true
    ) List<BookPriceByNameInterface> bookPriceByName();

    interface BookPriceByNameInterface {
        String getName();
        int getCountBook();
        int getMaxPrice();
        int getMinPrice();
        double getAvgPrice();
    }
}
