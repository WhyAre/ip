package jank.ui;

import java.net.URL;
import java.util.ResourceBundle;

import jank.JankBot;
import jank.JankBotException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controls the main logic of the user interface
 */
public class MainController implements Initializable {
    private static final String TASK_FILE = "./data/jank.bin";
    private final JankBot bot;

    @FXML
    private TextField inputbox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox outputContainer;

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

        var userInput = inputbox.getText();

        // Create a horizontal line
        Separator sep = new Separator();
        // Adds user input box to the input
        outputContainer.getChildren().addAll(sep, new DialogBox("> %s".formatted(userInput)));

        var cmd = userInput.split(" ");

        if (cmd[0].equalsIgnoreCase("bye")) {
            Stage stage = (Stage) inputbox.getScene().getWindow();
            stage.close();
        }

        try {
            var out = bot.executeCommand(cmd);
            outputContainer.getChildren().addAll(new DialogBox(out));
        } catch (JankBotException e) {
            outputContainer.getChildren().addAll(new DialogBox(e.getMessage()));
        }

        inputbox.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var dialog = new DialogBox(bot.greet());
        outputContainer.getChildren().add(dialog);

        outputContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
