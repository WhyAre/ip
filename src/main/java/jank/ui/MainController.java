package jank.ui;

import java.net.URL;
import java.util.ResourceBundle;

import jank.JankBot;
import jank.JankBotException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controls the main logic of the user interface
 */
public class MainController implements Initializable {
    private static final String TASK_FILE = "./data/jank.txt";
    private final JankBot bot;

    @FXML
    private TextField inputbox;

    @FXML
    private TextArea outputbox;

    public MainController() {
        bot = new JankBot(TASK_FILE);
    }

    /**
     * Handles user commands when the user presses the enter key
     *
     * @param ke KeyEvent
     */
    @FXML
    public void inputOnKeyPressed(KeyEvent ke) {
        if (!ke.getCode().equals(KeyCode.ENTER)) {
            return;
        }

        outputbox.appendText("> %s\n".formatted(inputbox.getText()));

        var cmd = inputbox.getText().split(" ");

        if (cmd[0].equalsIgnoreCase("bye")) {
            Stage stage = (Stage) inputbox.getScene().getWindow();
            stage.close();
        }

        try {
            var out = bot.executeCommand(cmd);
            outputbox.appendText(out + "\n");
        } catch (JankBotException e) {
            outputbox.appendText(e.getMessage() + "\n");
        }

        inputbox.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputbox.setText(bot.greet());
    }
}
