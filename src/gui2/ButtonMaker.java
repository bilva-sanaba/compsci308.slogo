package gui2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
/**
 * Used to make buttons for different purposes
 * @author Bilva
 *
 */
public class ButtonMaker {
	/**
	 * @param label String intended to be displayed on button
	 * @param e Lambda expression containing method that should be executed on click; 
	 * @returns the Button generated
	 */
	public Button createButton(String label, EventHandler<ActionEvent> e) {
		Button b = new Button();
		b.setText(label);
		b.setOnAction(e);
		return b;
	}
	/**
	 * Turns String into label for display on Button
	 * @param text desired to be displayed
	 * @return Label generated
	 */
	public Label createLabel(String text) {
		Label label = new Label(text);
		label.setTextFill(Color.BLACK);
		return label;  
	}
}
