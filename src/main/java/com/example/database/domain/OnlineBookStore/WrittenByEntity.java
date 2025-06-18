package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="WRITTEN_BY")
@Getter
@Setter
@Builder
@IdClass(WrittenByID.class)
public class WrittenByEntity {
    @Id
    @Column(name="NAME")
    private String name;

    @Id
    @Column(name="ADDRESS")
    private String address;

    @Id
    @Column(name="ISBN")
    private int isbn;
}
