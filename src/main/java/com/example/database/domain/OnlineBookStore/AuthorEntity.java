package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="AUTHOR")
@Getter
@Setter
@Builder
@IdClass(AuthorID.class)
public class AuthorEntity {
    @Id
    @Column(name="NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="URL")
    private String url;
}
