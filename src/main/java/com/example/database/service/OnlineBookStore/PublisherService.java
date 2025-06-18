package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.PublisherEntity;
import com.example.database.repository.OnlineBookStore.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<PublisherEntity> findAll() { return publisherRepository.findAll(); }

    public PublisherEntity save(PublisherEntity publisherEntity) {
        publisherRepository.saveOne(
                publisherEntity.getName(),
                publisherEntity.getAddress(),
                publisherEntity.getPhone(),
                publisherEntity.getUrl()
        );
        return publisherEntity;
    }
}
