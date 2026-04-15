package Cadastro.Main;

import Cadastro.Dominio.Endereco;
import Cadastro.Dominio.Pet;
import Cadastro.Dominio.Sexo;
import Cadastro.Dominio.Tipo;
import Cadastro.Servico.LerFormularioDeCadastro;
import Cadastro.Servico.MenuInicialCadastro;
import Cadastro.Servico.RespostaMenu;
import Cadastro.Servico.ValidacaoNome;


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
                if (RespostaMenu.isValido(respostaMenu) == false) {
                    System.out.println("\nXXX Numero de Entrada Invalido! XXX\nTente Novamente:");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nArgumento Invalido! A resposta deve ser um numero inteiro.\nTente Novamente:");
                input.next();
            }
        }

        System.out.println();

        if (respostaMenu == 1) {
            LerFormularioDeCadastro.formulario();
            input.nextLine();

            Pet pet = new Pet();

            while (true) {
                try {
                    System.out.print("\n1 - Qual o nome e sobrenome do pet?");
                    String nomeCompleto = input.nextLine();

                    if (!ValidacaoNome.hasNomeSobrenome(nomeCompleto)) {
                        System.out.println("Nome deve conter apenas Letras!\nTente Novamente:");
                        continue;
                    }
                    pet.setNome(nomeCompleto);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Argumento Invalido! ");
                    System.out.println("Seu Pet deve ter apenas, Nome e Sobrenome");
                }
            }

            while (true) {
                System.out.print("\n2 - Qual o tipo do pet (Cachorro/Gato)?");
                String tipo = input.nextLine();
                if (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")) {
                    System.out.println("tipo invalido");
                    continue;
                } else if (tipo.equalsIgnoreCase("cachorro")) {
                    pet.setTipo(Tipo.CACHORRO);
                } else {
                    pet.setTipo(Tipo.GATO);
                }
                break;
            }

            while (true) {
                System.out.print("\n3 - Qual o sexo do animal (Macho/Femea)?");
                String sexo = input.nextLine();
                if (!sexo.equalsIgnoreCase("macho") && !sexo.equalsIgnoreCase("femea")) {
                    System.out.println("sexo invalido");
                    continue;
                } else if (sexo.equalsIgnoreCase("macho")) {
                    pet.setSexo(Sexo.MACHO);
                } else {
                    pet.setSexo(Sexo.FEMEA);
                }
                break;
            }

            System.out.println("\n4 - Qual endereço e bairro que ele foi encontrado?");
            System.out.print("I - Cidade: ");
            String cidade = input.nextLine();
            System.out.print("II - Rua: ");
            String rua = input.nextLine();
            System.out.print("III - Numero da Residencia: ");
            int numeroResidencia = input.nextInt();

            Endereco endereco = new Endereco(cidade, rua, numeroResidencia);

            while (true) {
                try {
                    System.out.print("\n5 - Qual a idade aproximada do pet?");
                    String idadeEscrita = input.nextLine();

                    if (!idadeEscrita.matches("\\d+([\\.,]\\d+)?")) {
                        System.out.println("So pode conter digitos! ");
                        System.out.println("Tente novamente: ");
                        continue;
                    }
                    double valorIdade = Double.parseDouble(idadeEscrita.replace(",", "."));
                    if (valorIdade > 20 || valorIdade < 0) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    System.out.println("Idade Invalida para seu pet");
                    System.out.println("Digite novamente: ");
                }
            }

            while (true) {
                try {
                    System.out.print("\n6 - Qual o peso aproximado do pet?");
                    String pesoEscrito = input.nextLine();

                    if (!pesoEscrito.matches("\\d+([\\.,]\\d+)?")) {
                        System.out.println("So pode conter digitos! ");
                        System.out.println("Tente novamente: ");
                        continue;
                    }
                    double valorPeso = Double.parseDouble(pesoEscrito.replace(",", "."));
                    if (valorPeso > 60 || valorPeso < 0.5) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    System.out.println("Peso Invalido para seu pet");
                    System.out.println("Digite novamente: ");
                }
            }
        }
    }
}
