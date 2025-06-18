package com.example.database.domain.University;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureID implements Serializable {
    private String cno;
    private String pno;
}
