package GUI_Objects;

import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class PenSizeChooser {
	private static final double DEFAULT_PEN_SIZE = 4;
	protected double penSize = DEFAULT_PEN_SIZE;
	protected Node penSizeButton;
	protected TextAreaWriter myTextAreaWriter;
	protected Button runButton;
	PenSizeChooser(TextAreaWriter t, Button rb){
		myTextAreaWriter=t;
		runButton=rb;
		createPenSizeChooser();
	}
	public Node getPenButtons(){
		return penSizeButton;
	}
	protected abstract void createPenSizeChooser();

}
