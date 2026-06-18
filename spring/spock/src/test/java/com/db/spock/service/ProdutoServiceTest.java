package com.db.spock.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.db.spock.model.Produto;
import com.db.spock.model.TypeProduto;
import com.tngtech.jgiven.junit5.SimpleScenarioTest;
 
public class ProdutoServiceTest extends SimpleScenarioTest<ProdutoServiceTest.Stages> {
	  
     public static class Stages {
 
        ProdutoService service;
        Double total;
        List<String> nomes;

        List<Produto> listaProdutoResult;
        List<Double> listaDoublesResult;
        Produto produtoResult;
        boolean booleanResult;
        String stringResult;
        Map<TypeProduto, List<Produto>> mapaAgrupadoResult;
        Map<TypeProduto, Long> mapaContagemResult;
 
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

        public Stages when_busco_produtos_caros () {
            listaProdutoResult = service.buscaProdutosCaros();
            return this;
        }

        public Stages when_busco_computadores () {
            listaProdutoResult = service.buscaComputadores();
            return this;
        }

        public Stages when_pego_apenas_nomes () {
            nomes = service.pegaApenasNomes();
            return this;
        }

        public Stages when_obtenho_valor_total_por_produto() {
            listaDoublesResult = service.obterValorTotalPorProduto();
            return this;
        }

        public Stages when_verifico_se_existe_celular() {
            booleanResult = service.existeCelular();
            return this;
        }

        public Stages when_verifico_se_todos_estao_em_estoque() {
            booleanResult = service.todosEmEstoque();
            return this;
        }

        public Stages when_agrupo_por_tipo() {
            mapaAgrupadoResult = service.agruparPorTipo();
            return this;
        }

        public Stages when_calculo_total_do_estoque() {
            total = service.calcularValorTotalEstoque();
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

        public Stages then_a_lista_de_produtos_deve_ter_tamanho(int tamanhoEsperado) {
            assertEquals(tamanhoEsperado, listaProdutoResult.size());
            return this;
        }

        public Stages then_a_lista_de_strings_deve_ter_tamanho(int tamanhoEsperado) {
            assertEquals(tamanhoEsperado, nomes.size());
            return this;
        }

        public Stages then_o_resultado_booleano_deve_ser(boolean esperado) {
            assertEquals(esperado, booleanResult);
            return this;
        }

        public Stages then_o_primeiro_valor_double_deve_ser(Double esperado) {
            assertEquals(esperado, listaDoublesResult.get(0));
            return this;
        }

        public Stages then_o_mapa_deve_conter_a_chave(TypeProduto chave) {
            assertTrue(mapaAgrupadoResult.containsKey(chave));
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
    
    @Test
    void deve_filtrar_produtos_caros_acima_de_5000() {
        given().given_umProdutto()
        .when_busco_produtos_caros()
        .then_a_lista_de_produtos_deve_ter_tamanho(1);
    }

    @Test
    void deve_retornar_todos_como_computadores() {
        given().given_umProdutto()
        .when_busco_computadores()
        .then_a_lista_de_produtos_deve_ter_tamanho(3);
    }

    @Test
    void deve_extrair_apenas_nomes_dos_produtos() {
        given().given_umProdutto()
        .when_pego_apenas_nomes()
        .then_a_lista_de_strings_deve_ter_tamanho(3);
    }

    @Test
    void nao_deve_existir_celular_na_lista_inicial() {
        given().given_umProdutto()
        .when_verifico_se_existe_celular()
        .then_o_resultado_booleano_deve_ser(false);
    }

    @Test
    void todos_os_produtos_devem_ter_estoque_positivo() {
        given().given_umProdutto()
        .when_verifico_se_todos_estao_em_estoque()
        .then_o_resultado_booleano_deve_ser(true);
    }

    @Test
    void deve_agrupar_produtos_corretamente_por_tipo() {
        given().given_umProdutto()
        .when_agrupo_por_tipo()
        .then_o_mapa_deve_conter_a_chave(TypeProduto.COMPUTADOR);
    }

    @Test
    void deve_calcular_valor_total_do_estoque() {
        // (4000*2) + (6000*2) + (4500*3) = 8000 + 12000 + 13500 = 33500.0
        given().given_umProdutto()
        .when_calculo_total_do_estoque()
        .then_o_total_deve_ser(33500.0);
    }

    @Test
    void deve_calcular_valor_total_por_produto_individual() {
        // O primeiro produto (micro lenovo) deve ter total = 4000 * 2 = 8000.0
        given().given_umProdutto()
        .when_obtenho_valor_total_por_produto()
        .then_o_primeiro_valor_double_deve_ser(8000.0);
    }
}