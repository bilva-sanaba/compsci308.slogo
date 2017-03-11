package GUI_Objects;

import GUI.ExecutableButton;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class PenSizeChooser extends ExecutableButton {
	protected Node penSizeButton;
	PenSizeChooser(TextAreaWriter t, Button rb,Language l ){
		super(t,rb,l);
		createPenSizeChooser();
	}
	public Node getPenButtons(){
		return penSizeButton;
	}
	protected abstract void createPenSizeChooser();

}
