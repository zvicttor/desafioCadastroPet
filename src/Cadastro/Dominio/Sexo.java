package Cadastro.Dominio;

public enum Sexo {
    MACHO(1, "Macho"),
    FEMEA(2, "Femea"),
    SEXO_NAO_INFORMADO(3, "Sexo nao informado");

    public final int NUMBER_SEXO;
    public final String NAME_SEXO;

    Sexo(int NUMBER_SEXO, String NAME_SEXO) {
        this.NUMBER_SEXO = NUMBER_SEXO;
        this.NAME_SEXO = NAME_SEXO;
    }
}
