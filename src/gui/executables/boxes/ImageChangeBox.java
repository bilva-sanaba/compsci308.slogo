package gui.executables.boxes;

import java.io.File;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import xml.Default;

public class ImageChangeBox extends ExecutableButton{
	private ComboBox<Integer>imageChoice;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
	private int num;
	private ObservableMap<Integer,TurtleViewManager>tvmMap;
	private int selectedInt;
	private TurtleComboBox tcb;
	public ImageChangeBox(TextAreaWriter t,Language l,FireableButton rb,ObservableMap<Integer,TurtleViewManager> d,TurtleComboBox tcb){
		super(t,rb,l);
		num=0;
		this.tcb=tcb;
		tvmMap=d;
		initializeComboBox();
		
	}
	
	public ComboBox<Integer> getImageChooser(){
		return imageChoice;
	}
	private void refreshBox(){
		setFactories();
	}
	private void setFactories(){
		imageChoice.setCellFactory(c-> new ImageListCell(tvmMap));
		imageChoice.setButtonCell(new ImageListCell(tvmMap));
	}
	private void initializeComboBox(){
		
		imageChoice = new ComboBox<Integer>();
		imageChoice.setOnMouseClicked(e->refreshBox()
				
			);
	
		
	
		tvmMap.addListener((MapChangeListener.Change<? extends Integer, ? extends TurtleViewManager> c) -> 
		{
		
		imageChoice.getItems().setAll(tvmMap.keySet());
		});
		imageChoice.getItems().addAll(tvmMap.keySet());
		imageChoice.setPromptText("Choose Turtle to update");
		setFactories();
		
		imageChoice.valueProperty().addListener((x, y, newValue) -> {
		if(newValue!=null){
		getNewImage();
		imageChoice.getSelectionModel().clearSelection();
		}
		});
	}
	private void getNewImage(){
		Stage tempStage=new Stage();
		FileChooser fileChooser=new  FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("images","*.png","*.jpg","*.jpeg","*.gif"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		File file=fileChooser.showOpenDialog(tempStage);
		if(!tcb.getTurtleChooser().getItems().contains(file.getName()))
		tcb.getTurtleChooser().getItems().add(file.getName());
		selectedInt =tcb.getTurtleChooser().getItems().indexOf(file.getName());
		activate();
		activate();
	}
	protected String getText(){
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
		String command=(myResources.getString("Tell").split("\\|")[0]);
		command+=" [ ";
		command+=Integer.toString(imageChoice.getSelectionModel().getSelectedIndex());
		command+=" ] ";
		command+=(myResources.getString("SetShape").split("\\|")[0]);
		command+=" ";
		command+=selectedInt;
		return command;
	}
}
