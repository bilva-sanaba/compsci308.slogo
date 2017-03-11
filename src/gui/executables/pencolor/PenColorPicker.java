package gui.executables.pencolor;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import gui.movement.TurtleRegularMover;
import gui.movement.TurtleViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;

public class PenColorPicker extends PenColorChooser{
	public PenColorPicker(Default myDefault,TextAreaWriter t,Language l,FireableButton runButton,Palette p){
		super(myDefault, t, l,runButton,p);
		colorPicker = new ColorPicker(Color.web(paletteResource.getString("1")));
		displays.add(myButtonMaker.createLabel("Pick Pen Color: "));
		displays.add(colorPicker);
		((ComboBoxBase<Color>) colorPicker).setOnAction(e -> activate());
	}
	public Node getButton(){
		return colorPicker;
	}
	protected Color generateColor() {
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}