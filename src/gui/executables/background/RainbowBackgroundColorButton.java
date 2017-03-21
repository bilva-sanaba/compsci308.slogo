package gui.executables.background;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.paint.Color;
/**
 * Subclass of RainbowBackgroundColorButton which creates a random color from the rainbow when clicked
 * @author Bilva
 */
public class RainbowBackgroundColorButton extends BackgroundColorButton{
	public static final List<Color> Rainbow = Arrays.asList(Color.RED,Color.BLUE,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.INDIGO, Color.VIOLET);
	public RainbowBackgroundColorButton(TextAreaWriter t,Language l,FireableButton runButton, Palette p) {
		super(t,l,runButton,p);
	}
	@Override
	protected Color generateColor() {
		Random rand = new Random();
		return Rainbow.get(rand.nextInt(Rainbow.size()));
	}
}
