package com.desafio.person_api.dto;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.validator.constraints.br.CPF;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRequestDto(
    @Schema(example = "Fulano", description = "Nome completo da pessoa")
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @Schema(example = "1999-05-29", description = "Data de nascimento no formato YYYY-MM-DD")
    @NotNull(message = "A data de nascimento é obrigatória")
    LocalDate dataNascimento,

    @Schema(example = "12345678909", description = "CPF único e válido (apenas números)")
    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "O CPF deve estar no formato 000.000.000-00")
    String cpf,

    @Valid
    @NotNull(message = "A lista de endereços é obrigatória")
    List<AddressDto> addresses

) 
{ }
