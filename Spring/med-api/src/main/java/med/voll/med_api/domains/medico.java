package med.voll.med_api.domains;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med_api.domains.enums.Especialidade;
import med.voll.med_api.dto.DadosAtualizadosMedicos;
import med.voll.med_api.dto.DadosCadastro;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // 
public class medico {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // 
    private Endereco endereco;

    public medico(DadosCadastro dados) {
        super();
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInformacoes(DadosAtualizadosMedicos dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.telefone() != null) {
            this.telefone = dados.telefone();   
        }

        if(dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
}
