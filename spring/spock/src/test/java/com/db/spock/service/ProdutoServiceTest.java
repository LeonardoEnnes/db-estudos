package com.db.spock.service;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import com.tngtech.jgiven.junit5.SimpleScenarioTest;
 
public class ProdutoServiceTest extends SimpleScenarioTest<ProdutoServiceTest.Stages> {
	  
     public static class Stages {
 
        ProdutoService service;
        Double total;
        List<String> nomes;
 
        // GIVEN
        public Stages given_umProdutto() {
            service = new ProdutoService();
            service.init();
            return this;
        }
 
        // WHEN
        public Stages when_eu_calculo_o_total() {
            total = service.total();
            return this;
        }
 
        public Stages when_eu_busco_os_nomes_filtrados() {
            nomes = service.streamNomes();
            return this;
        }
 
        // THEN
        public Stages then_o_total_deve_ser(Double esperado) {
            assert total.equals(esperado);
            return this;
        }
 
        public Stages then_deve_retornar_dois_nomes() {
            assert nomes.size() == 2;
            return this;
        }
 
        public Stages deve_conter_nomes_corretos() {
            assert nomes.contains("micro hp");
            assert nomes.contains("micro del");
            return this;
        }
    }
 
 
    @Test
     public void deve_calcular_total() {
    	  given().given_umProdutto().
    	  when_eu_calculo_o_total().
          then_o_total_deve_ser(14500.0);
     	
       }
 
 
    @Test
    void teste_nomes() {
        given().given_umProdutto()
        .when_eu_busco_os_nomes_filtrados()
        .then_deve_retornar_dois_nomes();
    }
 
}