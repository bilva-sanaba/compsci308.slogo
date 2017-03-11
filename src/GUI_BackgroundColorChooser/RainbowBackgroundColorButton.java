package GUI_BackgroundColorChooser;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ColorChoosers.ColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RainbowBackgroundColorButton extends BackgroundColorButton{
	public RainbowBackgroundColorButton(TextAreaWriter t,Language l,Button runButton, Palette p) {
		super(t,l,runButton,p);
	}

	@Override
	protected Color generateColor() {
		Random rand = new Random();
		return ColorChooser.Rainbow.get(rand.nextInt(ColorChooser.Rainbow.size()));
	}
}
