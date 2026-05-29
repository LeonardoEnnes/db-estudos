package com.desafio.person_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(
    String id,
    @NotBlank(message = "A rua é obrigatória")
    String rua,

    @NotBlank(message = "O número é obrigatório")
    String numero,

    @NotBlank(message = "O bairro é obrigatório")
    String bairro,

    @NotBlank(message = "A cidade é obrigatória")
    String cidade,

    @NotBlank(message = "O estado é obrigatório")
    String estado,

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 12345-678")
    String cep,

    boolean principal
) {
}