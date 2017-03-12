/**
 * Large superclass for all clickable objects that enter text into a text area and run it 
 */
package gui.executables;

import java.util.ResourceBundle;

import gui.ButtonMaker;
import gui.language.Language;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
/**
 * With our current design, all GUI buttons that change the state of the displayed workspace work by
 * running a text command through the backend
 * The ExecutableButton class is abstract class that every GUI button should extend
 * @author Bilva
 *
 */
public abstract class ExecutableButton {
	protected TextAreaWriter myTextAreaWriter;
	protected FireableButton runButton;
	protected Language myLanguage;
	protected ResourceBundle myResources;
	protected ButtonMaker myButtonMaker = new ButtonMaker();
	/**
	 *  The parameters are needed to enter the correct language text into the text area, run the command
	 * @param t The object which holds the text area for commands
	 * @param rb An encapsulation of the runbutton which only allows firing of the runbutton
	 * @param l The language currently being used by the workspace
	 */
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
