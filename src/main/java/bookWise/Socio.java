package bookWise;

public class Socio {
    private static int contador = 1; // Para gerar números de sócio únicos

    private int numeroSocio;
    private String nome;
    private String morada;
    private String telefone;
    private String nifOuCc;
    private String email;
    private double valorMulta;

    public Socio(String nome, String morada, String telefone, String nifOuCc, String email, double valorMulta) {
        this.numeroSocio = contador++;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.nifOuCc = nifOuCc;
        this.email = email;
        this.valorMulta = valorMulta;
    }

    // Getters
    public int getNumeroSocio() {
        return numeroSocio;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNifOuCc() {
        return nifOuCc;
    }

    public String getEmail() {
        return email;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }
}
