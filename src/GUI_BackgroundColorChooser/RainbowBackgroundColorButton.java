package GUI_BackgroundColorChooser;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RainbowBackgroundColorButton extends BackgroundColorButton{
	public static final List<Color> Rainbow = Arrays.asList(Color.RED,Color.BLUE,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.INDIGO, Color.VIOLET);
	public RainbowBackgroundColorButton(Shape rect) {
		super(rect);
		
	}

	@Override
	protected Paint generateColor() {
		Random rand = new Random();
		return Rainbow.get(rand.nextInt(Rainbow.size()));
	}

}
