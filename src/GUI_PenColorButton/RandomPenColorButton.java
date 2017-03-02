package GUI_PenColorButton;

import java.util.Random;

import GUI.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class RandomPenColorButton extends PenColorButton{
	public RandomPenColorButton(TurtleViewManager tvm) {
		super(tvm);
		colorPicker = new Button("Randomize Pen Color");
		((ButtonBase) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}

	
	private double randomInt(){
		Random rand = new Random();
		return rand.nextFloat();
		
	}


	@Override
	protected Paint getPenColor() {
		return new Color(randomInt(),randomInt(),randomInt(),1.0);
	}
}
