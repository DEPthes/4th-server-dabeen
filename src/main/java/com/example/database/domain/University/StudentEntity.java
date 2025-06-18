package com.example.database.domain.University;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
public class StudentEntity {
    @Id
    @Column(name = "sno")
    private String sno;

    @Column(name = "pno")
    private String pno;

    @Column(name = "sname")
    private String sname;

    @Column(name = "year")
    private Integer year;

    @Column(name = "dept")
    private String dept;

    @ManyToOne
    @JoinColumn(name = "pno", referencedColumnName = "pno", insertable = false, updatable = false)
    private ProfessorEntity professor;

    // Convenience methods for getting numeric values
    public int getSnoAsInt() {
        return Integer.parseInt(sno);
    }

    public int getPnoAsInt() {
        return Integer.parseInt(pno);
    }

    public int getYearAsInt() {
        return year;
    }
}
