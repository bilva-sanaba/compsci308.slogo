/**
 * Encpasulating object for button (needed so executable buttons can use run button without full access)
 */
package gui.executables;

import javafx.scene.control.Button;

public class FireableButton {
	private Button fireButton;
	public FireableButton(Button button){
		fireButton = button;
	}
	public void fire(){
		fireButton.fire();
	}
}
