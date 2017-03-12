package gui.executables.background;

import java.util.ResourceBundle;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
/**
 *  Subclass of BackgroundColorChooser which implements it as a JavaFX ColorPicker
 * @author Bilva
 *
 */
public class BackgroundColorPicker extends BackgroundColorChooser{
	/**
	 * 
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public BackgroundColorPicker(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
		super(t,l,runButton,p);
		colorPicker = new ColorPicker(Color.web(paletteResource.getString("1")));
		displays.add(colorPicker);
		((ComboBoxBase<Color>) colorPicker).setOnAction(e -> activate());
	}
	protected Color generateColor(){
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}
