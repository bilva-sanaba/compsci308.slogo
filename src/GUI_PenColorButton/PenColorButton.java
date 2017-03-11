package GUI_PenColorButton;

import ColorChoosers.PenColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import xml.Default;

public abstract class PenColorButton extends PenColorChooser{
	public PenColorButton(Default d,TextAreaWriter t,Language l,Button runButton,Palette p) {
		super(d,t,l,runButton,p);
		colorPicker = new Button("Randomize Pen Color");
		displays.add(colorPicker);
		((ButtonBase) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
}
