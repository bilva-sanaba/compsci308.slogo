package GUI_BackgroundColorChooser;

import ColorChoosers.BackgroundColorChooser;
import GUI.GUI;
import GUI.Language;
import GUI.TextAreaWriter;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorComboBox extends BackgroundColorChooser{
	
	BackgroundColorComboBox(Shape rect,TextAreaWriter t,Language l){
	super(rect,t,l);
	colorPicker = new ComboBox<String>();
	displays.add(colorPicker);
	((ComboBox<String>) colorPicker).setOnAction(new EventHandler() {
		public void handle(Event t) {
			setColor();               
		}
});
	}
}
