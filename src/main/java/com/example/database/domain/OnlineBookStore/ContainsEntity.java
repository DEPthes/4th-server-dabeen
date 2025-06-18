package com.example.database.domain.OnlineBookStore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="CONTAINS")
@Getter
@Setter
@Builder
@IdClass(ContainsID.class)
public class ContainsEntity {
    @Id
    @Column(name="BASKETID")
    private String basketid;

    @Id
    @Column(name="ISBN")
    private int isbn;

    @Column(name="NUM")
    private int num;
}
