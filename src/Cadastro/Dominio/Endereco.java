package Cadastro.Dominio;

public class Endereco {
    private String cidade;
    private String rua;
    private int numeroResidencia;

    public Endereco(){}

    public Endereco(String cidade, String rua, int numeroResidencia) {
        this.cidade = cidade;
        this.rua = rua;
        this.numeroResidencia = numeroResidencia;
    }

    @Override
    public String toString() {
        return "cidade: " + cidade + ", " +
                "Rua: " + rua + ", " +
                numeroResidencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }
}
