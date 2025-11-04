package com.example;

/**
 * Hello world!
 *
 */
public class Upper 
{
    public static void main( String[] args )
    {
        String example = "hello world!";
        String upperCase = makeUpperCase(example);
        System.out.println( upperCase );
    }

    public static String makeUpperCase(String str){
        return str.toUpperCase();
    }
}
