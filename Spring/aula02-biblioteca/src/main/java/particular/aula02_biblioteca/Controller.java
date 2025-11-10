package particular.aula02_biblioteca;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/biblioteca")
public class Controller {

    private List<Livro> listaDeLivros;

    public Controller() {
        listaDeLivros = new ArrayList<>();

        listaDeLivros.add(new Livro(1, "O Senhor dos Aneis", 1954));
        listaDeLivros.add(new Livro(2, "1984", 1949));
        listaDeLivros.add(new Livro(3, "Dom Quixote", 1605));
    }

    @GetMapping()
    public String ola() {
        return "bem vindo a biblioteca";
    }

    @GetMapping("/livros")
    public String GetLivros(){
        return "Lista de livros";
    }

    @GetMapping("/livro/especifico")
    public Livro UmLivro() {
        Livro livro = new Livro(0, "null", 0);
        return livro;
    }

    @GetMapping("/valor")
    public double valor() {
        return 2.5;
    }

    @GetMapping("/livro/porId")
    public Livro livroPorId(@RequestParam int id) {
        for (Livro livro : listaDeLivros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null; // ou lançar uma exceção, dependendo do caso de uso
    }

    @GetMapping("/livroContendo/{param}")
    public List<Livro> livroContendo(@PathVariable String param) {
        return listaDeLivros.stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(param.toLowerCase()))
                .toList();
    }
    

    @PostMapping("/livro/novo")
    public Boolean livroNovo(@RequestBody Livro entity) {
        System.out.println("Recebeu o livro: " + entity.getTitulo()); // esse n existe
        listaDeLivros.add(entity);
        return true;
    }
    
    

}
