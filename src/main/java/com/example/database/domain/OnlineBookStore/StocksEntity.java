package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="STOCKS")
@Getter
@Setter
@Builder
@IdClass(StocksID.class)
public class StocksEntity {
    @Id
    @Column(name="ISBN")
    private int isbn;

    @Id
    @Column(name="code")
    private String code;

    @Column(name="num")
    private int num;
}
