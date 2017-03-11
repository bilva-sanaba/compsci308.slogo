package GUI_Objects;

import GUI.TextAreaWriter;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PenToggle {
	private ButtonMaker myMaker;
	private Button penToggleButton;
	private TextAreaWriter myTextAreaWriter;
	private Button runButton;
	private boolean toggle=true;
	public PenToggle(TextAreaWriter t,Button r){
		myTextAreaWriter = t;
		runButton = r;
		myMaker = new ButtonMaker();
		createPenToggleButton();
		
		
	}
	public Node getToggleButton(){
		return penToggleButton;
	}

	private void createPenToggleButton() {
		penToggleButton = myMaker.createButton("Toggle Pen", e -> activate());
	}
	
	private void activate() {
		myTextAreaWriter.setText(getText()); 
		runButton.fire();
	}
	private String getText(){
		toggle = !toggle;
		if (toggle){
			return "penup";
		}else{
			return "pendown";
		}
	}
}
