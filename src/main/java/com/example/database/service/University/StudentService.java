package com.example.database.service.University;

import com.example.database.domain.University.StudentEntity;
import com.example.database.repository.University.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }
    public long count() { return studentRepository.count(); }

    public StudentEntity save(StudentEntity studentEntity) {
        studentRepository.saveOne(
                studentEntity.getSno(),
                studentEntity.getPno(),
                studentEntity.getSname(),
                studentEntity.getYear(),
                studentEntity.getDept()
        );
        return studentEntity;
    }

}
