package br.senai.jandira.sp.projetoIntegrador.gui;

import br.senai.jandira.sp.projetoIntegrador.model.App;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaSaidaVeiculo {

    private Stage stage;
    private App app;

    public TelaSaidaVeiculo(Stage stage) {
        this.stage = stage;
        this.app = new App();
    }

    public void mostrar() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1A1D58;");

        // Título
        Label titulo = new Label("REGISTRAR SAÍDA");
        titulo.setStyle("-fx-text-fill: #FF7700; -fx-font-size: 24; -fx-font-weight: bold;");

        // Campo de busca
        Label lblPlaca = new Label("Placa do Veículo:");
        lblPlaca.setStyle("-fx-text-fill: white; -fx-font-size: 14;");

        TextField txtPlaca = new TextField();
        txtPlaca.setPromptText("Digite a placa (ex: ABC1234)");
        txtPlaca.setStyle("-fx-font-size: 14;");

        // Botão calcular
        Button btnCalcular = new Button("CALCULAR SAÍDA");
        btnCalcular.setStyle("-fx-background-color: #FF7700; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14;");
        btnCalcular.setPrefWidth(200);
        btnCalcular.setPrefHeight(40);

        // Área de resultado
        TextArea txtResultado = new TextArea();
        txtResultado.setEditable(false);
        txtResultado.setPrefHeight(250);
        txtResultado.setStyle("-fx-control-inner-background: #2A2D68; -fx-text-fill: white; -fx-font-size: 13; -fx-font-family: 'Courier New';");
        txtResultado.setWrapText(true);

        // Botão voltar
        Button btnVoltar = new Button("VOLTAR AO MENU");
        btnVoltar.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 14;");
        btnVoltar.setPrefWidth(200);
        btnVoltar.setPrefHeight(40);

        // Ação do botão CALCULAR
        btnCalcular.setOnAction(e -> {
            String placa = txtPlaca.getText().trim();

            if (placa.isEmpty()) {
                txtResultado.setText("Por favor, digite uma placa!");
                txtResultado.setStyle("-fx-control-inner-background: #2A2D68; -fx-text-fill: #FF0000; -fx-font-size: 14;");
            } else {
                // Chama o método que faz TUDO: busca, calcula, salva histórico, remove
                String resultado = app.buscarESair(placa);
                txtResultado.setText(resultado);
                txtResultado.setStyle("-fx-control-inner-background: #2A2D68; -fx-text-fill: white; -fx-font-size: 13; -fx-font-family: 'Courier New';");

                // Limpar o campo
                txtPlaca.clear();
            }
        });

        // Ação do botão VOLTAR
        btnVoltar.setOnAction(e -> {
            TelaEstacionamento telaEstacionamento = new TelaEstacionamento();
            telaEstacionamento.start(stage);
        });

        // Adicionar tudo ao root
        root.getChildren().addAll(
                titulo,
                lblPlaca,
                txtPlaca,
                btnCalcular,
                txtResultado,
                btnVoltar
        );

        Scene scene = new Scene(root, 500, 550);
        stage.setScene(scene);
    }
}