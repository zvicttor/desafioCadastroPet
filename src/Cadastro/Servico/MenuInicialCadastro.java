package Cadastro.Servico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class MenuInicialCadastro {
    public static void exibirMenu() {
        System.out.println();
        File file = new File("C:\\Users\\Victo\\Documents\\DevVictor\\Projetos\\DesafioCadastroPet\\desafioCadastroPet\\menuInicial.txt");
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while((linha = br.readLine()) != null){
                System.out.println(linha);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
