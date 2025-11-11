package particular.aula02_biblioteca;

public class Livro {
    private Integer id;
    private String titulo;
    private Integer anoPublicacao;

    public Livro(int id, String titulo, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
}
