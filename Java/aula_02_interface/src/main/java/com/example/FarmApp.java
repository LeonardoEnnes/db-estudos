package com.example;

import java.util.Arrays;
import java.util.List;

public class FarmApp 
{
    public static <T> void main( String[] args )
    {
        System.out.println( "Farm" );

        List<Talkable> talkables = Arrays.asList(
            new Dog("Rex"),
            new Cat("Miau"),
            new Radio()
        );


        talkables.forEach(t -> {
            System.out.println(t.talk());
        }); 
    }
}
