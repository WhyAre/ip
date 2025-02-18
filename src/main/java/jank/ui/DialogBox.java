package jank.ui;


import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DialogBox extends HBox {
    public DialogBox(String text) {
        var textlabel = new Text(text);
        textlabel.setFont(Font.font("Monospaced", 13));
        var textFlow = new TextFlow(textlabel);
        textFlow.setPadding(new Insets(2));
        textFlow.setLineSpacing(2);

        HBox.setHgrow(textFlow, Priority.ALWAYS);
        this.getChildren().add(textFlow);
    }

}
