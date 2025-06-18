package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.WrittenByEntity;
import com.example.database.repository.OnlineBookStore.WrittenByRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrittenByService {
    // 2-(a)
    @Autowired
    private WrittenByRepository writtenByRepository;

    public List<WrittenByEntity> findAll() { return writtenByRepository.findAll(); }

    public List<WrittenByRepository.SearchBookInterface> searchBook(String name) {
        return writtenByRepository.searchBook(name);
    }

        public WrittenByEntity save(WrittenByEntity writtenByEntity) {
            writtenByRepository.saveOne(
                writtenByEntity.getName(),
                writtenByEntity.getAddress(),
                writtenByEntity.getIsbn()
        );
        return writtenByEntity;
    }
}
