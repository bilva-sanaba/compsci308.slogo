/**
 * Abstract class which allows different sets of key instructions.
 */
package gui.executables.keymouse;

import gui.executables.FireableButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import model.UnmodifiableWorld;

public abstract class InputHandler {
	public abstract void handleKeyInput(KeyCode code,TextArea text, FireableButton play, UnmodifiableWorld world);
}
