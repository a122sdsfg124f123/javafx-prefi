package aclcbukidnon.com.javafxactivity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField text_result;

    private String operator = "";
    private double firstNumber = 0;
    private boolean startNewNumber = true;

    @FXML
    void Number(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();

        if (startNewNumber) {
            text_result.setText("");
            startNewNumber = false;
        }

        text_result.setText(text_result.getText() + value);
    }

    @FXML
    void Operation(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();

        switch (value) {
            case "AC":
                text_result.setText("00");
                operator = "";
                firstNumber = 0;
                startNewNumber = true;
                break;
            case "+":
            case "-":
            case "X":
            case "÷":
                operator = value;
                firstNumber = Double.parseDouble(text_result.getText());
                startNewNumber = true;
                break;
            case "=":
                double secondNumber = Double.parseDouble(text_result.getText());
                double result = calculate(firstNumber, secondNumber, operator);
                text_result.setText(String.valueOf(result));
                startNewNumber = true;
                break;
            case "%":
                double current = Double.parseDouble(text_result.getText());
                text_result.setText(String.valueOf(current / 100));
                startNewNumber = true;
                break;
            case ".":
                if (!text_result.getText().contains(".")) {
                    text_result.setText(text_result.getText() + ".");
                    startNewNumber = false;
                }
                break;
        }
    }

    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "X" -> a * b;
            case "÷" -> b != 0 ? a / b : 0;
            default -> 0;
        };
    }
}
