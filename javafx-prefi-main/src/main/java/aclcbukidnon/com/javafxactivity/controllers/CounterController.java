package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class CounterController {

    @FXML private Label labelCount;
    @FXML private TextField inputField;
    @FXML private Button startButton;
    @FXML private Button pauseButton;
    @FXML private Button stopButton;

    private int counter = 0;
    private Timeline timeline;

    @FXML
    public void initialize() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateCounter())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void updateCounter() {
        if (counter > 0) {
            counter--;
            labelCount.setText(String.valueOf(counter));
        } else {
            timeline.stop(); // Stop when countdown reaches 0
        }
    }

    @FXML
    private void onStartClick() {
        try {
            if (!timeline.getStatus().equals(Timeline.Status.RUNNING)) {
                if (!inputField.getText().isEmpty()) {
                    counter = Integer.parseInt(inputField.getText());
                    if (counter < 0) {
                        labelCount.setText("Must be ≥ 0");
                        return;
                    }
                    labelCount.setText(String.valueOf(counter));
                }
                timeline.play();
            }
        } catch (NumberFormatException e) {
            labelCount.setText("Invalid!");
        }
    }

    @FXML
    private void onPauseClick() {
        timeline.pause();
    }

    @FXML
    private void onStopClick() {
        timeline.stop();
        counter = 0;
        labelCount.setText("0");
    }
}
