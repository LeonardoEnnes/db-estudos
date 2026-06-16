package med.voll.med_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import med.voll.med_api.domains.medico;
import med.voll.med_api.dto.DadosAtualizadosMedicos;
import med.voll.med_api.dto.DadosCadastro;
import med.voll.med_api.dto.DadosListagemMedico;
import med.voll.med_api.repository.medicoRepository;
// import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/medicos")
public class medicoController {
    
    @Autowired
    private medicoRepository medicoRepository;

    @PostMapping("")
    @Transactional // para garantir que a operação de salvar o médico seja realizada dentro de uma transação, garantindo a integridade dos dados no banco de dados.
    public void cadatrarMedico(@RequestBody @Valid DadosCadastro dados) { // o valid foi adicionado para garantir que os dados recebidos sejam validados de acordo com as anotações de validação presentes na classe DadosCadastro. Se os dados não atenderem aos critérios de validação, uma resposta de erro será retornada automaticamente.
        // System.out.println(dados);
        medicoRepository.save(new medico(dados));
    }

    // 
    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) { // o Pageable é um objeto que encapsula informações sobre a paginação, como o número da página, o tamanho da página e os critérios de ordenação. O @PageableDefault é usado para definir valores padrão para a paginação, como o tamanho da página e o campo de ordenação.
        return medicoRepository
            .findAll(paginacao)
            // .stream() nao é necessário porque o método findAll(paginacao) já retorna um objeto do tipo Page, que é uma coleção paginada. O método map() pode ser aplicado diretamente ao objeto Page para transformar cada elemento em um objeto do tipo DadosListagemMedico.
            .map(DadosListagemMedico::new); // nao precisa do .stream() porque o método findAll(paginacao) já retorna um objeto do tipo Page, que é uma coleção paginada. O método map() pode ser aplicado diretamente ao objeto Page para transformar cada elemento em um objeto do tipo DadosListagemMedico.
            // .toList();
    }

    @PutMapping
    @Transactional // para garantir que a operação de atualizar o médico seja realizada
    public void atualizar(@RequestBody @Valid DadosAtualizadosMedicos dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
}
