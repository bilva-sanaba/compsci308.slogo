package gui.executables.boxes;


import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import xml.Default;
import javafx.scene.image.Image;
/**
 * ComboBox allowing user to select desired shape of Turtle
 * @author Alex
 *
 */
public class TurtleComboBox extends ExecutableButton{
	private ComboBox<String> turtleChoice;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
	public TurtleComboBox(TextAreaWriter t,Language l,FireableButton rb,Default d){
		super(t,rb,l);
		turtleChoice = new ComboBox<String>();
		turtleChoice.getItems().addAll(d.getImageString());
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.setCellFactory(c-> new TurtleListCell());
		turtleChoice.setButtonCell(new TurtleListCell());
	turtleChoice.valueProperty().addListener((x, y, newValue) -> {
		activate();
		});
		
	}
	/**
	 * 
	 * @return ComboBox containing shape choices
	 */
	public ComboBox<String> getTurtleChooser(){
		return turtleChoice;
	}

protected String getText(){
	myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
	
	String command=(myResources.getString("SetShape").split("\\|")[0]);
	command+=" ";
	command+=Integer.toString(turtleChoice.getSelectionModel().getSelectedIndex());
	return command;
}
}