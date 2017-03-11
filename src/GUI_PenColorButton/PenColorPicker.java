package GUI_PenColorButton;

import ColorChoosers.PenColorChooser;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;
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
	public PenColorPicker(Default myDefault,TextAreaWriter t,Language l,Button runButton,Palette p){
		super(myDefault, t, l,runButton,p);
		colorPicker = new ColorPicker(Color.valueOf("black"));
		displays.add(new Label("Pick Pen Color: "));
		displays.add(colorPicker);
		((ComboBoxBase<Color>) colorPicker).setOnAction(new EventHandler() {
			public void handle(Event t) {
				setColor();               
			}
    });
	}

	public Node getButton(){
		return colorPicker;
	}
	protected Color generateColor() {
		return ((ComboBoxBase<Color>) colorPicker).getValue();
	}
}
