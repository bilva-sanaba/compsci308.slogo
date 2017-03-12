package gui.executables.background;

import java.util.Random;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * Subclass of BackgroundColorButton which creates a random color when clicked
 * @author Bilva
 *
 */
public class BackgroundRandomColorButton extends BackgroundColorButton{
	
	public BackgroundRandomColorButton(TextAreaWriter t,Language l,FireableButton runButton, Palette p) {
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
