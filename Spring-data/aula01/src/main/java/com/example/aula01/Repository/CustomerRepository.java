package com.example.aula01.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.aula01.Models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
