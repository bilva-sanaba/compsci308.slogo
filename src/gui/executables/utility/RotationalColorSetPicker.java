// This entire file is part of my masterpiece.
// Bilva Sanaba
// Concrete ColorSetPicker which shows good design as it is what leads to a reduction of code and improved extendability
// 		in the background button class. Classes can use this class or a different class extending ColorSetPicker to pick colors. 
package gui.executables.utility;
import java.util.List;
import javafx.scene.paint.Color;
/** This class takes a List of Colors and picks a color, rotating through in order each time pick color is called
 * @author Bilva
 */
public class RotationalColorSetPicker extends ColorSetPicker{ 
	private int currentPos=-1;
	public Color pickColor(List<String> colorSet) {
		currentPos= (currentPos+1)%colorSet.size();
		return Color.web(colorSet.get(currentPos));
	}
}