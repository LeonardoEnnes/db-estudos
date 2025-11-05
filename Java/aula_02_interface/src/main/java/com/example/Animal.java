package com.example;

public abstract class Animal implements Talkable{ // lembrando que abstract impede a instanciação direta
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
