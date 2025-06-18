package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="BASKET_OF")
@Getter
@Setter
@Builder
@IdClass(BasketOfID.class)
public class BasketOfEntity {
    @Id
    @Column(name="EMAIL")
    private String email;

    @Id
    @Column(name="BASKETID")
    private String basketid;
}
