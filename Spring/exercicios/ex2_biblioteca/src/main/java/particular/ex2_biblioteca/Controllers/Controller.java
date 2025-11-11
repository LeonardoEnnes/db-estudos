package particular.ex2_biblioteca.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import particular.ex2_biblioteca.Entities.Livro;
import particular.ex2_biblioteca.Services.AcervoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/biblioteca")
public class Controller {
    private AcervoService acervo;

    public Controller(AcervoService acervo) {
        this.acervo = acervo;
    }

    @GetMapping
    public String saudacao() {
        return "Bem-vindo à Biblioteca!" ;
    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getLivros() {
        List<Livro> todosLivros = acervo.getAll();
        
        return ResponseEntity.ok(todosLivros);
    }
    
    @GetMapping("/countLivrosAutor/{autor}")
    public ResponseEntity<Integer> countLivrosAutor(@PathVariable("autor") String autor) {
        
        if(autor == null | autor.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        
        int contagem = acervo.countLivrosAutor(autor);

        return ResponseEntity.ok(contagem);
    }
    
    @GetMapping("/countObrasRecentes/{ano}")
    public ResponseEntity<Integer> countObrasRecentes(@PathVariable("ano") int ano) {
        if(ano < 0) {
            return ResponseEntity.badRequest().build();
        }
        
        int contagem = acervo.countObrasRecentes(ano);
        
        return ResponseEntity.ok(contagem);
    }
    
    @GetMapping("/calculaMediaObrasPorAutor")
    public ResponseEntity<Integer> calculaMediaObrasPorAutor() {
        int media = acervo.calculaMediaObrasPorAutor();
        return ResponseEntity.ok(media);
    }
    
}
