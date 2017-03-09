package GUI_Objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ButtonMaker {
	public Button createButton(String label, EventHandler<ActionEvent> e) {
		Button b = new Button();
		b.setText(label);
		b.setOnAction(e);
		return b;
	}
	public Label createLabel(String text) {
		Label label = new Label(text);
		label.setTextFill(Color.BLACK);
		return label;  
	}
}
