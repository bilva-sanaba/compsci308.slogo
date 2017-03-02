package GUI_PenColorButton;

import java.util.Random;

import GUI.TurtleViewManager;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class RandomPenColorButton extends PenColorButton{
	public RandomPenColorButton(TurtleViewManager tvm) {
		super(tvm);
	}
	private double randomInt(){
		Random rand = new Random();
		return rand.nextFloat();	
	}
	@Override
	protected Paint generateColor() {
		return new Color(randomInt(),randomInt(),randomInt(),1.0);
	}
}
