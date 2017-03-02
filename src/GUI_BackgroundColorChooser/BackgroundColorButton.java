package GUI_BackgroundColorChooser;

import ColorChoosers.BackgroundColorChooser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorButton extends BackgroundColorChooser {
	public BackgroundColorButton(Shape rect) {
		super(rect);
		colorPicker = new Button("Randomize Background Color");
		((ButtonBase) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
}
