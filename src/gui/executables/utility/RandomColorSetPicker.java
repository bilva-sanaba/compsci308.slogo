package gui.executables.utility;
import java.util.List;
import javafx.scene.paint.Color;
/**
 * 
 * @author Bilva
 *
 */
public class RandomColorSetPicker extends ColorSetPicker{
	public Color pickColor(List<String> colorSet) {
		return Color.web(colorSet.get(rand.nextInt(colorSet.size())));
	}
}
