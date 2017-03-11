package gui.executables.pencolor;

import gui.TextAreaWriter;
import gui.executables.FireableButton;
import gui.executables.boxes.Palette;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import xml.Default;

public abstract class PenColorButton extends PenColorChooser{
	public PenColorButton(Default d,TextAreaWriter t,Language l,FireableButton runButton,Palette p) {
		super(d,t,l,runButton,p);
		colorPicker = new Button("Randomize Pen Color");
		displays.add(colorPicker);
		((ButtonBase) colorPicker).setOnAction(e -> activate());
}
}
