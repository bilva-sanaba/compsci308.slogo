
package gui.executables.background;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.shape.Shape;
/**
 * Abstract extension of BackgroundColorChooser which implements it as a button
 * @author Bilva
 *
 */
public abstract class BackgroundColorButton extends BackgroundColorChooser {
	public BackgroundColorButton(TextAreaWriter t,Language l,FireableButton runButton, Palette p) {
		super(t,l,runButton,p);
		colorPicker = myButtonMaker.createButton("Randomize Background Color", e -> activate());
		displays.add(colorPicker);
	}
}
