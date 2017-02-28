package GUI_BackgroundColorChooser;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class ColorButton {
	protected Node colorPicker;
	protected Pane myPane;
	
	public ColorButton(Pane canvas){
		myPane=canvas;
	}
	protected abstract String getStringColor();
	
	protected void setColor(){
		 myPane.setStyle(getStringColor());
	 }
	public Node getButton(){
		return colorPicker;
	}
}
