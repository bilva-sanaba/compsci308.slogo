package gui.executables.background;
import controller.Controller;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;
/** Subclass of BackgroundColorChooser which implements it as a JavaFX ColorPicker
 * @author Bilva
 */
public class BackgroundColorPicker extends BackgroundChooser{
	private ComboBoxBase<Color> colorPicker;
	/**@param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public BackgroundColorPicker(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
		super(t,l,runButton,p);
		colorPicker = new ColorPicker(Color.web(Controller.PALETTE_RESOURCE_BUNDLE.getString("1")));
		displayedNodes.add(colorPicker);
		colorPicker.setOnAction(e -> executeCommand());
	}
	protected Color generateColor(){
		return colorPicker.getValue();
	}
}
