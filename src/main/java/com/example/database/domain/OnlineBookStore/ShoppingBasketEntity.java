package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="SHOPPING_BASKET")
@Getter
@Setter
@Builder
public class ShoppingBasketEntity {
    @Id
    @Column(name="BASKETID")
    private String basketid;
}
