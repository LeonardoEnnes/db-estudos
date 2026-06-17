package com.db.spock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.db.spock.model.Produto;
import com.db.spock.model.TypeProduto;

 
@Service
public class ProdutoService {
 
	private  List<Produto> produtos=new ArrayList<>();

	public void init() {
	produtos =	List.of(new Produto("123","micro lenovo",TypeProduto.COMPUTADOR,4000.,2),
				  new Produto("456","micro hp",TypeProduto.COMPUTADOR,6000.,2),
				 new Produto("789","micro del",TypeProduto.COMPUTADOR,4500.,3)
				);		
	}

	public ProdutoService() {
		this.init();
	}

	public void adicionarProduto(Produto produto) {
		   this.produtos.add(produto);
	}

	public List<Produto> getProdutos(){
		return this.produtos;
	}

	public Double total(){
		return produtos.stream().mapToDouble(produto-> produto.getPreco()).sum();
	}

	public List<String> streamNomes() {
		Supplier<String> nomes= () ->  produtos.stream().map(prod->prod.getNomeProduto())
				.findFirst()
                .orElse("");
		return  produtos.
				 stream().filter(prod1->prod1.getPreco()>=4500.)
				 .map(prod-> nomes.get())
				 .collect(Collectors.toList());
	}

    public List<Produto> buscaProdutosCaros() {
        return produtos.stream()
            .filter(prod -> prod.getPreco() > 5000.0) // coloquei 5k como muito caro
            .toList();
    }

    public List<Produto> buscaComputadores() {
        return produtos.stream()
            .filter(prod -> prod.getProduto() == TypeProduto.COMPUTADOR)
            .toList();
    }

    public List<Produto> buscaEstoqueBaixo() {
        return produtos.stream()
            .filter(prod -> prod.getQuantidade() <= 3)
            .toList();
    }

    public List<Produto> buscaComputadoresCaros() {
        return produtos.stream()
            .filter(prod -> prod.getProduto() == TypeProduto.COMPUTADOR)
            .filter(prod -> prod.getPreco() > 5000.0)
            .toList();
    }

    public List<Produto> buscaNomes (String palavra) {
        return produtos.stream()
            .filter(prod -> prod.getNomeProduto().toLowerCase().contains(palavra.toLowerCase()))
            .toList();
    }

    public List<String> pegaApenasNomes() {
        return produtos.stream()
            .map(Produto::getNomeProduto)
            .toList();
    }

    public List<Double> pegaApenasPrecos () {
        return produtos.stream()
            .map(Produto::getPreco)
            .toList();
    }

    public List<String> pegarApenasNomesMaiusculos() {
        return produtos.stream()
            .map(prod -> prod.getNomeProduto().toUpperCase())
            .toList();
    }

    public List<Double> obterValorTotalPorProduto() {
        return produtos.stream()
            .map(prod -> prod.getPreco() + prod.getQuantidade())
            .toList();
    }

    public List<String> gerarRelatorioProduto() {
        return produtos.stream()
            .map(prod -> prod.getNomeProduto() + "Custa: " + prod.getPreco())
            .toList();
    }

    public boolean existeCelular() {
        return produtos.stream()
            .anyMatch(prod -> prod.getProduto() == TypeProduto.CELULAR);
    }

    public boolean todosEmEstoque() {
        return produtos.stream()
            .allMatch(prod -> prod.getQuantidade() > 0);
    }

    public boolean nenhumProdutoMuitoCaro (){
        return produtos.stream()
            .noneMatch(prod -> prod.getPreco() > 10000.0);
    }

    public Produto buscarPrimeiroProduto() {
        return produtos.stream()
            .findFirst()
            .orElse(null);//
    }

    public Produto buscarQualquerComputador() {
        return produtos.stream()
                .filter(p -> p.getProduto() == TypeProduto.COMPUTADOR)
                .findAny()
                .orElseThrow(() -> new RuntimeException("nenhum computador foi encontrado"));
    }

    public List<Produto> ordenarPorPrecoCrescente() { // do mais barato para o mais caro
        return produtos.stream()
            .sorted((prod1,prod2)-> prod1.getPreco()
                .compareTo(prod2.getPreco())) 
            .toList();
    }

    public List<Produto> ordenarPorPrecoDecrescente () { // do mais caro pro mais barato
        return produtos.stream()
            .sorted((prod1, prod2) -> prod2.getPreco()
                .compareTo(prod1.getPreco()))
            .toList();
    }

    public List<Produto> obterOsDoisMaisCaros () {
        return ordenarPorPrecoDecrescente()
            .stream()
            .limit(2)
            .toList();
    }

    public List<Produto> pularPrimeiroProduto () {
        return produtos.stream()
            .skip(1)
            .toList();
    }

    public List<TypeProduto> obterTiposUnicos() {
        return produtos.stream()
                .map(Produto::getProduto)
                .distinct()
                .toList();
    }

    public Double calcularValorTotalEstoque() {
        return produtos.stream()
                .map(prod -> prod.getPreco() * prod.getQuantidade())
                .reduce(0.0, Double::sum);
    }

    public Produto buscarProdutoMaisCaro() {
        return produtos.stream()
                .max((prod1, prod2) -> prod1.getPreco().compareTo(prod2.getPreco()))
                .orElse(null);
    }

    public java.util.Map<TypeProduto, List<Produto>> agruparPorTipo() {
        return produtos.stream()
                .collect(Collectors.groupingBy(Produto::getProduto));
    }

    public java.util.Map<TypeProduto, Long> contarQuantidadePorTipo() {
        return produtos.stream()
                .collect(Collectors.groupingBy(Produto::getProduto, Collectors.counting()));
    }

    public String gerarStringComNomes() {
        return produtos.stream()
                .map(Produto::getNomeProduto)
                .collect(Collectors.joining(", "));
    }
}