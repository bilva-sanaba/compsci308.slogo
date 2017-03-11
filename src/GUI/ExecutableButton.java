package GUI;

import java.util.ResourceBundle;

import GUI_Objects.ButtonMaker;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public abstract class ExecutableButton {
	protected TextAreaWriter myTextAreaWriter;
	protected Button runButton;
	protected Language myLanguage;
	protected ResourceBundle myResources;
	protected ButtonMaker myButtonMaker = new ButtonMaker();
	public ExecutableButton(TextAreaWriter t, Button rb, Language l){
		myTextAreaWriter = t;
		runButton = rb;
		myLanguage = l; 
		myResources=ResourceBundle.getBundle(Controller.Controller.DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
	}
	protected void activate(){
		 myResources=ResourceBundle.getBundle(Controller.Controller.DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		 myTextAreaWriter.setText(getText()); 
		 runButton.fire();
	 }
	protected abstract String getText();
}
