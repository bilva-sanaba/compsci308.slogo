// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class implements the BackgroundChooser as a comboBox and gives it properties so that it executes a command
//     whenever the comboBox is interacted with, and also labels the node combobox
// It highlights good design as all implementations of the backgroundchooser which use a combobox would need this code
//     so by pushing it to a super class, duplicate code is reduced and extendability is more possible with less code
// It also reduces duplicate code by creating a combobox using the buttonmaker class which uses the factory design pattern. 
// 12 lines
package gui.executables.background;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.ComboBox;
/** Abstract extension of BackgroundColorChooser which implements it as a ComboBox
 * @author Bilva
 */
public abstract class BackgroundComboBox extends BackgroundChooser{
	protected ComboBox<String> colorPicker;
	public BackgroundComboBox(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
		super(t,l,runButton,p);
		colorPicker = myButtonMaker.createComboBox(myButtonResources.getString("BackgroundComboBox"),e -> executeCommand());
		displayedNodes.add(colorPicker);
	}
}