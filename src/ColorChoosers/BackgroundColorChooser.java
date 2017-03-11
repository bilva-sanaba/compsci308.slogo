package ColorChoosers;

import java.util.ResourceBundle;

import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.ButtonMaker;
import GUI_Objects.Palette;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorChooser extends ColorChooser{
	protected ButtonMaker buttonMaker = new ButtonMaker();
	private Language myLanguage;
	private ResourceBundle myResources;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
	public BackgroundColorChooser(TextAreaWriter t,Language l,Button runButton,  Palette p){
		super(runButton,t,p);
		displays.add(buttonMaker.createLabel("Pick Background Color: "));
		myLanguage=l;
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
	}
	
	protected void setColor(){
		 myTextAreaWriter.setText(getText()); 
		 runButton.fire();
	 }
	protected String getText(){
	//move to factory
	myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
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
