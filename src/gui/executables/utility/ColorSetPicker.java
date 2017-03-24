// This entire file is part of my masterpiece.
// Bilva Sanaba
// Class which allows the use of the strategy design pattern, allowing for extensions to the background button to be
// 		easily implemented.
package gui.executables.utility;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
/**
 * Class used (via strategy design pattern) in order to pick a color from a list of colors
 * @author Bilva
 */
public abstract class ColorSetPicker {
	protected Random rand = new Random();
	/**Method which subclasses must implement in order to pick a color
	 * @param colorSet List of Colors to be picked from
	 * @return Color returned
	 */
	public abstract Color pickColor(List<String> colorSet);
}