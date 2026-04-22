package Cadastro.Dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    static void main() {
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();
        while (true) {
            System.out.print("\n7 - Qual a raça do pet?");
            String raca = input.nextLine();

            if (raca.equals("")) {
                pet.setRaca(NaoInformado.NAO_INFORMADO.INFORMACAO_ESCRITA);
                break;
            }

            if (!raca.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
                System.out.println("Não poderá usar números nem caracteres especiais! ");
                System.out.println("Tente novamente: ");
                continue;
            }
            pet.setRaca(raca);
            break;
        }

    }
}
