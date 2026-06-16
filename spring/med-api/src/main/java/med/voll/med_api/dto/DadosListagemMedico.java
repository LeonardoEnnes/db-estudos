package med.voll.med_api.dto;

import med.voll.med_api.domains.enums.Especialidade;
import med.voll.med_api.domains.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}