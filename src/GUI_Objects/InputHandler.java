package GUI_Objects;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import model.UnmodifiableWorld;

public abstract class InputHandler {
	public abstract void handleKeyInput(KeyCode code,TextArea text, Button play, UnmodifiableWorld world);
}
