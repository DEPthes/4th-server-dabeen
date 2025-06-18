package com.example.database.service.University;

import com.example.database.domain.University.ProfessorEntity;
import com.example.database.repository.University.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorEntity> findAll() {
        return professorRepository.findAll();
    }
    public long count() { return professorRepository.count(); }

    public ProfessorEntity save(ProfessorEntity professorEntity) {
        professorRepository.saveOne(
                professorEntity.getPno(),
                professorEntity.getPname(),
                professorEntity.getPmajor(),
                professorEntity.getPdept()
        );
        return professorEntity;
    }
}
