package com.example.database.service.OnlineBookStore;

import com.example.database.repository.OnlineBookStore.ShoppingBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingBasketService {
    @Autowired
    private ShoppingBasketRepository shoppingBasketRepository;

}
