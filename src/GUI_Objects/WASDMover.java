package GUI_Objects;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class WASDMover extends InputHandler {
	@Override
	public void handleKeyInput(KeyCode code, TextArea textArea, Button runButton) {
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
