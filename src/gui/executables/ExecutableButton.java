package gui.executables;
import java.util.ResourceBundle;
import gui.ButtonMaker;
import gui.language.Language;
/**
 * With our current design, all GUI buttons that change the state of the displayed workspace work by
 * running a text command through the backend
 * The ExecutableButton class is abstract class that every GUI button should extend
 * @author Bilva
 */
public abstract class ExecutableButton {
	protected TextAreaWriter myTextAreaWriter;
	protected FireableButton myRunButton;
	protected Language myLanguage;
	protected ResourceBundle myCommandResources;
	protected ResourceBundle myButtonResources = controller.Controller.BUTTON_RESOURCE_BUNDLE;
	protected ButtonMaker myButtonMaker = new ButtonMaker();
	/**
	 *  The parameters are needed to enter the correct language text into the text area, run the command
	 * @param textAreaWriter The object which holds the text area for commands
	 * @param fireableRunButton An encapsulation of the runbutton which only allows firing of the runbutton
	 * @param currentLanguage The language currently being used by the workspace
	 */
	public ExecutableButton(TextAreaWriter textAreaWriter, FireableButton fireableRunButton, Language currentLanguage){
		myTextAreaWriter = textAreaWriter;
		myRunButton = fireableRunButton;
		myLanguage = currentLanguage; 
	}
	/**
	 * Runs the command written by getText()
	 */
	protected void executeCommand(){
		myCommandResources=ResourceBundle.getBundle(controller.Controller.DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		myTextAreaWriter.setText(getText()); 
		myRunButton.fire();
	}
	protected abstract String getText();
}