package com.example;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Method;
import java.util.function.Function;
import org.junit.Test;


public class UpperTest 
{
    @Test
    public void sampleTests() {
        doTest("hello", "HELLO");
        doTest("hello world", "HELLO WORLD");
        doTest("hello world !", "HELLO WORLD !");
        doTest("heLlO wORLd !", "HELLO WORLD !");
        doTest("1,2,3 hello world!", "1,2,3 HELLO WORLD!");
    }

    private static void doTest(String input, String expected) {
        String message = String.format("for string: \"%s\"\n", input);
        String actual = makeUpperCase.apply(input);
        assertEquals(message, expected, actual); // syntax error solved: tests in junit must use assertEquals(message, expected, actual)
    }

    private static final Function<String, String> makeUpperCase;
    static { 
        Method method = null;
        try { 
            method = Upper.class.getDeclaredMethod("makeUpperCase", String.class);
        } catch (NoSuchMethodException ignore) { // new solutions
            try {
                method = Upper.class.getDeclaredMethod("MakeUpperCase", String.class);
            } catch (NoSuchMethodException ignoreToo) {}
        }
        final Method finalMethod = method;
        makeUpperCase = (String input) -> {
            try {
                return (String) finalMethod.invoke(null, input);
            } catch (Exception cause) {
                throw new RuntimeException(cause);
            }
        };
    } 

}
