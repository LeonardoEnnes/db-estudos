package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat; // adicionado por fora
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Controllers.HomeController;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
