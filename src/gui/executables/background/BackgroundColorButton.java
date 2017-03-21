package gui.executables.background;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
/**
 * Abstract extension of BackgroundColorChooser which implements it as a button
 * @author Bilva
 *
 */
public abstract class BackgroundColorButton extends BackgroundColorChooser {
	public BackgroundColorButton(TextAreaWriter t,Language l,FireableButton runButton, Palette p) {
		super(t,l,runButton,p);
		colorPicker = myButtonMaker.createButton(myButtonResources.getString("BackgroundButton"), e -> executeCommand());
		displays.add(colorPicker);
	}
}
