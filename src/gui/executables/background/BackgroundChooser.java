// This entire file is part of my masterpiece.
// Bilva Sanaba
//This class is a subclass of ColorChooser which specifically sets the Background Color
//     In particular it labels the nodes appropriately and sets the additionalCommands() so that it 
//     writes the command which changes the background color to the correct number/color.
// This highlights good design since, if another concrete background button was to be created the additional commands code
//      would have to be copied and the nodes would have to be labeled again. Instead, it is placed in this super class. 
//      It removes duplicate code and allows for easy extendability with new BackgroundChoosers as wanted
// 19 lines
package gui.executables.background;
import gui.executables.ColorChooser;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.paint.Color;
/** Abstract extension of color chooser for setting a background color
 * @author Bilva
 */
public abstract class BackgroundChooser extends ColorChooser{
	/** Adds a label node to the list of Nodes created
	 * @param runButton Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public BackgroundChooser(TextAreaWriter t,Language l,FireableButton runButton,  Palette p){
		super(runButton,t,l, p);
		displayedNodes.add(myButtonMaker.createLabel(myButtonResources.getString("BackgroundLabel")));
	}
	/**This method is called by the ColorChooser when getCommand() is called. Returns additional commands needed to set background color 
	 */
	protected String additionalCommands(Color color){
		StringBuilder command= new StringBuilder();
		command.append((myCommandResources.getString("SetBackground").split("\\|")[0]));
		command.append(getColorIndex(color));
		return command.toString();
	}
}