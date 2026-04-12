package Cadastro.Main;

import Cadastro.Servico.LerFormularioDeCadastro;
import Cadastro.Servico.MenuInicialCadastro;
import Cadastro.Servico.RespostaMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner input = new Scanner(System.in);
        int respostaMenu = 0;

        while (true) {
            try {
                MenuInicialCadastro.exibirMenu();
                respostaMenu = input.nextInt();
                if(RespostaMenu.isValido(respostaMenu) == false){
                    System.out.println("XXX Numero de Entrada Invalido! XXX\nTente Novamente:");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Argumento Invalido! A resposta deve ser um numero inteiro.\nTente Novamente:");
                input.next();
            }
        }

        System.out.println("saiu");
    }
}
