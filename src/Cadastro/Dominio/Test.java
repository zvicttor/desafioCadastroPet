package Cadastro.Dominio;

import Cadastro.Servico.ValidacaoNome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    static void main() {
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();
        String nomeTemp;

        while (true) {
            try {
                System.out.print("\n1 - Qual o nome e sobrenome do pet?");
                String nomeCompleto = input.nextLine();

                if (nomeCompleto.equals("")){
                    nomeTemp = NaoInformado.NAO_INFORMADO.INFORMACAO_ESCRITA;
                    break;
                }
                if (!ValidacaoNome.hasNomeSobrenome(nomeCompleto)) {
                    System.out.println("Nome deve conter apenas Letras!\nTente Novamente:");
                    continue;
                }
                nomeTemp = nomeCompleto;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Argumento Invalido! ");
                System.out.println("Seu Pet deve ter apenas, Nome e Sobrenome");
            }
        }

        pet.setNome(nomeTemp);
        System.out.println(" --> " + pet.getNome());

    }
}
