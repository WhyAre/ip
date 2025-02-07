package jank;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController implements Initializable {
    @FXML
    private TextField inputbox;

    @FXML
    private TextArea outputbox;

    @FXML
    public void inputOnKeyPressed(KeyEvent ke) {
        if (!ke.getCode().equals(KeyCode.ENTER)) {
            return;
        }

        outputbox.appendText(inputbox.getText() + "\n");

        try {
            var out = JankBot.processCommand(inputbox.getText().split(" "));
            outputbox.appendText(out + "\n");
        } catch (JankBotException e) {
            outputbox.appendText(e.getMessage() + "\n");
        }

        inputbox.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputbox.setText(JankBot.greet());
    }
}
