package GUI_BackgroundColorChooser;

import ColorChoosers.BackgroundColorChooser;
import GUI.GUI;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorComboBox extends BackgroundColorChooser{
	
	BackgroundColorComboBox(TextAreaWriter t,Language l,Button runButton, Palette p){
	super(t,l,runButton,p);
	colorPicker = new ComboBox<String>();
	displays.add(colorPicker);
	((ComboBox<String>) colorPicker).setOnAction(new EventHandler() {
		public void handle(Event t) {
			activate();               
		}
});
	}
}
