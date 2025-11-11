package particular.ex1_biblioteca;

public class Livro {
    private int id;
    private String titulo;
    private int ano;
    private String autor;

    public Livro(int id, String titulo, int ano, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
