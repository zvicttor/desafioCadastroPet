package Cadastro.Main;

import Cadastro.Dominio.*;
import Cadastro.Servico.LerFormularioDeCadastro;
import Cadastro.Servico.MenuInicialCadastro;
import Cadastro.Servico.RespostaMenu;
import Cadastro.Servico.ValidacaoNome;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

                    if (nomeCompleto.equals("")){
                        pet.setNome(NaoInformado.NAO_INFORMADO.INFORMACAO_ESCRITA);
                        break;
                    }
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

                if (tipo.equals("")) {
                    pet.setTipo(Tipo.TIPO_NAO_INFORMADO);
                    break;
                }

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

                if (sexo.equals("")) {
                    pet.setSexo(Sexo.SEXO_NAO_INFORMADO);
                    break;
                }
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

            pet.setEndereco(endereco);

            while (true) {
                try {
                    System.out.print("\n5 - Qual a idade aproximada do pet (Se for meses, digite 0)?");
                    String idadeEscrita = input.nextLine();

                    if (idadeEscrita.equals("")) {
                        pet.setIdade(NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO);
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
                    if (valorIdade == 0){
                        while(true) {
                            System.out.print("Quantos meses (1/12)?");
                            int meses = input.nextInt();
                            input.nextLine();

                            if(String.valueOf(meses).equals("")){
                                pet.setMeses(NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO);
                                break;
                            }

                            if (meses < 1 || meses > 12) {
                                System.out.println("So pode de 1 á 12 meses");
                                System.out.println("Tente novamente: ");
                                continue;
                            }
                            pet.setMeses(meses);
                            break;
                        }
                        break;
                    }

                    pet.setIdade(valorIdade);
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
                        pet.setPeso(NaoInformado.NAO_INFORMADO.INFORMACAO_NUMERO);
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

                    pet.setPeso(valorPeso);
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

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'hhmm");
            String s1 = localDateTime.format(formatter);

            String nomeSemEspaco = pet.getNome().replaceAll("\\s+", "");

            String nomeArquivo = s1 + nomeSemEspaco.toUpperCase() + ".txt";

            File file = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados\\" + nomeArquivo);
            try(FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw)){
                file.createNewFile();
                bw.write("1 - " + pet.getNome());
                bw.newLine();
                bw.write("2 - " + pet.getTipo().TIPO_NOME);
                bw.newLine();
                bw.write("3 - " + pet.getSexo().NAME_SEXO);
                bw.newLine();
                bw.write("4 - " + pet.getEndereco());
                bw.newLine();

                if(pet.getIdade() > 0){
                    int idadeInteira = (int) pet.getIdade();
                    bw.write("5 - " + idadeInteira + " anos");
                    bw.newLine();
                }else{
                    bw.write("5 - Não completou 1 ano e tem: " + pet.getMeses() + " meses");
                    bw.newLine();
                }

                bw.write("6 - " + pet.getPeso() + "Kg");
                bw.newLine();
                bw.write("7 - " + pet.getRaca());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
