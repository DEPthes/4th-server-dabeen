package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="WAREHOUSE")
@Getter
@Setter
@Builder
public class WarehouseEntity {
    @Id
    @Column(name="CODE")
    private String code;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE")
    private String phone;
}
