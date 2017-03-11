package GUI_Objects;

import java.util.Collection;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import model.UnmodifiableWorld;

public class WASDMover extends InputHandler {
	@Override
	public void handleKeyInput(KeyCode code, TextArea textArea, Button runButton, UnmodifiableWorld world) {
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
	private String collectionToString(Collection<Integer> x){
		String returnString = "[ ";
		for (Integer i : x){
			returnString = returnString + i.toString() + " ";
		}
		returnString = returnString + "]";
		return returnString;
	}
}
