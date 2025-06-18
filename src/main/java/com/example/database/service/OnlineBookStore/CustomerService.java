package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.CustomerEntity;
import com.example.database.repository.OnlineBookStore.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    // 2-(c)
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> findAll() { return customerRepository.findAll();}

    public List<CustomerRepository.SearchCustomerInterface> searchCustomer(String email) {
        return customerRepository.searchCustomer(email);}

    public CustomerEntity save(CustomerEntity customerEntity) {
        customerRepository.saveOne(
                customerEntity.getEmail(),
                customerEntity.getName(),
                customerEntity.getAddress(),
                customerEntity.getPhone()
        );
        return customerEntity;
    }
}
