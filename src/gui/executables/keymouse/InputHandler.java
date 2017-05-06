package gui.executables.keymouse;

import gui.executables.FireableButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import model.UnmodifiableWorld;
/**
 *Abstract class which runs a method based on a keyCode and the current world
 *
 * @author Bilva
 *
 */
public abstract class InputHandler {
	/**
	 * 
	 * @param code
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 */
	public abstract void handleKeyInput(KeyCode code,TextArea text, FireableButton play, UnmodifiableWorld world);
}
