package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.WarehouseEntity;
import com.example.database.repository.OnlineBookStore.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<WarehouseEntity> findAll() { return warehouseRepository.findAll(); }

    public WarehouseEntity save(WarehouseEntity warehouseEntity) {
        warehouseRepository.saveOne(
                warehouseEntity.getCode(),
                warehouseEntity.getAddress(),
                warehouseEntity.getPhone()
        );
        return warehouseEntity;
    }
}
