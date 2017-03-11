package gui.executables.penproperties;

import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class PenSizeChooser extends ExecutableButton {
	protected Node penSizeButton;
	PenSizeChooser(TextAreaWriter t, FireableButton rb,Language l ){
		super(t,rb,l);
		createPenSizeChooser();
	}
	public Node getPenButtons(){
		return penSizeButton;
	}
	protected abstract void createPenSizeChooser();

}
