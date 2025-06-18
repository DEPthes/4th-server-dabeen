package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="PUBLISHER")
@Getter
@Setter
@Builder
public class PublisherEntity {
    @Id
    @Column(name="NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE")
    private String phone;

    @Column(name="URL")
    private String url;
}
