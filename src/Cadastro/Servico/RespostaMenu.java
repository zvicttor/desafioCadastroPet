package Cadastro.Servico;

public abstract class RespostaMenu{
    public static boolean isValido(int resposta){
        boolean isValido = true;
        if (resposta <= 0 || resposta > 6){
            isValido = false;
        }
        return isValido;
    }
}
