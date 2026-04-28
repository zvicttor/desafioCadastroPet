package Cadastro.Main;

import Cadastro.Dominio.*;
import Cadastro.Servico.*;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CadastrosPet cadastrosPet = new CadastrosPet();
        Pet[] pets = new Pet[50];
        cadastrosPet.setPet(pets);

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
            String nomeTemp;
            Tipo tipoTemp;
            Sexo sexoTemp;
            double idadeTemp = 0;
            int mesesTemp = 0;
            double pesoTemp;
            String racaTemp;

            while (true) {
                try {
                    System.out.print("\n1 - Qual o nome e sobrenome do pet?");
                    String nomeCompleto = input.nextLine();

                    if (nomeCompleto.equals("")) {
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

            while (true) {
                System.out.print("\n2 - Qual o tipo do pet (Cachorro/Gato)?");
                String tipo = input.nextLine();

                if (tipo.equals("")) {
                    tipoTemp = Tipo.TIPO_NAO_INFORMADO;
                    break;
                }

                if (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")) {
                    System.out.println("tipo invalido");
                    continue;
                } else if (tipo.equalsIgnoreCase("cachorro")) {
                    tipoTemp = Tipo.CACHORRO;
                } else {
                    tipoTemp = Tipo.GATO;
                }
                break;
            }

            while (true) {
                System.out.print("\n3 - Qual o sexo do animal (Macho/Femea)?");
                String sexo = input.nextLine();

                if (sexo.equals("")) {
                    sexoTemp = Sexo.SEXO_NAO_INFORMADO;
                    break;
                }
                if (!sexo.equalsIgnoreCase("macho") && !sexo.equalsIgnoreCase("femea")) {
                    System.out.println("sexo invalido");
                    continue;
                } else if (sexo.equalsIgnoreCase("macho")) {
                    sexoTemp = Sexo.MACHO;
                } else {
                    sexoTemp = Sexo.FEMEA;
                }
                break;
            }


            Endereco endereco = new Endereco();

            System.out.println("\n4 - Qual endereço e bairro que ele foi encontrado?");

            System.out.print("I - Cidade: ");
            String cidade = input.nextLine();

            System.out.print("II - Rua: ");
            String rua = input.nextLine();

            System.out.print("III - Numero da Residencia: ");
            int numeroResidencia = input.nextInt();
            input.nextLine();

            if (cidade.equals("")) {
                endereco.setCidade(NaoInformado.NAO_INFORMADO.INFORMACAO_ESCRITA);
            } else {
                endereco.setCidade(cidade);
            }

            if (rua.equals("")) {
                endereco.setRua(NaoInformado.NAO_INFORMADO.INFORMACAO_ESCRITA);
            } else {
                endereco.setRua(rua);
            }

            if (numeroResidencia == 0) {
                endereco.setNumeroResidencia(NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO);
            } else {
                endereco.setNumeroResidencia(numeroResidencia);
            }

            while (true) {
                try {
                    System.out.print("\n5 - Qual a idade aproximada do pet (Se for meses, digite 0)?");
                    String idadeEscrita = input.nextLine();

                    if (idadeEscrita.equals("")) {
                        idadeTemp = NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO;
                        break;
                    }

                    if (!idadeEscrita.matches("\\d+([\\.,]\\d+)?")) {
                        System.out.println("So pode conter digitos! ");
                        System.out.println("Tente novamente: ");
                        continue;
                    }
                    double valorIdade = Double.parseDouble(idadeEscrita.replace(",", "."));
                    if (valorIdade > 20 || valorIdade < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (valorIdade == 0) {
                        while (true) {
                            System.out.print("Quantos meses (1/12)?");
                            int meses = input.nextInt();
                            input.nextLine();

                            if (String.valueOf(meses).equals("")) {
                                mesesTemp = NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO;
                                break;
                            }

                            if (meses < 1 || meses > 12) {
                                System.out.println("So pode de 1 á 12 meses");
                                System.out.println("Tente novamente: ");
                                continue;
                            }
                            mesesTemp = meses;
                            break;
                        }
                        break;
                    }

                    idadeTemp = valorIdade;
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

                    if (pesoEscrito.equals("")) {
                        pesoTemp = NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO;
                        break;
                    }

                    if (!pesoEscrito.matches("\\d+([\\.,]\\d+)?")) {
                        System.out.println("So pode conter digitos! ");
                        System.out.println("Tente novamente: ");
                        continue;
                    }
                    double valorPeso = Double.parseDouble(pesoEscrito.replace(",", "."));
                    if (valorPeso > 60 || valorPeso < 0.5) {
                        throw new IllegalArgumentException();
                    }

                    pesoTemp = valorPeso;
                    break;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    System.out.println("Peso Invalido para seu pet");
                    System.out.println("Digite novamente: ");
                }
            }

            while (true) {
                System.out.print("\n7 - Qual a raça do pet?");
                String raca = input.nextLine();

                if (raca.equals("")) {
                    racaTemp = NaoInformado.NAO_INFORMADO.INFORMACAO_ESCRITA;
                    break;
                }

                if (!raca.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
                    System.out.println("Não poderá usar números nem caracteres especiais! ");
                    System.out.println("Tente novamente: ");
                    continue;
                }
                racaTemp = raca;
                break;
            }

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'hhmm");
            String s1 = localDateTime.format(formatter);
            String nomeSemEspaco = nomeTemp.replaceAll("\\s+", "");
            String nomeArquivo = s1 + nomeSemEspaco.toUpperCase() + ".txt";

            File file = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados\\" + nomeArquivo);
            CriarArquivoPet.criar(file, nomeTemp, tipoTemp, sexoTemp, endereco, idadeTemp, mesesTemp, pesoTemp, racaTemp);

            for (int i = 0; i < pets.length; i++) {
                if (pets[i] == null) {
                    pets[i] = new Pet(nomeTemp, tipoTemp, sexoTemp, endereco, idadeTemp, mesesTemp, pesoTemp, racaTemp);
                    break;
                }
            }
        }

        if (respostaMenu == 2) {

            input.nextLine();
            String respostaCriterio;
            String tipoPet;


            while (true) {
                System.out.println("Primeiro o Usuario deve encontrar o Pet! ");
                System.out.println("O usuário poderá buscar o pet com os seguintes criterios apenas:\n" +
                        "Nome ou sobrenome\n" + "Sexo\n" + "Idade\n" + "Peso\n" + "Raça\n" + "Endereço");
                System.out.println("Podendo combinar criterios, Exemplo (Idade e Peso)");
                System.out.print("Deseja combinar criterios (Sim/Nao)? ");
                respostaCriterio = input.nextLine();

                if (!respostaCriterio.equalsIgnoreCase("Sim") && !respostaCriterio.equalsIgnoreCase("Nao")) {
                    System.out.println("Resposta Invalida! Tente Novamente: ");
                    continue;
                }
                break;
            }

            while(true) {
                System.out.print("Digite o Tipo do Animal: ");
                String tipoAnimal = input.nextLine();

                if (!tipoAnimal.equalsIgnoreCase("cachorro") && !tipoAnimal.equalsIgnoreCase("gato")) {
                    System.out.println("Tipo invalido! (Cachorro/Gato)");
                    continue;
                }
                tipoPet = tipoAnimal;
                break;
            }

            if (respostaCriterio.equalsIgnoreCase("Sim")) {
                String criterio1;
                String criterio2;

                while (true) {
                    System.out.print("Digite o primeiro criterio: ");
                    String resposta1 = input.nextLine();

                    System.out.print("Digite o segundo criterio: ");
                    String resposta2 = input.nextLine();

                    if (Pet.temAtributo(resposta1) == false || Pet.temAtributo(resposta2) == false) {
                        System.out.println("Algum Criterio foi passado de forma invalida! Tente Novamente: ");
                        continue;
                    } else {
                        criterio1 = CadastrosPet.findWhichCriterion(resposta1, input);
                        criterio2 = CadastrosPet.findWhichCriterion(resposta2, input);
                    }
                    break;
                }

                CadastrosPet.find2Criterion(tipoPet, criterio1, criterio2);
            }

            if (respostaCriterio.equalsIgnoreCase("Nao")) {
                String criterio;

                while (true) {
                    System.out.print("Digite pelo menos 1 criterio para busca: ");
                    String resposta = input.nextLine();

                    if (Pet.temAtributo(resposta) == false) {
                        System.out.println("Criterio foi passado de forma invalida! Tente Novamente: ");
                        continue;
                    } else {
                        criterio = CadastrosPet.findWhichCriterion(resposta, input);
                    }
                    break;
                }

                CadastrosPet.findCriterion(tipoPet, criterio);
            }

            //dentro do 2
        }


        //dentro do main
    }
}
