package br.senai.jandira.sp.projetoIntegrador.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        paneButtons.setPadding(new Insets(0, 0, 0, 8));
        HBox boxBotoes = new HBox();
        boxBotoes.setSpacing(10);
        boxBotoes.setPadding(new Insets(8));
        paneButtons.getChildren().add(boxBotoes);

        paneButtons.setPadding(new Insets(16, 0, 16, 8));
        Button botaoEntrada = new Button("ENTRADA");
        Button botaoSaida = new Button("SAÍDA");

        botaoEntrada.setOnAction(e -> {
            TelaEntradaVeiculo telaEntrada = new TelaEntradaVeiculo(stage);
            telaEntrada.mostrar();
        });

        botaoSaida.setOnAction(e -> {
            TelaSaidaVeiculo telaSaida = new TelaSaidaVeiculo(stage);
            telaSaida.mostrar();
        });

        boxBotoes.getChildren().addAll(botaoEntrada, botaoSaida);

        VBox boxResultado = new VBox();
        boxResultado.setPrefHeight(300);

        Label labelResultado = new Label("VEÍCULOS ESTACIONADOS: ");
        labelResultado.setPadding(new Insets(8, 8, 8, 8));
        labelResultado.setStyle("-fx-text-fill: white;-fx-font-size: 18");

        ListView listaCarrosEstacionados = new ListView();
        listaCarrosEstacionados.setPadding(new Insets(8));

        boxResultado.getChildren().addAll(labelResultado, listaCarrosEstacionados);

        root.getChildren().addAll(header);
        root.getChildren().addAll(paneButtons);
        root.getChildren().addAll(boxResultado);

        stage.setScene(scene);
        stage.show();
    }

}