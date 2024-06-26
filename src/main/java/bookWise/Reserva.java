package bookWise;

public class Reserva {
    private String numeroSocio;
    private String autor;
    private String titulo;

    public Reserva(String numeroSocio, String autor, String titulo) {
        this.numeroSocio = numeroSocio;
        this.autor = autor;
        this.titulo = titulo;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }
}
