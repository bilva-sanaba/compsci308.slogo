package GUI_BackgroundColorChooser;

import ColorChoosers.BackgroundColorChooser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class BackgroundColorPicker extends BackgroundColorChooser{
	
	
	public BackgroundColorPicker(Shape background){
		super(background);
		colorPicker = new ColorPicker(Color.WHITE);
		displays.add(colorPicker);
		((ComboBoxBase<Color>) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
	protected Paint generateColor(){
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}
