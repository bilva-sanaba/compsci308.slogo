
package gui.executables.pencolor;

import java.util.ResourceBundle;

import gui.executables.ColorChooser;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import gui.movement.TurtleRegularMover;
import gui.movement.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;
/**
 * Abstract extension of Color chooser 
 * @author Bilva
 *
 */
public abstract class PenColorChooser extends ColorChooser {
	/**
	 * 
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public PenColorChooser(TextAreaWriter t,Language l,FireableButton runButton,Palette p){
		super(runButton,t,l, p);
	}
	/**
	 * creates text that is run in command. creates it as text needed to change pencolor
	 * with the color changed to determined by generateColor()
	 */
	protected String getText(){
		Color color=generateColor();
		String command=myResources.getString("SetPalette").split("\\|")[0];
		command+=(" "+Integer.toString(myPalette.getPalette().getItems().size()+1)+ " ");
		command+=(Double.toString(color.getRed()*255)+" ");
		command+=(Double.toString(color.getGreen()*255)+" ");
		command+=(Double.toString(color.getBlue()*255)+" ");
		command+=(myResources.getString("SetPenColor").split("\\|")[0]);
		command+=(" "+Integer.toString(myPalette.getPalette().getItems().size()+1));
		return command;
	}

}
