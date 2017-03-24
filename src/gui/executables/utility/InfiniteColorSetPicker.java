
package gui.executables.utility;
import java.util.List;
import javafx.scene.paint.Color;
/**
 * 
 * @author Bilva
 *
 */
public class InfiniteColorSetPicker extends ColorSetPicker {
	public Color pickColor(List<String> colorSet) {
		return Color.web(getStringColor());
	}
	private String getStringColor() {
		 return "rgb(" + Integer.toString(randomInt())+","+Integer.toString(randomInt())+","+Integer.toString(randomInt())+");";
	}
	private int randomInt(){
		return rand.nextInt(256);
	}
}