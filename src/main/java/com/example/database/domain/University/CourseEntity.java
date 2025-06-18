package com.example.database.domain.University;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Course")
@Data
@NoArgsConstructor
public class CourseEntity {
    @Id
    @Column(name = "cno")
    private String cno;

    @Column(name = "cname")
    private String cname;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "sessions")
    private Integer sessions;
}
