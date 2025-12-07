package br.senai.jandira.sp.projetoIntegrador.model;

import br.senai.jandira.sp.projetoIntegrador.App;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        Scanner leitor = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SISTEMA DE ESTACIONAMENTO ==="); //prints de opções para o usuário
            System.out.println("1 - Registrar Entrada");
            System.out.println("2 - Registrar Saída");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = leitor.nextInt();
            leitor.nextLine(); // Limpar buffer

            if (opcao == 1) { //opção de registrar veículo
                app.obterDados();
            } else if (opcao == 2) { //opção de saída de veículo
                app.registrarSaida();
            } else if (opcao == 0) { //sair da aplicação
                System.out.println("Saindo...");
                break;
            }
        }
    }
}