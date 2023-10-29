package com.example.calculadorajava;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    private Button botonmulti;

    @FXML
    private GridPane cientifica;

    @FXML
    private Button siete;

    @FXML
    private Button log;

    @FXML
    private Button cos;

    @FXML
    private Button dos;

    @FXML
    private Button seis;

    @FXML
    private Button botonigual;

    @FXML
    private Button botonhistorial;

    @FXML
    private Button cinco;

    @FXML
    private TextField pantalla;

    @FXML
    private BorderPane root;

    @FXML
    private Button sin;

    @FXML
    private Button tres;

    @FXML
    private Button tan;

    @FXML
    private Button botondiv;

    @FXML
    private Button ocho;

    @FXML
    private Button nueve;

    @FXML
    private Button cuatro;

    @FXML
    private Button botonrestar;

    @FXML
    private Button cero;

    @FXML
    private Button borrar;

    @FXML
    private Button botoncientifica;

    @FXML
    private Button botonsumar;

    @FXML
    private Button uno;

    @FXML
    private TextArea historial;



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            acciones();
            instancias();
            root.setLeft(null);
            root.setRight(null);
            historial.setDisable(true);


        }

        private void instancias(){
            uno.setOnAction(e -> imprimirNumero("1"));
            dos.setOnAction(e -> imprimirNumero("2"));
            tres.setOnAction(e -> imprimirNumero("3"));
            cuatro.setOnAction(e -> imprimirNumero("4"));
            cinco.setOnAction(e -> imprimirNumero("5"));
            seis.setOnAction(e -> imprimirNumero("6"));
            siete.setOnAction(e -> imprimirNumero("7"));
            ocho.setOnAction(e -> imprimirNumero("8"));
            nueve.setOnAction(e -> imprimirNumero("9"));
            cero.setOnAction(e -> imprimirNumero("0"));

            botonsumar.setOnAction(e -> handleOperaciones("+"));
            botonrestar.setOnAction(e -> handleOperaciones("-"));
            botonmulti.setOnAction(e -> handleOperaciones("x"));
            botondiv.setOnAction(e -> handleOperaciones("/"));
            botonigual.setOnAction(e -> handleOperaciones("="));
        }

        private void acciones(){
            botoncientifica.setOnAction( this);
            botonhistorial.setOnAction(this);
            borrar.setOnAction(this);
            sin.setOnAction(this::handleFuncion);
            cos.setOnAction(this::handleFuncion);
            tan.setOnAction(this::handleFuncion);
            log.setOnAction(this::handleFuncion);
        }

            @Override

            public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == botoncientifica) {
                if (root.getLeft() != null) {
                    root.setLeft(null);
                } else {
                    root.setLeft(cientifica);
                }
            } else if (actionEvent.getSource() == botonhistorial) {
                if (root.getRight() != null) {
                    root.setRight(null);
                } else {
                    root.setRight(historial);
                }
            } else if (actionEvent.getSource() == borrar) {
                pantalla.clear();

            }
        }
        private double num1 = 0;
        private String operacion = "";



        private void handleOperaciones(String op) {
            if (!operacion.isEmpty() || op.equals("=")) {
                double num2 = Double.parseDouble(pantalla.getText());
                double result = 0;

                switch (operacion) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "x":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            pantalla.setText("Error");
                            return;
                        }
                        break;
                }

                pantalla.setText(String.valueOf(result));
                historial.appendText(num1 + " " + operacion + " " + num2 + " = " + result + "\n");
                operacion = "";
                num1 = result;
            } else {
                num1 = Double.parseDouble(pantalla.getText());
                operacion = op;
                pantalla.setText("");
            }
        }

        @FXML
        public void handleFuncion(ActionEvent event) {
            Button button = (Button) event.getSource();
            String btnText = button.getText();

            double num = Double.parseDouble(pantalla.getText());
            double resultado = 0;

            switch (btnText) {
                case "log":
                    if (num > 0) {
                        resultado = Math.log10(num);
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
                case "sin":
                    resultado = Math.sin(Math.toRadians(num));
                    break;
                case "cos":
                    resultado = Math.cos(Math.toRadians(num));
                    break;
                case "tan":
                    if (Math.cos(Math.toRadians(num)) != 0) {
                        resultado = Math.tan(Math.toRadians(num));
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
            }

            pantalla.setText(String.valueOf(resultado));
            historial.appendText(btnText + "(" + num + ")" + " = " + resultado + "\n");
        }
        private void imprimirNumero(String number) {
            String oldText = pantalla.getText();
            String newText = oldText + number;
            pantalla.setText(newText);
        }
}








