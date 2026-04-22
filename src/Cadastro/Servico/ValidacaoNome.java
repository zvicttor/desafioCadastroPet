package Cadastro.Servico;

import Cadastro.Dominio.Pet;

import java.util.regex.PatternSyntaxException;

public abstract class ValidacaoNome {
    public static boolean hasNomeSobrenome(String nomeCompleto) {

        String[] nomeAndSobrenome = nomeCompleto.trim().split("\\s+");
            if(nomeAndSobrenome.length < 2 || nomeAndSobrenome.length > 3 ){
            throw new IllegalArgumentException();
        }

        for (String parte : nomeAndSobrenome){
                if(!parte.matches("[a-zA-Z]+")){
                    return false;
                }
            }
        return true;
    }
}
