package br.senai.jandira.sp.projetoIntegrador.model;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    public String placaDoVeiculo; //declarando variáveis públicas
    public String modeloCarro;
    public String nomeProprietario;

    public void obterDados() {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Placa do Veículo: "); //prints para o usuário registrar os dados de entrada
        placaDoVeiculo = leitor.nextLine();

        System.out.print("Modelo do Veículo: ");
        modeloCarro = leitor.nextLine();

        System.out.print("Nome do Proprietário: ");
        nomeProprietario = leitor.nextLine();

        salvarNoCSV();
    }

    public void salvarNoCSV() { //salvar o registro de entrada do veículo em estacionamento.csv

        try (FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv", true)) {

            fw.write(placaDoVeiculo + ";" + modeloCarro + ";" + nomeProprietario + ";" + LocalDateTime.now() + "\n"); //modelo de como será registrado no arquivo csv e separador ";"
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public String buscarESair(String placaBuscada) {
        try {
            Scanner arquivo = new Scanner(new File("estacionados.csv"));

            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine();
                String[] dados = linha.split(",");

                if (dados[0].trim().equalsIgnoreCase(placaBuscada.trim())) {
                    LocalDateTime horaEntrada = LocalDateTime.parse(dados[3].trim());
                    LocalDateTime horaSaida = LocalDateTime.now();

                    long minutos = Duration.between(horaEntrada, horaSaida).toMinutes();
                    double preco = calcularPreco(minutos);

                    // Salvar no histórico e remover de estacionados
                    salvarNoHistorico(dados, horaSaida, minutos, preco);
                    removerDoEstacionados(placaBuscada);

                    arquivo.close();

                    return String.format(
                            "═══════════════════════════\n" +
                                    "   SAÍDA REGISTRADA\n" +
                                    "═══════════════════════════\n\n" +
                                    "Placa: %s\n" +
                                    "Modelo: %s\n" +
                                    "Proprietário: %s\n\n" +
                                    "Tempo: %d minutos\n" +
                                    "Valor: R$ %.2f\n\n" +
                                    "═══════════════════════════",
                            dados[0].trim(), dados[1].trim(), dados[2].trim(), minutos, preco
                    );
                }
            }
            arquivo.close();
            return "Placa não encontrada nos registros!";

        } catch (Exception e) {
            return "Erro ao buscar: " + e.getMessage();
        }
    }

    public void registrarSaida() {
        Scanner leitor = new Scanner(System.in);
        System.out.print("Digite a placa do veículo: ");
        String placaBuscada = leitor.nextLine().trim();

        try {
            Scanner arquivo = new Scanner(new File("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv"));
            boolean encontrou = false;

            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine();
                String[] dados = linha.split(";");

                if (dados[0].trim().equals(placaBuscada)) {
                    encontrou = true;

                    LocalDateTime horaEntrada = LocalDateTime.parse(dados[3].trim());
                    LocalDateTime horaSaida = LocalDateTime.now();

                    long minutos = Duration.between(horaEntrada, horaSaida).toMinutes();
                    double preco = calcularPreco(minutos);

                    System.out.println("\n=== SAÍDA REGISTRADA ===");
                    System.out.println("Placa: " + dados[0]);
                    System.out.println("Modelo: " + dados[1]);
                    System.out.println("Tempo: " + minutos + " minutos");
                    System.out.println("Valor: R$ " + String.format("%.2f", preco));

                    salvarNoHistorico(dados, horaSaida, minutos, preco);
                    removerDoEstacionados(placaBuscada);

                    break;
                }
            }

            if (!encontrou) {
                System.out.println("Placa não encontrada!");
            }

            arquivo.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvarNoHistorico(String[] dadosVeiculo, LocalDateTime horaSaida, long minutos, double preco) {
        try (FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/historico.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(dadosVeiculo[0] + ";" +
                    dadosVeiculo[1] + ";" +
                    dadosVeiculo[2] + ";" +
                    dadosVeiculo[3] + ";" +
                    horaSaida + ";" +
                    minutos + ";" +
                    preco);

            System.out.println("Salvo no histórico!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public void removerDoEstacionados(String placaRemover) {
        try {
            Scanner arquivo = new Scanner(new File("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv"));
            StringBuilder conteudo = new StringBuilder();

            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine();
                String[] dados = linha.split(";");

                if (!dados[0].trim().equals(placaRemover)) {
                    conteudo.append(linha).append("\n");
                }
            }
            arquivo.close();

            FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv");
            fw.write(conteudo.toString());
            fw.close();

            System.out.println("Removido do estacionamento!");

        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public double calcularPreco(long minutos) {
        if (minutos <= 60) {
            return 10.0;
        }

        long minutosExtras = minutos - 60;
        long horasCompletasExtras = minutosExtras / 60;          // quantas horas completas depois da primeira
        long restoMinutos = minutosExtras % 60;                  // minutos dentro da última hora

        double valor = 10.0 + (horasCompletasExtras * 5.0);

        // se passar de 5 min, cobrar mais R$5
        if (restoMinutos >= 5) {
            valor += 5.0;
        }

        return valor;
    }
}