package ColorChoosers;

import javafx.scene.Node;
import javafx.scene.paint.Paint;

public abstract class ColorChooser {
	protected Node colorPicker;
	public Node getChooser(){
		return colorPicker;
	}
	protected abstract Paint generateColor();
	protected abstract void setColor();
}
