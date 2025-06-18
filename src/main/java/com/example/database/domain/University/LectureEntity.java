package com.example.database.domain.University;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "LECTURE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LectureID.class)
public class LectureEntity {
    @Id
    @Column(name = "CNO", nullable = false)
    private String cno;

    @Id
    @Column(name = "PNO", nullable = false)
    private String pno;

    @Column(name = "LEC_TIME", nullable = false)
    private String lec_time;

    @Column(name = "ROOM", nullable = false)
    private String room;

    // Convenience method for getting numeric value
    public int getPnoAsInt() {
        return Integer.parseInt(pno);
    }
}
