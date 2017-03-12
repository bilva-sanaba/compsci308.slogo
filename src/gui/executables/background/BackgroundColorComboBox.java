package gui.executables.background;

import gui.GUI;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Shape;
/**
 *  Abstract extension of BackgroundColorChooser which implements it as a ComboBox
 * @author Bilva
 *
 */
public abstract class BackgroundColorComboBox extends BackgroundColorChooser{
	public BackgroundColorComboBox(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
	super(t,l,runButton,p);
	colorPicker = new ComboBox<String>();
	displays.add(colorPicker);
	((ComboBox<String>) colorPicker).setOnAction(e -> activate());            
		}

	}

