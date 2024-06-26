package bookWise;

public class Livro {
    private String titulo;
    private String dataAquisicao;
    private String fornecedor;
    private String editora;
    private double preco;
    private String genero;
    private String sbn;

    public Livro(String titulo, String dataAquisicao, String fornecedor, String editora, double preco, String genero, String sbn) {
        this.titulo = titulo;
        this.dataAquisicao = dataAquisicao;
        this.fornecedor = fornecedor;
        this.editora = editora;
        this.preco = preco;
        this.genero = genero;
        this.sbn = sbn;
    }

    // Getters and setters
    public String getTitulo() {
        return titulo;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public String getEditora() {
        return editora;
    }

    public double getPreco() {
        return preco;
    }

    public String getGenero() {
        return genero;
    }

    public String getSbn() {
        return sbn;
    }
}
