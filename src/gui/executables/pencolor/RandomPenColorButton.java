package gui.executables.pencolor;

import java.util.Random;

import gui.TextAreaWriter;
import gui.executables.FireableButton;
import gui.executables.boxes.Palette;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;

public class RandomPenColorButton extends PenColorButton{
	public RandomPenColorButton(Default d,TextAreaWriter t,Language l,FireableButton runButton,Palette p) {
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
