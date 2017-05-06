/**
 * button which can be used to retrieve a pen size in the GUI
 */
package gui.executables.penproperties;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * Subclass of PenSizeChooser which implements it as a textinput field
 * @author Bilva
 *
 */
public class PenSizeTextInput extends PenSizeChooser {
	private static final double DEFAULT_PEN_SIZE = 4;
	protected double penSize = DEFAULT_PEN_SIZE;
	public PenSizeTextInput(TextAreaWriter t, FireableButton rb,Language l) {
		super(t,rb,l);
	}

	@Override
	protected void createPenSizeChooser() {
		penSizeButton = new TextField();
		((TextField) penSizeButton).setPromptText("Enter Pen Size");
		((TextField) penSizeButton).setOnAction(e -> {
			try{
				activate();
				((TextField) penSizeButton).clear();
			}catch(IllegalArgumentException y){

			}
			catch(NullPointerException i){}
		});
	}
	/**
	 * This code should be moved up to superclass with a little modification
	 * Not done currently as there was only time for one implementation
	 */
	protected String getText(){
		String command=myResources.getString("SetPenSize").split("\\|")[0];
		command+=" "+((TextField) penSizeButton).getText();
		return command;
	}
}



