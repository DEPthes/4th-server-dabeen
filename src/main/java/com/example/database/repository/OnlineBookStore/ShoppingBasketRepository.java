package com.example.database.repository.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.ShoppingBasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasketEntity, String> {
    @Override
    @Query(
            value = "SELECT * FROM SHOPPING_BASKET",
            nativeQuery = true
    )
    List<ShoppingBasketEntity> findAll();

}
