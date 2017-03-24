// This entire file is part of my masterpiece.
// Bilva Sanaba
// In order to test and see this background button, change line 82 on inputPanel to an instance of this class.
//This is a concrete subclass of BackgroundButton which implements it as a random color generator picking
//      from a list of colors
// Good design is shown as the list of colors to be picked from is created from a resource file. 
//      Additionally, a single line (line 19) can be changed in order to pick a different color list if it is 
// 		in the resource file
//      	Example: change: mySetName = "Favorite" (in line 19)
// It also uses the strategy design pattern. Since this class works by using a color list and picking a new color on click
//      from that set of colors, different ways to pick the color can be determined by a strategy pattern. In particular,
//      currently a RandomColorSetPicker is used so a Random color is picked from the color list; however, RotationalColorSetPicker
//      could be used instead to pick colors in order, or InfiniteColorSetPicker could be used to constantly generate
//      new completely random colors. It demonstrates good design as it allows someone to change the button function by
//      creating just a new subclass of ColorSetPicker rather than modifying existing code. 
// Essentially, the class highlights good design because the strategy design patterns allows for easy reuse of this code,
//      and other functionality for a BackgroundButton can be created by creating a new subclass of BackgroundButton. 
// 21 lines
package gui.executables.background;
import java.util.Arrays;
import java.util.List;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.executables.utility.ColorSetPicker;
import gui.executables.utility.RandomColorSetPicker;
import gui.executables.utility.RotationalColorSetPicker;
import gui.language.Language;
import javafx.scene.paint.Color;
/**Concrete subclass of BackgroundButton which generates a random color from the appropriate ColorSet as defined by mySetName
 * @author Bilva
 */
public class StandardBackgroundButton extends BackgroundButton{
	private static String mySetName = "Rainbow";
	private ColorSetPicker myPicker= new RandomColorSetPicker();
	public static final List<String> ColorSet = Arrays.asList(colorSets.getString(mySetName).split(colorSets.getString("splitter")));
	public StandardBackgroundButton(TextAreaWriter t,Language l,FireableButton runButton, Palette p) {
		super(t,l,runButton,p);
	}
	protected Color generateColor() {
		return myPicker.pickColor(ColorSet);
	}
}