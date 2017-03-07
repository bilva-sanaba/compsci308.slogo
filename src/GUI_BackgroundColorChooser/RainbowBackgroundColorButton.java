package GUI_BackgroundColorChooser;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ColorChoosers.ColorChooser;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RainbowBackgroundColorButton extends BackgroundColorButton{
	public RainbowBackgroundColorButton(Shape rect) {
		super(rect);
	}

	@Override
	protected Paint generateColor() {
		Random rand = new Random();
		return ColorChooser.Rainbow.get(rand.nextInt(ColorChooser.Rainbow.size()));
	}
}
