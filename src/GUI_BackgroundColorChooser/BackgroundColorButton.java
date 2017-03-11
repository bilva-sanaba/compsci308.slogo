package GUI_BackgroundColorChooser;

import ColorChoosers.BackgroundColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorButton extends BackgroundColorChooser {
	public BackgroundColorButton(TextAreaWriter t,Language l,Button runButton, Palette p) {
		super(t,l,runButton,p);
		colorPicker = myButtonMaker.createButton("Randomize Background Color", e -> activate());
		displays.add(colorPicker);
	}
}
