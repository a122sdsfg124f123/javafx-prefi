package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TrafficLightController {

    @FXML private Circle redLight;
    @FXML private Circle yellowLight;
    @FXML private Circle greenLight;
    @FXML private Button toggleModeButton;
    @FXML private Button nextLightButton;

    private enum Light { RED, GREEN, YELLOW }
    private Light currentLight = Light.RED;

    private boolean isAutoMode = true;
    private Timeline autoTimeline;

    @FXML
    public void initialize() {
        updateLights(); // Set initial light
        setupAutoCycle(); // Start automatic cycle
    }

    private void setupAutoCycle() {
        autoTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> switchLight()),
                new KeyFrame(Duration.seconds(3)) // every 3 seconds
        );
        autoTimeline.setCycleCount(Timeline.INDEFINITE);
        if (isAutoMode) {
            autoTimeline.play();
        }
    }

    private void switchLight() {
        // Cycle through the lights in the correct order
        switch (currentLight) {
            case RED -> currentLight = Light.GREEN;
            case GREEN -> currentLight = Light.YELLOW;
            case YELLOW -> currentLight = Light.RED;
        }
        updateLights();
    }

    private void updateLights() {
        redLight.setFill(currentLight == Light.RED ? Color.RED : Color.web("#2f2f2f"));
        yellowLight.setFill(currentLight == Light.YELLOW ? Color.YELLOW : Color.web("#2f2f2f"));
        greenLight.setFill(currentLight == Light.GREEN ? Color.LIMEGREEN : Color.web("#2f2f2f"));
    }

    @FXML
    private void toggleMode() {
        isAutoMode = !isAutoMode;
        toggleModeButton.setText(isAutoMode ? "Switch to Manual" : "Switch to Auto");
        nextLightButton.setDisable(isAutoMode);

        if (isAutoMode) {
            autoTimeline.playFromStart();
        } else {
            autoTimeline.stop();
        }
    }

    @FXML
    private void nextLight() {
        if (!isAutoMode) {
            switchLight();
        }
    }
}
