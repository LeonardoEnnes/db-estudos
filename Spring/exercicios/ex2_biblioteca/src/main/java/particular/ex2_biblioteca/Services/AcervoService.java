package particular.ex2_biblioteca.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import particular.ex2_biblioteca.Entities.Livro;

@Service
public class AcervoService {
    private List<Livro> livros;

    public AcervoService() {
        livros = new LinkedList<>();
        
        livros.add(new Livro("1984", "George Orwell", 1949));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien",    1954));
        livros.add(new Livro("Dom Quixote", "Miguel de Cervantes", 1605));
        livros.add(new Livro("Guerra e Paz", "Liev Tolstói", 1869));
        livros.add(new Livro("Teste", "Liev Tolstói", 000));
        
    }

    public List<Livro> getAll() {
        return livros;
    }

    public List<String> getTitulos() {
        return livros.stream()
            .map(Livro::getTitulo)
            .toList();
    }

    // 1 quantas obras sao de um determinado autor
    public int countLivrosAutor(String autor) {
        return livros.stream()
            .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
            .toList()
            .size();
    }

    // 2 Contar quantas são as obras mais recentes que determinado ano
    public int countObrasRecentes(int ano) {
        return livros.stream()
            .filter(livro -> livro.getAno() > ano)
            .toList()
            .size();
    }

    // 3. calcular n medio de obras por autor
    public int calculaMediaObrasPorAutor(){
        long numeroAutoresUnicos = livros.stream()
            .map(Livro::getTitulo)
            .distinct() // remove duplicatas
            .count();

        if(numeroAutoresUnicos == 0){
            return 0;
        }

        return livros.size() / (int) numeroAutoresUnicos;
    }
}
