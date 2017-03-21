package gui.executables.background;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.ComboBox;
/**
 *  Abstract extension of BackgroundColorChooser which implements it as a ComboBox
 * @author Bilva
 *
 */
public abstract class BackgroundColorComboBox extends BackgroundColorChooser{
	public BackgroundColorComboBox(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
		super(t,l,runButton,p);
		colorPicker = new ComboBox<String>();
		displays.add(colorPicker);
		((ComboBox<String>) colorPicker).setOnAction(e -> executeCommand());    
		((ComboBox<String>) colorPicker).setPromptText(myButtonResources.getString("BackgroundComboBox"));
	}
}

