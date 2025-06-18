package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.BasketOfEntity;
import com.example.database.repository.OnlineBookStore.BasketOfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketOfService {
    @Autowired
    private BasketOfRepository basketOfRepository;

    public List<BasketOfEntity> findAll() { return basketOfRepository.findAll();}

    public BasketOfEntity save(BasketOfEntity basketOfEntity) {
        basketOfRepository.saveOne(
                basketOfEntity.getEmail(),
                basketOfEntity.getBasketid()
        );
        return basketOfEntity;
    }
}
