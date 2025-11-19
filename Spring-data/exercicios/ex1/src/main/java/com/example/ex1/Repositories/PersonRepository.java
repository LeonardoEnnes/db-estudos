package com.example.ex1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ex1.Entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
