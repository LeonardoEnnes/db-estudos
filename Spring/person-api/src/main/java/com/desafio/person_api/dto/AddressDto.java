package com.desafio.person_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(
    String id,

    @Schema(example = "Rua das Flores")
    @NotBlank(message = "A rua é obrigatória")
    String rua,

    @Schema(example = "123")
    @NotBlank(message = "O número é obrigatório")
    String numero,

    @Schema(example = "Centro")
    @NotBlank(message = "O bairro é obrigatório")
    String bairro,

    @Schema(example = "Porto")
    @NotBlank(message = "A cidade é obrigatória")
    String cidade,

    @Schema(example = "RJ")
    @NotBlank(message = "O estado é obrigatório")
    String estado,

    @Schema(example = "89545-000", description = "CEP no formato 12345-678")
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 12345-678")
    String cep,

    @Schema(example = "true")
    boolean principal
) {
}