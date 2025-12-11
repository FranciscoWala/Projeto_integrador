package br.senai.jandira.sp.projetoIntegrador.gui;

import br.senai.jandira.sp.projetoIntegrador.model.App;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEntradaVeiculo {

    private Stage stage;
    private App app;

    public TelaEntradaVeiculo(Stage stage) {
        this.stage = stage;
        this.app = new App();
    }

    public void mostrar() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1A1D58;");

        Label titulo = new Label("ParKing");
        titulo.setStyle("-fx-text-fill: #FF7700; -fx-font-size: 24; -fx-font-weight: bold;");
        Label subtitulo = new Label("Station");
        subtitulo.setStyle("-fx-text-fill: white; -fx-font-size: 24; -fx-font-weight: bold;");


        Label lblPlaca = new Label("");
        lblPlaca.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
        TextField txtPlaca = new TextField();
        txtPlaca.setPromptText("Placa do Veículo:");

        Label lblModelo = new Label("Modelo:");
        lblModelo.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
        TextField txtModelo = new TextField();
        txtModelo.setPromptText("Ex: Gol");

        Label lblProprietario = new Label("Proprietário:");
        lblProprietario.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
        TextField txtProprietario = new TextField();
        txtProprietario.setPromptText("Ex: João Silva");

        Button btnSalvar = new Button("SALVAR");
        btnSalvar.setStyle("-fx-background-color: #FF7700; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSalvar.setPrefWidth(150);

        Button btnVoltar = new Button("VOLTAR");
        btnVoltar.setStyle("-fx-background-color: #555; -fx-text-fill: white;");
        btnVoltar.setPrefWidth(150);

        Label lblResultado = new Label();

        btnSalvar.setOnAction(e -> {
            if (txtPlaca.getText().trim().isEmpty() ||
                    txtModelo.getText().trim().isEmpty() ||
                    txtProprietario.getText().trim().isEmpty()) {

                lblResultado.setText("Preencha todos os campos!");
                lblResultado.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: bold;");
            } else {
                app.placaDoVeiculo = txtPlaca.getText().trim();
                app.modeloCarro = txtModelo.getText().trim();
                app.nomeProprietario = txtProprietario.getText().trim();

                app.salvarNoCSV();

                lblResultado.setText("Veículo estacionado!");
                lblResultado.setStyle("-fx-text-fill: #00FF00; -fx-font-weight: bold;");

                txtPlaca.clear();
                txtModelo.clear();
                txtProprietario.clear();
            }
        });

        btnVoltar.setOnAction(e -> {
            TelaEstacionamento telaEstacionamento = new TelaEstacionamento();
            telaEstacionamento.start(stage);
        });

        root.getChildren().addAll(
                titulo,
                subtitulo,
                lblPlaca, txtPlaca,
                lblModelo, txtModelo,
                lblProprietario, txtProprietario,
                btnSalvar,
                btnVoltar,
                lblResultado
        );

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
    }
}