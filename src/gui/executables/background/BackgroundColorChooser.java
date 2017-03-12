package gui.executables.background;

import java.util.ResourceBundle;

import gui.ButtonMaker;
import gui.executables.ColorChooser;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
/**
 * Abstract extension of color chooser for setting a background color
 * @author Bilva
 *
 */
public abstract class BackgroundColorChooser extends ColorChooser{
	/**
	 *  Adds a label node to the list of Nodes created
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public BackgroundColorChooser(TextAreaWriter t,Language l,FireableButton runButton,  Palette p){
		super(runButton,t,l, p);
		displays.add(myButtonMaker.createLabel("Pick Background Color: "));
	}
	/**
	 * Text entered is the text needed for setting a background to a new color
	 * the color picked is the color created by generateColor()
	 */
	protected String getText(){
		Color color=generateColor();
		String command=myResources.getString("SetPalette").split("\\|")[0];
		command+=(" "+Integer.toString(myPalette.getPalette().getItems().size()+1)+ " ");
		command+=(Double.toString(color.getRed()*255)+" ");
		command+=(Double.toString(color.getGreen()*255)+" ");
		command+=(Double.toString(color.getBlue()*255)+" ");
		command+=(myResources.getString("SetBackground").split("\\|")[0]);
		command+=(" "+Integer.toString(myPalette.getPalette().getItems().size()+1)+" ");
		return command;

	}
}
