package med.voll.med_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.med_api.domains.enums.Especialidade;
import med.voll.med_api.dto.endereco.DadosEndereco;

public record DadosCadastro(
    
    @NotBlank
    String nome, 

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}") // expressão regular para validar o formato do CRM (apenas dígitos, com comprimento entre 4 e 6 caracteres)
    String crm,

    @NotNull
    Especialidade especialidade,

    @NotNull
    @Valid // para validar os campos do endereço usando as anotações de validação presentes na classe DadosEndereco
    DadosEndereco endereco // ta em outro record
) {

}
