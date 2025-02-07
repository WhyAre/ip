package jank;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController implements Initializable {
    @FXML
    private TextField inputbox;

    @FXML
    public void inputOnKeyPressed(KeyEvent ke) {
        if (!ke.getCode().equals(KeyCode.ENTER)) {
            return;
        }

        try {
            JankBot.processCommand(inputbox.getText().split(" "));
        } catch (JankBotException e) {
        }

        inputbox.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
