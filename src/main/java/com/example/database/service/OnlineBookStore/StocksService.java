package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.StocksEntity;
import com.example.database.repository.OnlineBookStore.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksService {
    @Autowired
    private StocksRepository stocksRepository;

    public List<StocksEntity> findAll() { return stocksRepository.findAll(); }

    public StocksEntity save(StocksEntity stocksEntity) {
        stocksRepository.saveOne(
                stocksEntity.getIsbn(),
                stocksEntity.getCode(),
                stocksEntity.getNum()
        );
        return stocksEntity;
    }
}
