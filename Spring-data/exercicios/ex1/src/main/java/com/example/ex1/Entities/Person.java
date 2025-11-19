package com.example.ex1.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Person(String name) {
        this.name = name;
    }

    public Person() { }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    // setperson

    //public 
}
