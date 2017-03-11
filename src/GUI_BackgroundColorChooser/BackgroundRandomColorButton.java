package GUI_BackgroundColorChooser;

import java.util.Random;

import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BackgroundRandomColorButton extends BackgroundColorButton{
	
	public BackgroundRandomColorButton(TextAreaWriter t,Language l,Button runButton, Palette p) {
		super(t,l,runButton,p);
	}
	private String getStringColor() {
		 return "rgb(" + Integer.toString(randomInt())+","+Integer.toString(randomInt())+","+Integer.toString(randomInt())+");";
	}
	private int randomInt(){
		Random rand = new Random();
		return rand.nextInt(256);
	}
	protected Color generateColor() {
		return Color.web(getStringColor());
	}

}
