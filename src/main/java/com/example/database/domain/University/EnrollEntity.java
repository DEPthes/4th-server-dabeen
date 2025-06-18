package com.example.database.domain.University;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Enroll")
@Data
@NoArgsConstructor
@IdClass(EnrollId.class)
public class EnrollEntity {
    @Id
    @Column(name = "sno")
    private String sno;

    @Id
    @Column(name = "cno")
    private String cno;

    @Column(name = "exam")
    private Integer exam;

    @Column(name = "grade")
    private String grade;

    @ManyToOne
    @JoinColumn(name = "sno", referencedColumnName = "sno", insertable = false, updatable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "cno", referencedColumnName = "cno", insertable = false, updatable = false)
    private CourseEntity course;
}
