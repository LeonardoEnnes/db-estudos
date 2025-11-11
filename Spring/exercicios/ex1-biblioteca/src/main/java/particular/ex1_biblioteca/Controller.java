package particular.ex1_biblioteca;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controller {
    private List<Livro> listaDeLivros;

    public Controller() {
        listaDeLivros = new ArrayList<>();
        listaDeLivros.add(new Livro(1, "O Senhor dos Aneis", 1954, null));
        listaDeLivros.add(new Livro(2, "1984", 1949, null));
        listaDeLivros.add(new Livro(3, "Dom Quixote", 160, "A"));
        listaDeLivros.add(new Livro(4, "O Pequeno Principe", 1943, "A"));
    }

    @GetMapping()
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/biblioteca/novoLivro")
    public Boolean novoLivro(@RequestBody final Livro livro) {
        listaDeLivros.add(livro);
        return true;
    }

    // retorna todos os livros completos
    
    // 1
    @GetMapping("/biblioteca/titulos")
    public  List<String> titulos() {
        return listaDeLivros.stream()
                .map(Livro::getTitulo)
                .toList();
    }
    
    // 2
    @GetMapping("/biblioteca/autores")
    public  List<String> autores() {
        return listaDeLivros.stream()
                .map(Livro::getAutor)
                .toList();          
    }
    
    // 3
    @GetMapping("/biblioteca/livrosAno/{ano}")
    public List<Livro> livrosAno(@PathVariable int ano) {
        return listaDeLivros.stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    // 4
    @GetMapping("/biblioteca/desatualiizados/{ano}")
    public List<Livro> desatualizados(@PathVariable int ano) {
        return listaDeLivros.stream()
                .filter(livro -> livro.getAno() < ano)
                .toList();      
    }
    
    // 5
    @GetMapping("/biblioteca/livrosTitulo/{titulo}")
    public ResponseEntity<List<Livro>> getLivrosPorTitulo(@PathVariable String titulo) {
        var resultado = listaDeLivros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .toList();

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
    
    // 6
    @GetMapping("/biblioteca/livrosAutor/{autor}/{ano}")
    public ResponseEntity<List<Livro>> getLivrosAutorDeterminadoAno(@PathVariable String autor, @PathVariable int ano) {
        List<Livro> livrosFiltrados = listaDeLivros.stream()
                .filter(Livro -> Livro.getAutor() != null)
                .filter(livro -> livro.getAno() == ano)
                .toList();

        if (livrosFiltrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(livrosFiltrados);
    }

    // 7
    @PostMapping("/biblioteca/atualizaLivros/{id}")
    public ResponseEntity<Livro> atualizaLivroPorId(@PathVariable int id, @RequestBody Livro livroAtualizado) {
        
       Livro livroExistente = null;

       for (Livro livro : listaDeLivros) {
            if (livro.getId() == id) {
                livroExistente = livro;
                break;
            }
       }

        if (livroExistente != null) {
            livroExistente.setTitulo(livroAtualizado.getTitulo());    
            livroExistente.setAno(livroAtualizado.getAno());
            livroExistente.setAutor(livroAtualizado.getAutor());

            return ResponseEntity.ok(livroExistente);
        }
        
        return ResponseEntity.notFound().build();
    }
    
}
