package ColorChoosers;

import java.util.ResourceBundle;

import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xml.Default;

public abstract class PenColorChooser extends ColorChooser {
		protected TextAreaWriter myTextArea;
		private ResourceBundle myResources;
		private Language myLanguage;
		public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
		public PenColorChooser(Default d,TextAreaWriter t,Language l,Button runButton,Palette p){
			super(runButton,t,p);
			myTextArea=t;
			myLanguage=l;
			myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		}
		protected void setColor(){
			myTextArea.setText(getText()); 
			runButton.fire();
		 }
	protected String getText(){
//Hide this in a factory later
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
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
