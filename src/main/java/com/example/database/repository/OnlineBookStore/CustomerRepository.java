package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.CustomerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    // 2-(c)
    // 모든 고객의 정보를 출력하는 쿼리
    @Override
    @Query(
            value = "SELECT * FROM CUSTOMER",
            nativeQuery = true
    )
    List<CustomerEntity> findAll();

    // 2-(c)
    // 고객의 EMAIL로 검색
    // 해당 고객이 주문한 도서와 해당 도서의 출판사, 저자의 정보를 출력하는 쿼리
    // 도서 정보 - 도서명, 출판서 정보 - 출판사명, 저자 정보 - 저자의 이름
    @Query(
            value = "SELECT S.email AS email, " +
                    "B.TITLE AS bookName, " +
                    "P.NAME AS publisherName, " +
                    "W.NAME AS authorName " +
                    "FROM BASKET_OF S, CONTAINS C, BOOK B, PUBLISHED_BY P, WRITTEN_BY W " +
                    "WHERE S.EMAIL =:email AND S.basketid = C.basketid " +
                    "AND C.isbn = B.isbn AND B.isbn = P.isbn AND B.isbn = W.isbn",
            nativeQuery = true
    ) List<SearchCustomerInterface> searchCustomer(
            @Param("email") String email
    );
    interface SearchCustomerInterface {
        String getEmail();
        String getBookName();
        String getPublisherName();
        String getAuthorName();
    }

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO CUSTOMER(EMAIL, NAME, ADDRESS, PHONE) VALUES (:email, :name, :address, :phone)",
            nativeQuery = true
    )
    void saveOne(
            @Param("email") String email,
            @Param("name") String name,
            @Param("address") String address,
            @Param("phone") String phone
    );
}
