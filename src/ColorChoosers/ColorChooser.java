package ColorChoosers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class ColorChooser {
	public static final List<Color> Rainbow = Arrays.asList(Color.RED,Color.BLUE,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.INDIGO, Color.VIOLET);
	protected Node colorPicker;
	protected List<Node> displays;
	
	public ColorChooser(){
		displays = new ArrayList<Node>();
	}
	public List<Node> getChooser(){
		return displays;
	}
	protected abstract Paint generateColor();
	protected abstract void setColor();
}
