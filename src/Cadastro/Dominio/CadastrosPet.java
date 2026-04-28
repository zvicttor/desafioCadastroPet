package Cadastro.Dominio;

import java.io.*;
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
            System.out.print("entre com o Sexo do animal: ");
            String sexoTemp = input.nextLine();

            while (true) {
                if (!sexoTemp.equalsIgnoreCase("macho") && !sexoTemp.equalsIgnoreCase("femea")) {
                    System.out.println("Sexo invalido! (Macho/Femea)");
                    continue;
                }
                break;
            }
            return sexoTemp;
        }

        if (resposta.equalsIgnoreCase("Idade")) {
            System.out.print("Entre com a idade do animal: ");
            String idadeEscritaTemp = input.nextLine();

            while (true) {
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
            System.out.print("Entre com o peso do animal: ");
            String pesoEscritoTemp = input.nextLine();

            while (true) {
                if (!pesoEscritoTemp.matches("\\d+([\\.,]\\d+)?")) {
                    System.out.println("So pode conter digitos! ");
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
            System.out.print("Voce deseja buscar o endereco por Cidade ou a Rua? ");
            String respostaEndereco = input.nextLine();

            while (true){
                if(!respostaEndereco.equalsIgnoreCase("Cidade") && !respostaEndereco.equalsIgnoreCase("Rua")){
                    System.out.println("Resposta Invalida, Tente Novamente! (Cidade ou Rua)");
                    continue;
                }
                break;
            }
            if(respostaEndereco.equalsIgnoreCase("Cidade")){
                System.out.print("Entre com a Cidade: ");
                return input.nextLine();
            }
            if(respostaEndereco.equalsIgnoreCase("Rua")){
                System.out.print("Entre com a Rua: ");
                return input.nextLine();
            }
        }
        return "Criterio não encontrado";
    }

    public static void findCriterion(String tipoPet, String criterio) {
        File pasta = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados");
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Não existem pets cadastrados!");
            return;
        }

        System.out.println("LISTA DE PETS ENCONTRADOS (Criterio Usado: " + criterio + " ) ");
        boolean hasOne = false;

        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                StringBuilder conteudoPet = new StringBuilder();
                boolean findTipo = false;
                boolean findCriterion = false;

                while ((linha = br.readLine()) != null) {
                    conteudoPet.append(linha).append("\n");

                    if(linha.toLowerCase().contains(tipoPet.toLowerCase())){
                        findTipo = true;
                    }

                    if (linha.toLowerCase().contains(criterio.toLowerCase())) {
                        findCriterion = true;
                    }
                }

                if (findCriterion && findTipo) {
                    System.out.println(conteudoPet.toString());
                    System.out.println("-------------------------------------------------");
                    hasOne = true;
                }

            } catch (IOException e) {
                System.out.println("Erro ao ler aquivo! ");
            }
        }

        if (!hasOne) {
            System.out.println("Não foi encontrado Pet com esse criterio!");
        }
    }

    public static void find2Criterion(String tipoPet, String criterio1, String criterio2) {
        File pasta = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\src\\Cadastro\\PetCadastrados");
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Não existem pets cadastrados!");
            return;
        }

        System.out.println("LISTA DE PETS ENCONTRADOS / Criterios Usados: " + criterio1 + " - " + criterio2);
        boolean hasOne = false;

        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                StringBuilder conteudoPet = new StringBuilder();
                boolean findTipo = false;
                boolean findCriterion1 = false;
                boolean findCriterion2 = false;

                while ((linha = br.readLine()) != null) {
                    conteudoPet.append(linha).append("\n");

                    if(linha.toLowerCase().contains(tipoPet.toLowerCase())){
                        findTipo = true;
                    }

                    if (linha.toLowerCase().contains(criterio1.toLowerCase()))  {
                        findCriterion1 = true;
                    }
                    if(linha.toLowerCase().contains(criterio2.toLowerCase())){
                        findCriterion2 = true;
                    }
                }

                if (findCriterion1 && findCriterion2 && findTipo) {
                    System.out.println(conteudoPet.toString());
                    System.out.println("-------------------------------------");
                    hasOne = true;
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

