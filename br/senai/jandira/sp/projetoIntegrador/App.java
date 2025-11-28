package br.senai.jandira.sp.projetoIntegrador;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    public String placaDoVeiculo;
    public String modeloCarro;
    public String nomeProprietario;

    public void ObterDados(){
        Scanner leitor = new Scanner(System.in);

        System.out.print("Placa do Veículo:");
        placaDoVeiculo = leitor.nextLine();

        System.out.print("Modelo do Veículio:");
        modeloCarro = leitor.nextLine();

        System.out.print("Nome do Proprietário:");
        nomeProprietario = leitor.nextLine();

        salvarNoCSV();

    }

    public void salvarNoCSV() {

        try (FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv", true)) {

            fw.write(placaDoVeiculo + " ; " + modeloCarro + " ; " + nomeProprietario + " ; " + LocalDateTime.now() + "\n");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

