package bookWise;

public class Emprestimo {
    private String titulo;
    private String sbn;
    private String numeroSocio;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(String titulo, String sbn, String numeroSocio, String dataEmprestimo, String dataDevolucao) {
        this.titulo = titulo;
        this.sbn = sbn;
        this.numeroSocio = numeroSocio;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSbn() {
        return sbn;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void prolongarEmprestimo(int dias) {
          this.dataDevolucao = dataDevolucao + dias;

    }
}
