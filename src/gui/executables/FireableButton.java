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
