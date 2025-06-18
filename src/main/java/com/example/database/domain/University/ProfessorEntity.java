package com.example.database.domain.University;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Professor")
@Data
@NoArgsConstructor
public class ProfessorEntity {
    @Id
    @Column(name = "pno")
    private String pno;

    @Column(name = "pname")
    private String pname;

    @Column(name = "pmajor")
    private String pmajor;

    @Column(name = "pdept")
    private String pdept;

    // Convenience method for getting numeric value
    public int getPnoAsInt() {
        return Integer.parseInt(pno);
    }
}
