package com.db.spock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.tngtech.jgiven.junit5.SimpleScenarioTest;

@SpringBootTest
class ApplicationTests extends SimpleScenarioTest<ApplicationTests.Stages> {
 
    static class Stages {
 
        public Stages when_sistema_sobe() {
            return this;
        }
        
        //given
        //when
        //then
 
        public Stages then_contexto_deve_carregar() {
            assert true;
            return this;
        }
    }
 
    @Test
    void contexto_spring() {
        given().when_sistema_sobe()
             .then_contexto_deve_carregar();
        }
}