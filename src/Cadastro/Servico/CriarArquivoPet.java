package Cadastro.Servico;

import Cadastro.Dominio.Endereco;
import Cadastro.Dominio.Sexo;
import Cadastro.Dominio.Tipo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivoPet {
    public static void criar(File file, String nomeTemp, Tipo tipoTemp, Sexo sexoTemp, Endereco endereco, double idadeTemp, int mesesTemp, double pesoTemp, String racaTemp){
        try(FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw)){
            file.createNewFile();
            bw.write("1 - " + nomeTemp);
            bw.newLine();
            bw.write("2 - " + tipoTemp.TIPO_NOME);
            bw.newLine();
            bw.write("3 - " + sexoTemp.NAME_SEXO);
            bw.newLine();
            bw.write("4 - " + endereco);
            bw.newLine();

            if(idadeTemp > 0){
                int idadeInteira = (int) idadeTemp;
                bw.write("5 - " + idadeInteira + " anos");
                bw.newLine();
            }else{
                bw.write("5 - Não completou 1 ano e tem: " + mesesTemp + " meses");
                bw.newLine();
            }

            bw.write("6 - " + pesoTemp + "Kg");
            bw.newLine();
            bw.write("7 - " + racaTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
