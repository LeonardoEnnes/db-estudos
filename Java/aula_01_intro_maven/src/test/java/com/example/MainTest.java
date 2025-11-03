package com.example;

import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        int expected = 19;
        int actual = Main.add(10, 9);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAnswerWithTrue2()
    {
        int expected = -6;
        int actual = Main.add(-10, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAnswerWithTrue3()
    {
        int expected = 8;
        int actual = Main.add(15, -7);
        assertEquals(expected, actual);
    }

}
