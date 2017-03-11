package gui.executables.background;

import java.util.ResourceBundle;

import gui.ButtonMaker;
import gui.TextAreaWriter;
import gui.executables.ColorChooser;
import gui.executables.FireableButton;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorChooser extends ColorChooser{
	public BackgroundColorChooser(TextAreaWriter t,Language l,FireableButton runButton,  Palette p){
		super(runButton,t,l, p);
		displays.add(myButtonMaker.createLabel("Pick Background Color: "));
	}

	protected void setColor(){
		myTextAreaWriter.setText(getText()); 
		runButton.fire();
	}
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
