package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="BOOK")
@Getter
@Setter
@Builder
public class BookEntity {
    @Id
    @Column(name="ISBN")
    private int isbn;

    @Column(name="TITLE")
    private String title;

    @Column(name="YEAR")
    private int year;

    @Column(name="PRICE")
    private int price;
}
