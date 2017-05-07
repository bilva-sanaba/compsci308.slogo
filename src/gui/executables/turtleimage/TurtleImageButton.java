package gui.executables.turtleimage;


import java.util.ResourceBundle;

import gui.ButtonMaker;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.beans.property.MapProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
/**
 * Button to be displayed on GUI which when clicked creates a 
 * ITurtleViewChanger to allow user to change Turtle Images
 * @author Bilva
 *
 */
public class TurtleImageButton implements IDisplayNode {
	private ButtonMaker buttonMaker;
	private ITurtleViewChanger titv;
	private Button turtleImageButton;
	private ResourceBundle buttonResources;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources/";
	public static final String BUTTONS="ButtonNames";
	public TurtleImageButton(MapProperty<Integer, TurtleViewManager> list, TextAreaWriter myTextAreaWriter, FireableButton runButton, Language myLanguage){
		titv = new TurtleImageToggleView(list,myTextAreaWriter,runButton,myLanguage);
		buttonMaker = new ButtonMaker();
		buttonResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+BUTTONS);
		turtleImageButton = buttonMaker.createButton(buttonResources.getString("ChangeImage"), e -> openTurtleView());
	}
	@Override
	public Node getNode(){
		return turtleImageButton;
	}
	
	private void openTurtleView() {
		titv.run();
	}
}
