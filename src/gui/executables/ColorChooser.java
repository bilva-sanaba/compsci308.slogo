
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
/**
 * Abstract extension of the ExecutableButton, specifically for buttons whose text relies on knowing the current palette
 * of the workspace
 * @author Bilva
 *
 */
public abstract class ColorChooser extends ExecutableButton {
	public static final List<Color> Rainbow = Arrays.asList(Color.RED,Color.BLUE,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.INDIGO, Color.VIOLET);
	public static final String PALETTE_RESOURCES = "resources/defaultPalette";
	protected ResourceBundle paletteResource=ResourceBundle.getBundle(PALETTE_RESOURCES);
	protected Node colorPicker;
	protected List<Node> displays;
	protected Palette myPalette;
	/**
	 * Creates an executable button with a stored palette and list of displayed Nodes
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
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
