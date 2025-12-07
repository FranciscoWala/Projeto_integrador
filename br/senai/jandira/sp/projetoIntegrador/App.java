package br.senai.jandira.sp.projetoIntegrador;

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

        try (FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv", true)) { //adiciona ao final do arquivo sem apagar o conteúdo anterior

            fw.write(placaDoVeiculo + ";" + modeloCarro + ";" + nomeProprietario + ";" + LocalDateTime.now() + "\n"); //modelo de como será registrado no arquivo csv e separador ";"
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
public void registrarSaida() { //captura a placa digitada pelo usuário e busca no arquivo de carros estacionados
    Scanner leitor = new Scanner(System.in);
    System.out.print("Digite a placa do veículo: ");
    String placaBuscada = leitor.nextLine().trim();

    try {
        Scanner arquivo = new Scanner(new File("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv")); // Abre o arquivo de carros estacionados
        boolean encontrou = false;

        while (arquivo.hasNextLine()) {  // le o arquivo linha por linha
            String linha = arquivo.nextLine();
            String[] dados = linha.split(";");

            if (dados[0].trim().equals(placaBuscada)) {// Se a placa for igual à buscada, processa a saída
                encontrou = true;

                LocalDateTime horaEntrada = LocalDateTime.parse(dados[3].trim());
                LocalDateTime horaSaida = LocalDateTime.now();

                long minutos = Duration.between(horaEntrada, horaSaida).toMinutes();
                double preco = calcularPreco(minutos);

                System.out.println("\n=== SAÍDA REGISTRADA ===");// resumo na tela
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
            System.out.println("Placa não encontrada!"); //mensagem caso a placa não seja encontrada
        }

        arquivo.close();

    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage()); //mensagem de erro
        e.printStackTrace();
    }
}

public void salvarNoHistorico(String[] dadosVeiculo, LocalDateTime horaSaida, long minutos, double preco) { // Salva os dados da saída no arquivo historico.csv
    try (FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/historico.csv", true);
         PrintWriter pw = new PrintWriter(fw)) {

        pw.println(dadosVeiculo[0] + ";" + // Escreve todos os dados no histórico
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

public void removerDoEstacionados(String placaRemover) { // Remove os dados do veículo do arquivo estacionamento.csv
    try {
        Scanner arquivo = new Scanner(new File("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv"));
        StringBuilder conteudo = new StringBuilder();

        while (arquivo.hasNextLine()) { // Reescreve o arquivo sem a placa que está saindo
            String linha = arquivo.nextLine();
            String[] dados = linha.split(";");

            if (!dados[0].trim().equals(placaRemover)) {// Se a placa não for a que deve ser removida, mantém no arquivo
                conteudo.append(linha).append("\n");
            }
        }
        arquivo.close();

        FileWriter fw = new FileWriter("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv"); // Sobrescreve o arquivo com os registros atualizados
        fw.write(conteudo.toString());
        fw.close();

        System.out.println("Removido do estacionamento!");

    } catch (Exception e) {
        System.out.println("Erro ao remover: " + e.getMessage());
    }
}
public double calcularPreco(long minutos) {// Calcula o valor a ser cobrado
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


