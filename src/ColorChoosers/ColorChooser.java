package ColorChoosers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import GUI.ExecutableButton;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class ColorChooser extends ExecutableButton {
	public static final List<Color> Rainbow = Arrays.asList(Color.RED,Color.BLUE,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.INDIGO, Color.VIOLET);
	public static final String PALETTE_RESOURCES = "resources/defaultPalette";
	protected ResourceBundle paletteResource=ResourceBundle.getBundle(PALETTE_RESOURCES);
	protected Node colorPicker;
	protected List<Node> displays;
	protected Palette myPalette;
	
	public ColorChooser(Button rb,TextAreaWriter t, Language l, Palette p){
		super(t,rb,l);
		displays = new ArrayList<Node>();
		myPalette=p;
	}
	public List<Node> getChooser(){
		return displays;
	}
	protected abstract Color generateColor();
}
