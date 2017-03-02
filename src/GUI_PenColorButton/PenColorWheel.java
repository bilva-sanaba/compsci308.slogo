package GUI_PenColorButton;

import GUI.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PenColorWheel extends PenColorButton{
	public PenColorWheel(TurtleViewManager tvm){
		super(tvm);
		colorPicker = new ColorPicker(Color.BLACK);
		((ComboBoxBase<Color>) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}

	public Node getButton(){
		return colorPicker;
	}

	@Override
	protected Paint getPenColor() {
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}
