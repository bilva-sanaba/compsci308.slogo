package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import GUI_Objects.ButtonMaker;
import GUI_TurtleMovers.TurtleAnimator;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;
import configuration.Trajectory;
import error.SlogoAlert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Model;
import xml.Default;
import xml.XML;
import xml.XMLWriter;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private TextAreaWriter textAreaWriter;
	private Canvas canvas;
	private InputPanel realInput;
	private GraphicsContext gc;
	private LeftPanel lp;
	private VBox rightScreen = new VBox();
	private RightPanel rp;
	private Shape background;
	private Button runButton;
	private Button newTab;
	private Pane wrapperPane = new Pane();
	private TurtleViewManager tvm;
	private List<Button> otherButtons;
	private ButtonMaker buttonMaker = new ButtonMaker();
	BorderPane bottomPanel=new BorderPane();
	public static final int SCENE_WIDTH = GUI_Configuration.SCENE_WIDTH; 
	public static final int SCENE_HEIGHT = GUI_Configuration.SCENE_HEIGHT-120;

	public static final String DEFAULT_FILE="data/Defaults.xml";

	private List<Label> stateLabels;
	private Model model;
	private XML xml;
	private Default myDefault;
	public GUI(Button b,Button n, Model m){
		runButton = b;
		newTab=n;
		model=m;
		xml=new XML(DEFAULT_FILE);
		myDefault=xml.getDefaults();
		createButtons();
		createRoot();
		initializeRightScreen();
		initializeMainScreen();
		initializeTurtle();
		createInputPanel();
		placeTurtle();
	}
	private void placeTurtle(){
		drawTurtle();
		wrapperPane.getChildren().add(tvm.getImage());
	}
	public void handleKeyInput(KeyCode code){
		if (tvm.isActive()){
			if (code == KeyCode.W){
				textAreaWriter.setText("fd 100");
				runButton.fire();
			}
			if (code == KeyCode.S){
				textAreaWriter.setText("back 100");
				runButton.fire();
			}
			if (code == KeyCode.A){
				textAreaWriter.setText("left 90");
				runButton.fire();
			}
			if (code == KeyCode.D){
				textAreaWriter.setText("right 90");
				runButton.fire();
			}
		}
	}
	private void initializeMainScreen(){
		background=new Rectangle(SCENE_WIDTH*5/8,SCENE_HEIGHT*12/17,Color.valueOf(myDefault.getBackgroundColor()));


		wrapperPane.setClip(new Rectangle(background.getLayoutX(),background.getLayoutY(),background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight()));
		wrapperPane.getChildren().add(background);
		createCanvas();
	}
	private void createInputPanel(){
		RunButtonFire fire=(b)->b.fire();
		//try to use lambdas to do this instead of passing the whole button
		
		realInput = new InputPanel(tvm, otherButtons,background,SCENE_WIDTH,SCENE_HEIGHT,myDefault,textAreaWriter,runButton);
		bottomPanel.setCenter(realInput.getBottomPanel());
		myRoot.setBottom(bottomPanel);
	}
	private void initializeRightScreen(){
		rightScreen.getChildren().add(rp.getPanel());
	}
	private void initializeTurtle(){
		tvm = new TurtleAnimator(new TurtleView(myDefault.getImageString(),myDefault.getPenColor()), gc);
		tvm.getImage().setOnMouseEntered(e->showStates(getStateLabels()));
		tvm.getImage().setOnMouseExited(e->removeStates());
	}
	private List<Label> getStateLabels(){
		return tvm.getStateLabels();
	}
	private void showStates(List<Label> turtleStates){	
		wrapperPane.getChildren().addAll(turtleStates);
		stateLabels=turtleStates;
		
	}
	private void removeStates(){
		wrapperPane.getChildren().removeAll(stateLabels);
	}
	private void drawTurtle(){	
		tvm.setX(background.getBoundsInLocal().getWidth()/2);
		tvm.setY(background.getBoundsInLocal().getHeight()/2);
	}
	private void createCanvas(){
		canvas = new Canvas(background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight());
		gc = canvas.getGraphicsContext2D();
		wrapperPane.getChildren().add(canvas);	
	}
	private void createTextArea(){
		TextArea textArea = new TextArea();
		 textAreaWriter=new TextAreaWriter(textArea);
		//Ratio chosen to impose symmetry,
		textArea.setMaxWidth(SCENE_WIDTH/3);
		textArea.setMinWidth(SCENE_WIDTH/3);
		textArea.setPromptText("Enter Code Here");
		bottomPanel.setLeft(textArea);
		myRoot.setBottom(bottomPanel);
	}
	private void createRoot() {
		createTextArea();
		lp = new LeftPanel(SCENE_WIDTH,SCENE_HEIGHT,model);
		rp = new RightPanel(textAreaWriter, runButton, SCENE_WIDTH,SCENE_HEIGHT);	
		myRoot.setCenter(wrapperPane);
		myRoot.setLeft(lp.getPanel());	
		myRoot.setRight(rightScreen);
	}
	public Tab getTab(){
		Tab tab=new Tab();
		tab.setContent(myRoot);
		return tab;
	}
	public String getCurrentLanguage(){
		return realInput.getCurrentLanguage();
	}
	public String getText(){
		return textAreaWriter.getText();
	}
	public void handleRunButton(Trajectory T){
		rp.getScrollPane().addText();
		tvm.moveTurtle(T,background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight());
		textAreaWriter.clear();
	}
	private void createButtons(){
		Button play = runButton;
		Button clear = buttonMaker.createButton("Clear", e -> {
			textAreaWriter.clear();
			textAreaWriter.setText("clear");
			gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
			
		});   
		Button loadPref= buttonMaker.createButton("Load Preferences",e-> handleLoadPref());
		Button save=buttonMaker.createButton("Save Preferences",e->handleSave());
		Button loadCommand=buttonMaker.createButton("Load Command", e->handleLoadCommand());
		Button newW=newTab;
		otherButtons = Arrays	.asList(play, clear,newW,loadPref,save,loadCommand);
	}
	private void handleSave(){	
		XMLWriter xmlWriter=new XMLWriter(myDefault);
		xmlWriter.getXML(realInput.getCurrentTurtleImage(), background.getFill(), realInput.getCurrentPenColor(), realInput.getCurrentLanguage());
	}
	
	private void handleLoadCommand(){
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("Select Command File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".logo files","*.logo"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		Stage ownerWindow = new Stage();
		File file = fileChooser.showOpenDialog(ownerWindow);
		try{
			FileReader f=new FileReader(file);
			BufferedReader buf = new BufferedReader(f); 
			String line = buf.readLine(); 
			StringBuilder sb = new StringBuilder();
			while(line != null){ 
				sb.append(line).append("\n"); 
				line = buf.readLine(); 
				}
			String fileAsString = sb.toString();
			textAreaWriter.setText(fileAsString);
			
			
		}
		catch(Exception e){
			SlogoAlert alert=new SlogoAlert("Not a valid file",e.getMessage());
			alert.showAlert();
		}
	}
	private void handleLoadPref() {
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("Select xml Default File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".xml files","*.xml"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		Stage ownerWindow = new Stage();
		File file = fileChooser.showOpenDialog(ownerWindow);
		try{
			xml=new XML(file);
			myDefault = xml.getDefaults();
			updateDefaults();
		}
		catch(Exception e){;
			SlogoAlert alert=new SlogoAlert("Not a valid file",e.getMessage());
			alert.showAlert();
		}
	}
	private void updateDefaults() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		updateInputPanel();
			
		updateBackground();
	}
	private void updateBackground(){
		background.setFill(Color.valueOf(myDefault.getBackgroundColor()));
	}
	
	private void updateInputPanel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		realInput.updateDefaults(myDefault);
	}
}