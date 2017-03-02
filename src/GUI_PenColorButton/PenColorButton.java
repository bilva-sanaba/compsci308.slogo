package GUI_PenColorButton;

import ColorChoosers.PenColorChooser;
import GUI.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;

public abstract class PenColorButton extends PenColorChooser{
	public PenColorButton(TurtleViewManager tvm) {
		super(tvm);
		colorPicker = new Button("Randomize Pen Color");
		((ButtonBase) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
}
