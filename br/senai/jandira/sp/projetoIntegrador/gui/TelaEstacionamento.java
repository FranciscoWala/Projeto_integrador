package br.senai.jandira.sp.projetoIntegrador.gui;

import br.senai.jandira.sp.projetoIntegrador.model.App;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TelaEstacionamento extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Estacionamento");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setResizable(false);


        VBox root = new VBox();
        root.setStyle("-fx-background-color: #1A1D58;");

        Scene scene = new Scene(root);

        VBox header = new VBox();
        header.setStyle("-fx-background-color: #1A1D58;");

        Label labelTitulo = new Label("ParKing");
        labelTitulo.setPadding(new Insets(8, 8, 0, 120));
        labelTitulo.setStyle("-fx-text-fill: #FF7700;-fx-font-size: 32;-fx-font-weight: bold;");

        Label labelSubtitulo = new Label("Station");
        labelSubtitulo.setPadding(new Insets(0, 8, 8, 220));
        labelSubtitulo.setStyle("-fx-text-fill: white; -fx-font-size: 32;");

        header.getChildren().addAll(labelTitulo, labelSubtitulo);

        Pane paneButtons = new Pane();
        paneButtons.setPadding(new Insets(0, 0, 0, 120));
        HBox boxBotoes = new HBox();
        boxBotoes.setSpacing(10);
        boxBotoes.setPadding(new Insets(8));
        paneButtons.getChildren().add(boxBotoes);

        paneButtons.setPadding(new Insets(16, 0, 16, 120));
        Button btnEntrada = new Button("ENTRADA");
        Button btnSaida = new Button("SAÍDA");


        btnEntrada.setOnAction(e -> {
            TelaEntradaVeiculo telaEntrada = new TelaEntradaVeiculo(stage);
            telaEntrada.mostrar();
        });

        btnSaida.setOnAction(e -> {
            TelaSaidaVeiculo telaSaida = new TelaSaidaVeiculo(stage);
            telaSaida.mostrar();
        });

        boxBotoes.getChildren().addAll(btnEntrada, btnSaida);
        VBox boxResultado = new VBox();
        boxResultado.setPrefHeight(10);
        boxResultado.setPrefWidth(10);

        Button btnMostrar = new Button("MOSTRAR VEÍCULOS ESTACIONADOS: ");
        btnMostrar.setStyle("-fx-background-color: #FF7700; -fx-text-fill: white; -fx-font-weight: bold;");

        TextArea txtResultado = new TextArea();
        txtResultado.setEditable(false);
        txtResultado.setPrefHeight(250);
        txtResultado.setStyle("-fx-control-inner-background: #2A2D68; -fx-text-fill: white; -fx-font-size: 13; -fx-font-family: 'Courier New';");
        txtResultado.setWrapText(true);

        btnMostrar.setOnAction(e -> {
            try {
                File arquivo = new File("br/senai/jandira/sp/projetoIntegrador/estacionamento.csv");
                Scanner leitor = new Scanner(arquivo);

                StringBuilder sb = new StringBuilder();
                sb.append("=== VEÍCULOS ESTACIONADOS ===\n\n");

                while (leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] dados = linha.split(";");

                    sb.append("Placa: ").append(dados[0]).append("\n");
                    sb.append("Modelo: ").append(dados[1]).append("\n");
                    sb.append("Proprietário: ").append(dados[2]).append("\n");
                    sb.append("Entrada: ").append(dados[3]).append("\n");
                    sb.append("-------------------------------\n");
                }

                txtResultado.setText(sb.toString());
                leitor.close();

            } catch (Exception ex) {
                txtResultado.setText("Erro ao carregar os dados:\n" + ex.getMessage());
            }
        });



        root.getChildren().addAll(header);
        root.getChildren().addAll(paneButtons);
        root.getChildren().addAll(btnMostrar);
        root.getChildren().addAll(boxResultado);
        root.getChildren().addAll(txtResultado);


        stage.setScene(scene);
        stage.show();
    }

}