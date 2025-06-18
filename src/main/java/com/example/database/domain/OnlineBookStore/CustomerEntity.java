package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="CUSTOMER")
@Getter
@Setter
@Builder
public class CustomerEntity {
    @Id
    @Column(name="EMAIL")
    private String email;

    @Column(name="NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE")
    private String phone;
}
