// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class also uses the Command Design Pattern, with concrete classes needing to use the additionalCommands() and generateColor() methods
// This is an abstract subclass for the ExecutableButton. It functions by filling out the getText() method so that
//     a color is added to the palette, chosen by the abstract generateColor(), and then
//     additional commands are determined by the abstract method additionalCommands.
// The class shows good design as it extract codes from both the pencolorchooser and the backgroundcolorchooser classes
//     Additionally, if any other extension required setting the palette to some color, it could utilize this class easily.
// Finally the resource file for what colorsets will be used is here as many different implementations of color chooser
//	   may need access to a list of colors stored in a resource file. 
// 56 lines
package gui.executables;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.Node;
import javafx.scene.paint.Color;
/** Abstract extension of the ExecutableButton,for buttons who update the palette with a new color before running additional slogocommands
 * @author Bilva
 */
public abstract class ColorChooser extends ExecutableButton {
	protected static final String colorSetResources = "resources/ColorSets";
	protected static final ResourceBundle colorSets = ResourceBundle.getBundle(colorSetResources);
	protected List<Node> displayedNodes = new ArrayList<Node>();
	protected Palette myPalette;
	/** Creates an executable button with a stored palette and list of displayed Nodes
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public ColorChooser(FireableButton rb,TextAreaWriter t, Language l, Palette p){
		super(t,rb,l);
		myPalette=p;
	}
	/** This returns the nodes that will be added to the GUI and is needed for display
	 * @return List of Nodes to be added to the GUI
	 */
	public List<Node> getChooser(){
		return displayedNodes;
	}
	/** Creates the string command that is run when executecommand is called. In this implementation the method written
	 * adds the appropriate color to the palette and then additional commands are added
	 */
	protected String getCommand(){
		Color color=generateColor();
		StringBuilder command = new StringBuilder();
		command.append(addToPaletteCommand(color));
		command.append(additionalCommands(color));
		return command.toString();
	}
	/** This method returns a color which is used when creating a command to be run (getCommand() method)
	 * @return Color which can be used in some way depending on the implementation
	 */
	protected abstract Color generateColor();
	/** Method used so that implementations may add more commands after the palette color is added in getCommand()
	 * @param Color to be used when creating a command
	 * @return String command to be added to the slogo command after palette setting
	 */
	protected abstract String additionalCommands(Color color);
	/** Method used to find a colors' index in a palette
	 * @param Color that will be added to the palette
	 * @return String of index of color in Palette
	 */
	protected String getColorIndex(Color color){
		String colorIndex = " ";
		if (myPalette.inPalette(color)){
			colorIndex += Integer.toString(myPalette.getColorIndex(color));
		}else{
			colorIndex += Integer.toString(myPalette.getNextAvailableIndex());
		}
		return colorIndex+=" ";
	}
	private String addToPaletteCommand(Color color){
		StringBuilder paletteCommand = new StringBuilder();
		if (!myPalette.inPalette(color)){
			paletteCommand.append(myCommandResources.getString("SetPalette").split("\\|")[0]);
			paletteCommand.append(getColorIndex(color));
			paletteCommand.append(colorToString(color));
		}
		return paletteCommand.toString(); 
	}
	private String colorToString(Color color){
		StringBuilder colorString = new StringBuilder();
		colorString.append((Double.toString(color.getRed()*255)+" "));
		colorString.append((Double.toString(color.getGreen()*255)+" "));
		colorString.append((Double.toString(color.getBlue()*255)+" "));
		return colorString.toString();
	}
}