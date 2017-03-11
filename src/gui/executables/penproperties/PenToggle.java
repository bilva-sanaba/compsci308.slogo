package gui.executables.penproperties;

import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PenToggle extends ExecutableButton{

	private Button penToggleButton;
	private boolean toggle=true;
	public PenToggle(TextAreaWriter t,FireableButton r,Language l){
		super(t,r,l);
		createPenToggleButton();
	}
	public Node getToggleButton(){
		return penToggleButton;
	}

	private void createPenToggleButton() {
		penToggleButton = myButtonMaker.createButton("Toggle Pen", e -> activate());
	}
	
	protected String getText(){
		toggle = !toggle;
		if (toggle){
			String command=myResources.getString("PenDown").split("\\|")[0];
			return command;
		}else{
			String command=myResources.getString("PenUp").split("\\|")[0];
			return command;
		}
	}
}
