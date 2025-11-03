package com.example;

public class FarmApp {
    public static void main( String[] args ) {
        System.out.println( "Welcome to the Farm App!" );

        Animal[] as = {
            new Cow("Mimi", 5),
            new Horse("Priscilo", 10),
            new Dog("bob", 1)
        };

        for (Animal a : as) {
            System.out.println(a.getName() + " - Idade: " + a.getIdade());
        }

        System.out.println("Total de animais na fazenda: " + Animal.totalAnimais);
    }
}
