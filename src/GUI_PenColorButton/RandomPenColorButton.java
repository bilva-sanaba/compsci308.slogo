package GUI_PenColorButton;

import java.util.Random;

import GUI.Language;
import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;

public class RandomPenColorButton extends PenColorButton{
	public RandomPenColorButton(TurtleViewManager tvm,Default d,TextAreaWriter t,Language l) {
		super(tvm,d,t,l);
	}
	private double randomInt(){
		Random rand = new Random();
		return rand.nextFloat();	
	}
	@Override
	protected Color generateColor() {
		return new Color(randomInt(),randomInt(),randomInt(),1.0);
	}
}
