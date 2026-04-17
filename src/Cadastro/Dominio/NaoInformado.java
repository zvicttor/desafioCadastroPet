package Cadastro.Dominio;

public enum NaoInformado {
    NAO_INFORMADO(0, "Não Informado");

    public int INFORMACAO_NUMERO;
    public String INFORMACAO_ESCRITA;

    NaoInformado(int INFORMACAO_NUMERO, String INFORMACAO_ESCRITA) {
        this.INFORMACAO_NUMERO = INFORMACAO_NUMERO;
        this.INFORMACAO_ESCRITA = INFORMACAO_ESCRITA;
    }
}
