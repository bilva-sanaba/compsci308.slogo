
package gui.executables.pencolor;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import xml.Default;
/**
 * Abstract extension of pen color chooser which implements it as a button
 * @author Bilva
 *
 */
public abstract class PenColorButton extends PenColorChooser{
	/**
	 * Creates a button for randomizing pen color
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public PenColorButton(TextAreaWriter t,Language l,FireableButton runButton,Palette p) {
		super(t,l,runButton,p);
		colorPicker = myButtonMaker.createButton("Randomize Pen Color", e->activate());
		displays.add(colorPicker);
	}
}
