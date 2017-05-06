package gui.executables.keymouse;

import java.util.Collection;
import java.util.List;

import gui.executables.FireableButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import model.UnmodifiableWorld;
/**
 * Subclass of InputHandler which handles key inputs by running various command lines
 * @author Bilva
 *
 */
public class WASDMover extends InputHandler {
	/**
	 * runs these commands given certain Keycodes. 
	 */
	@Override
	public void handleKeyInput(KeyCode code, TextArea textArea, FireableButton runButton, UnmodifiableWorld world) {
		if (code == KeyCode.W){
			textArea.setText("fd 100");
			runButton.fire();
		}
		if (code == KeyCode.S){
			textArea.setText("back 100");
			runButton.fire();
		}
		if (code == KeyCode.A){
			textArea.setText("left 90");
			runButton.fire();
		}
		if (code == KeyCode.D){
			textArea.setText("right 90");
			runButton.fire();
		}
	}
}
