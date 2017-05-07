package gui.executables.boxes;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleRegularMover;
import gui.movement.TurtleViewManager;
import gui.panels.ImageListCell;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import xml.Default;

public class ImageChangeBox extends ExecutableButton{
	private ComboBox<Integer>imageChoice;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
	private int num;
	public ImageChangeBox(TextAreaWriter t,Language l,FireableButton rb,ObservableMap<Integer,TurtleViewManager> d){
		super(t,rb,l);
		num=0;
		imageChoice = new ComboBox<Integer>();
		
		
		
	
		d.addListener((MapChangeListener.Change<? extends Integer, ? extends TurtleViewManager> c) -> 
		{
		System.out.println("change made");
		imageChoice.getItems().setAll(d.keySet());
		});
		//map.remove(0);
		
		imageChoice.getItems().addAll(d.keySet());
		imageChoice.setPromptText("Choose Turtle to update");
		imageChoice.setCellFactory(c-> new ImageListCell(d));
		imageChoice.setButtonCell(new ImageListCell(d));
		imageChoice.valueProperty().addListener((x, y, newValue) -> {
		System.out.println(d.keySet());
		activate();
		
		//imageChoice.getSelectionModel().clearSelection();
		});
		
	}
	private int getNum(){
		num++;
		num=num%3;
		return num;
		
	}
	public ComboBox<Integer> getImageChooser(){
		return imageChoice;
	}
	protected String getText(){
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		String command=(myResources.getString("Tell").split("\\|")[0]);
		command+=" [ ";
		command+=Integer.toString(imageChoice.getSelectionModel().getSelectedIndex());
		command+=" ] ";
		command+=(myResources.getString("SetShape").split("\\|")[0]);
		command+=" ";
		command+=getNum();
		return command;
	}
}
