package com.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        in.close();

        int x = add(a, b);
        System.out.println("Sum: " + x);
        System.out.println(x);
        System.out.println();
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
