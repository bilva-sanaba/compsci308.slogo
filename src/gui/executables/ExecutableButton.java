/**
 * Large superclass for all clickable objects that enter text into a text area and run it 
 */
package gui.executables;

import java.util.ResourceBundle;

import gui.ButtonMaker;
import gui.language.Language;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public abstract class ExecutableButton {
	protected TextAreaWriter myTextAreaWriter;
	protected FireableButton runButton;
	protected Language myLanguage;
	protected ResourceBundle myResources;
	protected ButtonMaker myButtonMaker = new ButtonMaker();
	public ExecutableButton(TextAreaWriter t, FireableButton rb, Language l){
		myTextAreaWriter = t;
		runButton = rb;
		myLanguage = l; 
		myResources=ResourceBundle.getBundle(controller.Controller.DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
	}
	protected void activate(){
		 myResources=ResourceBundle.getBundle(controller.Controller.DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		 myTextAreaWriter.setText(getText()); 
		 runButton.fire();
	 }
	protected abstract String getText();
}
