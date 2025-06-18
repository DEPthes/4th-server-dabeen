package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.AuthorEntity;
import com.example.database.repository.OnlineBookStore.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorEntity> findAll() { return authorRepository.findAll(); }

    public AuthorEntity save(AuthorEntity authorEntity) {
        authorRepository.saveOne(
                authorEntity.getName(),
                authorEntity.getAddress(),
                authorEntity.getUrl()
        );
        return authorEntity;
    }

}
