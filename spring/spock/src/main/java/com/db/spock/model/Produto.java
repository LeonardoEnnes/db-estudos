package com.db.spock.model;

import java.util.UUID;

public class Produto {
    private String id;
    private String nomeProduto;
    private TypeProduto produto;
    private Integer quantidade;
    private Double preco;

    public Produto(String id, String nomeProduto, TypeProduto produto, Double preco, Integer quantidade) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public Produto() {
		this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString(){
        return "Produto [id=" + id + ", nomeProduto=" + nomeProduto + ", produto=" + produto + ", preco=" + preco + ", quantidade=" + quantidade + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public TypeProduto getProduto() {
        return produto;
    }
    
    public void setProduto(TypeProduto produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
