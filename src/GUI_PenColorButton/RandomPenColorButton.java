package GUI_PenColorButton;

import java.util.Random;

import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;

public class RandomPenColorButton extends PenColorButton{
	public RandomPenColorButton(Default d,TextAreaWriter t,Language l,Button runButton,Palette p) {
		super(d,t,l,runButton,p);
	}
	private double randomInt(){
		Random rand = new Random();
		return rand.nextFloat();	
	}
	@Override
	protected Color generateColor() {
		return new Color(randomInt(),randomInt(),randomInt(),1.0);
	}
}
