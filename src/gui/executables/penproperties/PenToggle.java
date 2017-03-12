
package gui.executables.penproperties;

import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * Subclass of ExecutableButton which when run changes penup to pendown and viceversa
 * Abstract version could be made to facilitate different types of penup pen down button variants
 * (such as a seperate button for pen up and pen down)
 * @author Bilva
 *
 */
public class PenToggle extends ExecutableButton{

	private Button penToggleButton;
	private boolean toggle=true;
	public PenToggle(TextAreaWriter t,FireableButton r,Language l){
		super(t,r,l);
		createPenToggleButton();
	}
	/**
	 * needed by GUI to display Node
	 * @return
	 */
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
