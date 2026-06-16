package com.desafio.person_api.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "address")
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") 
public class Address {
 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // pensar se precisa de uuid ou se pode ser um id numérico
    private String id;
    
    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private boolean principal;

    @ManyToOne(fetch = FetchType.LAZY) // para evitar carregamento desnecessário da pessoa quando buscamos um endereço
    @JoinColumn(name = "person_id") 
    private Person person; 
}
