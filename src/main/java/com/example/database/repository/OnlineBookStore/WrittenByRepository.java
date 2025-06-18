package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.WrittenByEntity;
import com.example.database.domain.OnlineBookStore.WrittenByID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WrittenByRepository extends JpaRepository<WrittenByEntity, WrittenByID> {
    // 2-(a)
    // 작가의 이름으로 검색
    // 작가가 작성한 도서의 제목, 해당 도서의 재고 수량을 출력하는 쿼리
    @Override
    @Query(
            value = "SELECT * FROM WRITTEN_BY",
            nativeQuery = true
    )
    List<WrittenByEntity> findAll();

    @Query(
            value = """
                    SELECT wb.NAME, b.TITLE, SUM(s.NUM) AS stockNum
                    FROM WRITTEN_BY wb
                    JOIN BOOK b ON wb.ISBN = b.ISBN
                    LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
                    WHERE wb.NAME = :NAME
                    GROUP BY wb.NAME, b.TITLE
                    """,
            nativeQuery = true
    )
    List<SearchBookInterface> searchBook(@Param("NAME") String name);

    interface SearchBookInterface {
        String getName();
        String getTitle();
        Integer getStockNum();
    }

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO WRITTEN_BY(NAME, ADDRESS, ISBN) VALUES (:name, :address, :isbn)",
            nativeQuery = true
    )
    void saveOne(
            @Param("name") String name,
            @Param("address") String address,
            @Param("isbn") int isbn
    );
}
