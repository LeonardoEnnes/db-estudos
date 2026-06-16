package med.voll.med_api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.med_api.dto.endereco.DadosEndereco;

public record DadosAtualizadosMedicos(
    @NotNull
    Long id, 
    String nome, 
    String telefone, 
    DadosEndereco endereco) // usando dto de endereco 
{ }