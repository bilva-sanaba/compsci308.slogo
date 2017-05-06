package gui.executables.pencolor;

import java.util.Random;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;
/**
 * Subclass of PenColorButton which implements it as a rng color
 * @author Bilva
 *
 */
public class RandomPenColorButton extends PenColorButton{
	public RandomPenColorButton(TextAreaWriter t,Language l,FireableButton runButton,Palette p) {
		super(t,l,runButton,p);
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
