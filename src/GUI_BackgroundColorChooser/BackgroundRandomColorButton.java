package GUI_BackgroundColorChooser;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BackgroundRandomColorButton extends BackgroundColorButton{
	
	public BackgroundRandomColorButton(Shape rect) {
		super(rect);
	}
	private String getStringColor() {
		 return "rgb(" + Integer.toString(randomInt())+","+Integer.toString(randomInt())+","+Integer.toString(randomInt())+");";
	}
	private int randomInt(){
		Random rand = new Random();
		return rand.nextInt(256);
	}
	protected Paint generateColor() {
		return Color.web(getStringColor());
	}

}
