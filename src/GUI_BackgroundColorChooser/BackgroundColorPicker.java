package GUI_BackgroundColorChooser;

import java.util.ResourceBundle;

import ColorChoosers.BackgroundColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class BackgroundColorPicker extends BackgroundColorChooser{
	public BackgroundColorPicker(TextAreaWriter t,Language l,Button runButton, Palette p){
		super(t,l,runButton,p);
		colorPicker = new ColorPicker(Color.web(paletteResource.getString("1")));
		displays.add(colorPicker);
		((ComboBoxBase<Color>) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
	protected Color generateColor(){
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}
