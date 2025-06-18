package com.example.database.service.University;

import com.example.database.domain.University.LectureEntity;
import com.example.database.repository.University.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    public List<LectureEntity> findAll() { return lectureRepository.findAll();}
    public long count() { return lectureRepository.count(); }

    public LectureEntity save(LectureEntity lectureEntity) {
        lectureRepository.saveOne(
                lectureEntity.getCno(),
                lectureEntity.getPno(),
                lectureEntity.getLec_time(),
                lectureEntity.getRoom()
        );
        return lectureEntity;
    }
}
