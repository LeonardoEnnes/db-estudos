package com.example.aula01.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.aula01.Models.Customer;
import com.example.aula01.Repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CostomerService {
    private final CustomerRepository costomerRepository;

    public CostomerService(CustomerRepository costomerRepository) {
        this.costomerRepository = costomerRepository;
    }

    @Transactional // pq ?
    public Customer saveCostomer(Customer customer) {
        return costomerRepository.save(customer);
    }

    @Transactional
    public List<Customer> findAllCustomers() {
        return (List<Customer>) costomerRepository.findAll();
    }

     @Transactional
    public Optional<Customer> findCustomerById(Long id) {
        return costomerRepository.findById(id);
    }

    @Transactional
    public void deleteCostomerById(Long id) {
        costomerRepository.deleteById(id);
    }
}
