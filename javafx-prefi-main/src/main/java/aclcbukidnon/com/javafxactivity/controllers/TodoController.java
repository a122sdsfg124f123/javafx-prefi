package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    @FXML
    private TextField todoInput;

    private ObservableList<String> items;

    @FXML
    public void initialize() {

    }

    @FXML
    public void onCreateClick() {
        String input = todoInput.getText().trim();
        if (!input.isEmpty()) {
            items.add(input);
            todoInput.clear();
        }
    }

    @FXML
    public void onDeleteClick() {
        String selectedItem = todoList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            items.remove(selectedItem);
        }
    }

    public void toggleMode(ActionEvent actionEvent) {
    }

    public void nextLight(ActionEvent actionEvent) {
    }
}
