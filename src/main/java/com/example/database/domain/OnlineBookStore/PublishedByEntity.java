package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="PUBLISHED_BY")
@Getter
@Setter
@Builder
@IdClass(PublishedByID.class)
public class PublishedByEntity {
    @Id
    @Column(name="NAME")
    private String name;

    @Id
    @Column(name="ISBN")
    private int isbn;
}
