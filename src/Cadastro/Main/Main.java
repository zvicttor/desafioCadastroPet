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
            Tipo tipoTemp;
            String nomeTemp;
            String sobrenomeTemp;
            Sexo sexoTemp;
            double idadeTemp = 0;
            double pesoTemp;
            String racaTemp;
            Endereco enderecoTemp;

            while (true) {
                System.out.println("O usuário poderá buscar o pet por:\n" +
                        "Nome ou sobrenome\n" + "Sexo\n" + "Idade\n" + "Peso\n" + "Raça\n" + "Endereço");
                System.out.println("Podendo combinar criterios, Exemplo (Idade e Peso)");
                System.out.println("Deseja combinar criterios (Sim/Nao)? ");

                String respostaCriterio = input.nextLine();

                if (!respostaCriterio.equalsIgnoreCase("Sim") && !respostaCriterio.equalsIgnoreCase("Nao")) {
                    System.out.println("Resposta Invalida! Tente Novamente: ");
                    continue;
                }

                if (respostaCriterio.equalsIgnoreCase("Sim")) {
                    System.out.print("Digite o Tipo do Animal: ");
                    String tipo = input.nextLine();

                    if (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")) {
                        System.out.println("Tipo invalido!");
                        continue;
                    } else if (tipo.equalsIgnoreCase("cachorro")) {
                        tipoTemp = Tipo.CACHORRO;
                    } else {
                        tipoTemp = Tipo.GATO;
                    }

                    String criterio1;
                    String criterio2;

                    while (true) {
                        System.out.print("Digite o primeiro criterio: ");
                        criterio1 = input.nextLine();

                        System.out.print("Digite o segundo criterio: ");
                        criterio2 = input.nextLine();

                        if (Pet.temAtributo(criterio1) == false && Pet.temAtributo(criterio2) == false) {
                            System.out.println("Algum Criterio foi passado de forma invalida! Tente Novamente: ");
                            continue;
                        }
                        break;
                    }

                    if (criterio1.equalsIgnoreCase("Nome") || criterio2.equalsIgnoreCase("Nome")) {
                        System.out.println("entre com o Nome: ");
                        nomeTemp = input.nextLine();

                    }
                    if (criterio1.equalsIgnoreCase("Sobrenome") || criterio2.equalsIgnoreCase("Sobrenome")) {
                        System.out.println("entre com o Sobrenome: ");
                        sobrenomeTemp = input.nextLine();

                    }
                    if (criterio1.equalsIgnoreCase("Sexo") || criterio2.equalsIgnoreCase("Sexo")) {
                        System.out.println("entre com o Sexo do animal: ");
                        String sexo = input.nextLine();

                        while (true) {
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
                    }
                    if (criterio1.equalsIgnoreCase("Idade") || criterio2.equalsIgnoreCase("Idade")) {
                        System.out.println("Entre com a idade do animal: ");
                        idadeTemp = input.nextDouble();
                        input.nextLine();

                    }
                    if (criterio1.equalsIgnoreCase("Peso") || criterio2.equalsIgnoreCase("Peso")) {
                        System.out.println("Entre com o peso do animal: ");
                        pesoTemp = input.nextDouble();
                        input.nextLine();

                    }
                    if (criterio1.equalsIgnoreCase("Raca") || criterio2.equalsIgnoreCase("Raca")) {
                        System.out.println("Entre com a raca do animal: ");
                        racaTemp = input.nextLine();

                    }
                    if (criterio1.equalsIgnoreCase("Endereco") || criterio2.equalsIgnoreCase("Endereco")) {
                        System.out.print("I - Cidade: ");
                        String cidade = input.nextLine();

                        System.out.print("II - Rua: ");
                        String rua = input.nextLine();

                        System.out.print("III - Numero da Residencia: ");
                        int numeroResidencia = input.nextInt();
                        input.nextLine();

                        enderecoTemp = new Endereco(cidade, rua, numeroResidencia);
                    }
                    break;
                } else {
                    System.out.println("Digite o Tipo do Animal: ");
                    String tipo = input.nextLine();

                    if (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")) {
                        System.out.println("Tipo invalido!");
                        continue;
                    } else if (tipo.equalsIgnoreCase("cachorro")) {
                        tipoTemp = Tipo.CACHORRO;
                    } else {
                        tipoTemp = Tipo.GATO;
                    }

                    String criterio;

                    while (true) {
                        System.out.print("Digite 1 Criterio para busca: ");
                        criterio = input.nextLine();
                        if (Pet.temAtributo(criterio) == false) {
                            System.out.println("O criterio foi passado de forma invalida! Tente Novamente: ");
                            continue;
                        }
                        break;
                    }

                    if (criterio.equalsIgnoreCase("Nome")) {
                        System.out.println("entre com o Nome: ");
                        nomeTemp = input.nextLine();

                    }
                    if (criterio.equalsIgnoreCase("Sobrenome")) {
                        System.out.println("entre com o Sobrenome: ");
                        sobrenomeTemp = input.nextLine();

                    }
                    if (criterio.equalsIgnoreCase("Sexo")) {
                        System.out.println("entre com o Sexo do animal: ");
                        String sexo = input.nextLine();

                        while (true) {
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

                    }
                    if (criterio.equalsIgnoreCase("Idade")) {
                        System.out.println("Entre com a idade do animal: ");
                        idadeTemp = input.nextDouble();
                        input.nextLine();

                    }
                    if (criterio.equalsIgnoreCase("Peso")) {
                        System.out.println("Entre com o peso do animal: ");
                        pesoTemp = input.nextDouble();
                        input.nextLine();

                    }
                    if (criterio.equalsIgnoreCase("Raca")) {
                        System.out.println("Entre com a raca do animal: ");
                        racaTemp = input.nextLine();

                    }
                    if (criterio.equalsIgnoreCase("Endereco")) {
                        System.out.print("I - Cidade: ");
                        String cidade = input.nextLine();

                        System.out.print("II - Rua: ");
                        String rua = input.nextLine();

                        System.out.print("III - Numero da Residencia: ");
                        int numeroResidencia = input.nextInt();
                        input.nextLine();

                        enderecoTemp = new Endereco(cidade, rua, numeroResidencia);
                    }
                }

                File pasta = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados");
                File[] arquivos = pasta.listFiles();

                if(arquivos == null){
                    System.out.println("Nao existe pets Cadastrados! ");
                    break;
                }else{
                    for (File arquivo : arquivos){
                        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                            String linha;
                            while ((linha = br.readLine()) != null){
                                //to lendo os arquivos, agora pensar apartir daqui
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }

                break;
            }
        }
    }
}