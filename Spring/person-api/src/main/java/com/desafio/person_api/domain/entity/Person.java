package com.desafio.person_api.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "person")
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // gera os métodos equals e hashCode usando apenas o campo id
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // cara deixei como string

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

}
