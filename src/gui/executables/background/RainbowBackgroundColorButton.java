package gui.executables.background;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gui.executables.ColorChooser;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Subclass of RainbowBackgroundColorButton which creates a random color from the rainbow when clicked
 * @author Bilva
 *
 */
public class RainbowBackgroundColorButton extends BackgroundColorButton{
	public RainbowBackgroundColorButton(TextAreaWriter t,Language l,FireableButton runButton, Palette p) {
		super(t,l,runButton,p);
	}

	@Override
	protected Color generateColor() {
		Random rand = new Random();
		return ColorChooser.Rainbow.get(rand.nextInt(ColorChooser.Rainbow.size()));
	}
}
