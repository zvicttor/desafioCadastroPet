package Cadastro.Dominio;

public enum Tipo {
    CACHORRO(1, "Cachorro"),
    GATO(2, "Gato");

    public final int TIPO_NUMBER;
    public final String TIPO_NOME;

    Tipo(int TIPO_NUMBER, String TIPO_NOME) {
        this.TIPO_NUMBER = TIPO_NUMBER;
        this.TIPO_NOME = TIPO_NOME;
    }
}
