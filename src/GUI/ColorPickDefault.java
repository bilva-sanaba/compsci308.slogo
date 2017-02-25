package GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorPickDefault extends ColorButton{
	
	
	public ColorPickDefault(Pane canvas){
		super(canvas);
		colorPicker = new ColorPicker(Color.WHITE);
		((ComboBoxBase<Color>) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}
	protected String getStringColor(){
		return "-fx-background-color: #"+((ComboBoxBase<Color>) colorPicker).getValue().toString().substring(2)+";";
	}
	public Node getButton(){
		return colorPicker;
	}
}
