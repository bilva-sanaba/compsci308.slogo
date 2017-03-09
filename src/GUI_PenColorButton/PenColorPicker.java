package GUI_PenColorButton;

import ColorChoosers.PenColorChooser;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;

public class PenColorPicker extends PenColorChooser{
	public PenColorPicker(TurtleViewManager tvm,Default myDefault){
		super(tvm,myDefault);
		colorPicker = new ColorPicker(Color.valueOf(myDefault.getPenColor()));
		displays.add(new Label("Pick Pen Color: "));
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
