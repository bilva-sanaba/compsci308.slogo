package GUI_BackgroundColorChooser;

import ColorChoosers.BackgroundColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class BackgroundColorPicker extends BackgroundColorChooser{
	
	
	public BackgroundColorPicker(Shape background,TextAreaWriter t,Language l){
		super(background,t,l);
		colorPicker = new ColorPicker(Color.WHITE);
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
