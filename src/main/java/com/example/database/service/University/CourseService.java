package com.example.database.service.University;

import com.example.database.domain.University.CourseEntity;
import com.example.database.repository.University.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    public long count() { return courseRepository.count(); }

    public CourseEntity save(CourseEntity courseEntity) {
        courseRepository.saveOne(
                courseEntity.getCno(),
                courseEntity.getCname(),
                courseEntity.getCredit(),
                courseEntity.getSessions()
        );
        return courseEntity;
    }
}
