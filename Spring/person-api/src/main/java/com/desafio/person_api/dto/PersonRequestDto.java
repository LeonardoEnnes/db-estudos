package com.desafio.person_api.dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRequestDto(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotNull(message = "A data de nascimento é obrigatória")
    LocalDate dataNascimento,

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "O CPF deve estar no formato 000.000.000-00")
    String cpf,

    @Valid
    @NotNull(message = "A lista de endereços é obrigatória")
    List<AddressDto> addresses

) 
{ }
