package Cadastro.Dominio;

import Cadastro.Servico.ValidacaoNome;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    static void main() {
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();
        String nomeTemp;

        String numeros = "012345";

        System.out.println(numeros.substring(3));
    }

}
