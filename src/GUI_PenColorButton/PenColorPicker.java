package GUI_PenColorButton;

import ColorChoosers.PenColorChooser;
import GUI.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PenColorPicker extends PenColorChooser{
	public PenColorPicker(TurtleViewManager tvm){
		super(tvm);
		colorPicker = new ColorPicker(Color.BLACK);
		displays.add(colorPicker);
		((ComboBoxBase<Color>) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}

	public Node getButton(){
		return colorPicker;
	}
	protected Paint generateColor() {
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}
