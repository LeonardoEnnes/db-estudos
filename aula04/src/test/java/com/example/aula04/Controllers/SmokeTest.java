package com.example.aula04.Controllers;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private HomeController controller;

    @Test
    void contextLoad() throws Exception{
        assertThat(controller).isNotNull();
    }

    // skipando testes
    @Test
    @Disabled("This test is disabled")
    void fakeContextLoad() {
        assertThat(controller).isNull();
    }
}
