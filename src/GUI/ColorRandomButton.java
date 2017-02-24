package GUI;

import java.util.Random;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorRandomButton extends ColorButton{
	
	public ColorRandomButton(Pane canvas) {
		super(canvas);
		colorPicker = new Button("Randomize Background Color");
		((ButtonBase) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}

	
	protected String getStringColor() {
		 return "-fx-background-color: rgb("+Integer.toString(randomInt())+","+Integer.toString(randomInt())+","+Integer.toString(randomInt())+");";
	}
	private int randomInt(){
		Random rand = new Random();
		return rand.nextInt(256);
		
	}

}
