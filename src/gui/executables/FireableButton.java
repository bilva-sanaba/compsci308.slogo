
package gui.executables;

import javafx.scene.control.Button;
/**
 * An encapsulating version of a button which passes down only what is needed to run a button
 * @author Bilva
 *
 */
public class FireableButton {
	private Button fireButton;
	public FireableButton(Button button){
		fireButton = button;
	}
	/**
	 * Runs the button
	 */
	public void fire(){
		fireButton.fire();
	}
}
