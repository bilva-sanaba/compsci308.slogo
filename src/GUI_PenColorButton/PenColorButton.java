package GUI_PenColorButton;

import ColorChoosers.PenColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import xml.Default;

public abstract class PenColorButton extends PenColorChooser{
	public PenColorButton(TurtleViewManager tvm,Default d,TextAreaWriter t,Language l) {
		super(tvm,d,t,l);
		colorPicker = new Button("Randomize Pen Color");
		displays.add(colorPicker);
		((ButtonBase) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
}
