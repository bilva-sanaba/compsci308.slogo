// This entire file is part of my masterpiece.
// Bilva Sanaba
// Note: I did not include comments in my line count (unclear if I was supposed to)
// This class utilizes the command design pattern.
// This abstract class is extended by every node in the GUI that must function by entering and running a command 
//     when activated. It does this by getting the appropriate text command in the correct language, setting it to 
//     the text area, and then firing the play button. 
// The fact that it was noticed that many GUI components change the environment in a way that can be done with slogo
//     commands allows for the extraction of code into a super class and a major reduction in duplicate code. In particular, 
// 	   ColorChooser, TurtleComboBox, ClickHandler, and InputHandler all extend the class.
// Encapsulation in the class also highlights the good design. The class requires running a command and due to our design
//      this requires writing text into the textarea and then firing the runbutton. Rather than both these two notes being
// 		passed in, they are encapsulated into a fireableRunButton and textAreaWriter. Both classes only have methods
//      to fire the button or write in text, respectively. 
// For readability the class also has instance variables for resourcebundles so that the static calls will not have to be 
//      written out each time. (Lines 30 and 31)
// Finally a ButtonMaker class which acts as a factory design pattern was instantiated here as a protected variable so that the various 
//      subclasses can make similar buttons without duplicate code. 
// 21 lines
package gui.executables;
import java.util.ResourceBundle;
import gui.ButtonMaker;
import gui.language.Language;
/** This abstract class contains the code needed to run a slogo command. All that is needed by the concrete classes
 * is a getText() method so that the appropriate commmand can be run. (Extended by ColorChooser, TurtleComboBox, ClickHandler, and InputHandler
 * @author Bilva
 */
public abstract class ExecutableButton {
	protected TextAreaWriter myTextAreaWriter;
	protected FireableButton myRunButton;
	protected Language myLanguage;
	protected ResourceBundle myCommandResources;
	protected ResourceBundle myButtonResources = controller.Controller.BUTTON_RESOURCE_BUNDLE;
	protected ResourceBundle myAlertResources = controller.Controller.ALERT_RESOURCE_BUNDLE;
	protected ButtonMaker myButtonMaker = new ButtonMaker();
	/** The parameters are needed to enter the correct language text into the text area and run the command
	 * @param textAreaWriter The object which holds the text area for commands
	 * @param fireableRunButton An encapsulation of the runbutton which only allows firing of the runbutton
	 * @param currentLanguage The language currently being used by the workspace
	 */
	public ExecutableButton(TextAreaWriter textAreaWriter, FireableButton fireableRunButton, Language currentLanguage){
		myTextAreaWriter = textAreaWriter;
		myRunButton = fireableRunButton;
		myLanguage = currentLanguage; 
	}
	/** Runs the command written by getText()
	 */
	protected void executeCommand(){
		myCommandResources=ResourceBundle.getBundle(controller.Controller.DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		myTextAreaWriter.setText(getCommand()); 
		myRunButton.fire();
	}
	/** This method returns the string that will run in the Slogo environment when the executeCommand method is called 
	 * @return String command that will be entered into the textfield and run as code
	 */
	protected abstract String getCommand();
}