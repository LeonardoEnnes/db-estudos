package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AnimalTest 
{
    @Test
    public void ifCanCreateCowCorrectly()
    {
        Animal mimi = new Cow("Mimi", 4);
        
        assertEquals("Mimi", mimi.getName());
        assertEquals(4, mimi.getIdade());
    }

    @Test
    public void ifCanCreateHorseCorrectly()
    {
        Animal priscilo = new Horse("Priscilo", 7);

        assertEquals("Priscilo", priscilo.getName());
        assertEquals(7, priscilo.getIdade());
    }

    @Test
    public void ifCanCreateDogCorrectly(){
        Animal bob = new Dog("Bob", 4);

        assertEquals("Bob", bob.getName());
        assertEquals(4, bob.getIdade());
    }
}
