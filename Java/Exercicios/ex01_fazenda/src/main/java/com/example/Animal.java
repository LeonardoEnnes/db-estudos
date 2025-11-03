package com.example;

public abstract class Animal {
    private String name;
    private int idade;
    static int totalAnimais = 0;
    
    public Animal(String name, Integer idade) {
        this.name = name;
        this.idade =idade;
        totalAnimais++;
    }

    // getters
    public String getName(){
        return this.name;
    }

    public int getIdade(){
        return this.idade;
    }
}
