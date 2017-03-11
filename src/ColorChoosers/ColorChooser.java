package ColorChoosers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GUI.TextAreaWriter;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class ColorChooser {
	public static final List<Color> Rainbow = Arrays.asList(Color.RED,Color.BLUE,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.INDIGO, Color.VIOLET);
	protected Node colorPicker;
	protected List<Node> displays;
	protected Button runButton;
	protected TextAreaWriter myTextAreaWriter;
	
	public ColorChooser(Button rb,TextAreaWriter t){
		displays = new ArrayList<Node>();
		runButton=rb;
		myTextAreaWriter=t;
	}
	public List<Node> getChooser(){
		return displays;
	}
	protected abstract Color generateColor();
	protected abstract void setColor();
}
