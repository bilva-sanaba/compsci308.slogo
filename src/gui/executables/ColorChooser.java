
package gui.executables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gui.executables.boxes.Palette;
import gui.language.Language;
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
	/**
	 * Extends an executable button specifically allows updates with reference to a palette
	 * @param rb
	 * @param t
	 * @param l
	 * @param p
	 */
	public ColorChooser(FireableButton rb,TextAreaWriter t, Language l, Palette p){
		super(t,rb,l);
		displays = new ArrayList<Node>();
		myPalette=p;
	}
	/**
	 * method needed for GUI to display Node
	 * @return
	 */
	public List<Node> getChooser(){
		return displays;
	}
	protected abstract Color generateColor();
}
