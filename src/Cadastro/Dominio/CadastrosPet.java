package Cadastro.Dominio;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CadastrosPet {
    private Pet[] pet;

    public Pet[] getPet() {
        return pet;
    }

    public void setPet(Pet[] pet) {
        this.pet = pet;
    }

    public static String findWhichCriterion(String resposta, Scanner input) {
        if (resposta.equalsIgnoreCase("Nome")) {
            System.out.print("entre com o Nome: ");
            return input.nextLine();
        }

        if (resposta.equalsIgnoreCase("Sobrenome")) {
            System.out.print("entre com o Sobrenome: ");
            return input.nextLine();
        }

        if (resposta.equalsIgnoreCase("Sexo")) {
            String sexoTemp;
            while (true) {
                System.out.print("entre com o Sexo do animal: ");
                sexoTemp = input.nextLine();

                if (!sexoTemp.equalsIgnoreCase("macho") && !sexoTemp.equalsIgnoreCase("femea")) {
                    System.out.println("Sexo invalido! (Macho/Femea)");
                    continue;
                }
                break;
            }
            return sexoTemp;
        }

        if (resposta.equalsIgnoreCase("Idade")) {
            String idadeEscritaTemp;

            while (true) {
                System.out.print("Entre com a idade do animal: ");
                idadeEscritaTemp = input.nextLine();

                if (!idadeEscritaTemp.matches("\\d+([\\.,]\\d+)?")) {
                    System.out.println("So pode conter digitos! ");
                    System.out.println("Tente novamente: ");
                    continue;
                }

                double valorIdade = Double.parseDouble(idadeEscritaTemp.replace(",", "."));
                if (valorIdade > 20 || valorIdade < 0) {
                    System.out.println("Idade Invalida! (0 - 20)");
                    continue;
                }

                if (valorIdade == 0) {
                    int mesesTemp;
                    while (true) {
                        System.out.print("Quantos meses (1/12)?");
                        mesesTemp = input.nextInt();
                        input.nextLine();

                        if (mesesTemp < 1 || mesesTemp > 12) {
                            System.out.println("Limite de 1 á 12 meses");
                            System.out.println("Tente novamente: ");
                            continue;
                        }
                        break;
                    }
                    return String.valueOf(mesesTemp);
                }
                break;
            }
            return idadeEscritaTemp;
        }

        if (resposta.equalsIgnoreCase("Peso")) {
            String pesoEscritoTemp;
            while (true) {
                System.out.print("Entre com o peso do animal: ");
                pesoEscritoTemp = input.nextLine();

                if (!pesoEscritoTemp.matches("\\d+([\\.,]\\d+)?")) {
                    System.out.println("\nSo pode conter digitos! ");
                    System.out.println("Tente novamente: ");
                    continue;
                }
                break;
            }
            return pesoEscritoTemp;
        }

        if (resposta.equalsIgnoreCase("Raca")) {
            System.out.print("Entre com a raca do animal: ");
            return input.nextLine();
        }

        if (resposta.equalsIgnoreCase("Endereco")) {
            String respostaEndereco;
            while (true) {
                System.out.print("Voce deseja buscar o endereco por Cidade ou a Rua? ");
                respostaEndereco = input.nextLine();

                if (!respostaEndereco.equalsIgnoreCase("Cidade") && !respostaEndereco.equalsIgnoreCase("Rua")) {
                    System.out.println("\nResposta Invalida, Tente Novamente! (Cidade ou Rua)");
                    continue;
                }
                break;
            }

            if (respostaEndereco.equalsIgnoreCase("Cidade")) {
                System.out.print("Entre com a Cidade: ");
                return input.nextLine();
            }
            if (respostaEndereco.equalsIgnoreCase("Rua")) {
                System.out.print("Entre com a Rua: ");
                return input.nextLine();
            }
        }

        return "\nCriterio não encontrado";
    }

    public static void findCriterion(String tipoPet, String criterio, String dataCadastro) {
        File pasta = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados");
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Não existem pets cadastrados!");
            return;
        }

        System.out.println("\nLISTA DE PETS ENCONTRADOS");
        System.out.println("Criterio Utilizado: " + criterio.toUpperCase() + "\n");
        boolean hasOne = false;

        int contadorResultados = 1;
        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                StringBuilder conteudoPet = new StringBuilder();
                boolean findTipo = false;
                boolean findCriterion = false;
                boolean hasDate = arquivo.getName().contains(dataCadastro);
                String dataBR = "N/A";

                if(hasDate){
                    LocalDate data = LocalDate.parse(dataCadastro, DateTimeFormatter.BASIC_ISO_DATE);
                    DateTimeFormatter formatterBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataBR = data.format(formatterBr);
                }

                while ((linha = br.readLine()) != null) {
                    String dados = linha.substring(linha.indexOf("-") + 1).trim();

                    if(conteudoPet.length() > 0){
                        conteudoPet.append(" - ");
                    }
                    conteudoPet.append(dados);

                    if (linha.toLowerCase().contains(tipoPet.toLowerCase())) {
                        findTipo = true;
                    }

                    if (linha.toLowerCase().contains(criterio.toLowerCase())) {
                        findCriterion = true;
                    }
                }

                if (findCriterion && findTipo && hasDate) {
                    System.out.println(contadorResultados + ". " + conteudoPet.toString() + " -> Data Cadastro: " + dataBR);
                    hasOne = true;
                    contadorResultados++;
                }
                if (findCriterion && findTipo && dataCadastro.equals("-")) {
                    System.out.println(contadorResultados + ". " + conteudoPet.toString());
                    hasOne = true;
                    contadorResultados++;
                }

            } catch (IOException e) {
                System.out.println("Erro ao ler aquivo! ");
            }
        }

        if (!hasOne) {
            System.out.println("Não foi encontrado Pet com esse criterio!");
        }
    }

    public static void find2Criterion(String tipoPet, String criterio1, String criterio2, String dataCadastro) {
        File pasta = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados");
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Não existem pets cadastrados!");
            return;
        }

        System.out.println("\nLISTA DE PETS ENCONTRADOS");
        System.out.println("Criterios Utilizados: " + criterio1.toUpperCase() + " - " + criterio2.toUpperCase() +"\n");
        boolean hasOne = false;

        int contadorResultados = 1;
        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                StringBuilder conteudoPet = new StringBuilder();
                boolean findTipo = false;
                boolean findCriterion1 = false;
                boolean findCriterion2 = false;
                boolean hasDate = arquivo.getName().contains(dataCadastro);
                String dataBR = "N/A";

                if(hasDate){
                    LocalDate data = LocalDate.parse(dataCadastro, DateTimeFormatter.BASIC_ISO_DATE);
                    DateTimeFormatter formatterBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataBR = data.format(formatterBr);
                }

                while ((linha = br.readLine()) != null) {
                    String dados = linha.substring(linha.indexOf("-") + 1).trim();

                    if(conteudoPet.length() > 0){
                        conteudoPet.append(" - ");
                    }
                    conteudoPet.append(dados);

                    if (linha.toLowerCase().contains(tipoPet.toLowerCase())) {
                        findTipo = true;
                    }

                    if (linha.toLowerCase().contains(criterio1.toLowerCase())) {
                        findCriterion1 = true;
                    }
                    if (linha.toLowerCase().contains(criterio2.toLowerCase())) {
                        findCriterion2 = true;
                    }
                }

                if (findCriterion1 && findCriterion2 && findTipo && hasDate) {
                    System.out.println(contadorResultados + ". " + conteudoPet.toString() + " -> Data Cadastro: " + dataBR);
                    hasOne = true;
                    contadorResultados++;
                }
                if (findCriterion1 && findCriterion2 && findTipo && dataCadastro.equals("-")) {
                    System.out.println(contadorResultados + ". " + conteudoPet.toString());
                    hasOne = true;
                    contadorResultados++;
                }

            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo! ");
            }
        }
        if (!hasOne) {
            System.out.println("Não foi encontrado pet com os criterios passados");
        }
    }
}

