package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI_Objects.ButtonMaker;
import GUI_Objects.InputHandler;
import GUI_Objects.Palette;
import GUI_Objects.WASDMover;
import GUI_TurtleMovers.TurtleAnimator;
import GUI_TurtleMovers.TurtleDotMover;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;

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
import model.UnmodifiableWorld;
import model.World;
import model.configuration.SingleTurtleState;
import model.configuration.Trajectory;
import model.exceptions.CommandException;
import xml.Default;
import xml.XML;
import xml.XMLWriter;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private TextArea textArea=new TextArea();
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
	private TextAreaWriter textAreaWriter;
	BorderPane bottomPanel=new BorderPane();
	private Map<Integer, TurtleViewManager> activeTurtles;
	private UnmodifiableWorld currentWorld;
	public InputHandler inputHandler=new WASDMover();
	private Palette myPalette = new Palette();
	public static final int GUI_WIDTH = GUI_Configuration.SCENE_WIDTH; 
	public static final int GUI_HEIGHT = GUI_Configuration.SCENE_HEIGHT-120;
	public static final double BACKGROUND_WIDTH = GUI_WIDTH*5/8;
	public static final double BACKGROUND_HEIGHT =GUI_HEIGHT*12/17;

	public static final String DEFAULT_FILE="data/Defaults.xml";

	private List<Label> stateLabels;
	private XML xml;
	private Default myDefault;
	public GUI(Button b,Button n){
		runButton = b;
		newTab=n;
		xml=new XML(DEFAULT_FILE);
		myDefault=xml.getDefaults();
		createButtons();
		createRoot();
		initializeRightScreen();
		initializeMainScreen();
		initializeTurtle();
		createInputPanel();
		placeTurtle(tvm);
	}
	private void placeTurtle(TurtleViewManager tvm){
		drawTurtle(tvm);
		wrapperPane.getChildren().add(tvm.getImage());
	}
	public void handleKeyInput(KeyCode code){
		if (currentWorld!=null){
			inputHandler.handleKeyInput(code,textArea,runButton,currentWorld);
		}
	}

	private void configureStateDisplay(TurtleViewManager tvm){
		tvm.getImage().setOnMouseEntered(e->showStates(getStateLabels(tvm)));
		tvm.getImage().setOnMouseExited(e->removeStates(tvm));
	}

	private void initializeMainScreen(){
		background=new Rectangle(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,Color.valueOf(myDefault.getBackgroundColor()));
		wrapperPane.setClip(new Rectangle(background.getLayoutX(),background.getLayoutY(),background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight()));
		wrapperPane.getChildren().add(background);
		createCanvas();
	}
	private void createInputPanel(){
		realInput = new InputPanel(tvm, otherButtons,background, myDefault,textAreaWriter,runButton,myPalette);
		bottomPanel.setCenter(realInput.getBottomPanel());
		myRoot.setBottom(bottomPanel);
	}
	private void initializeRightScreen(){
		rightScreen.getChildren().add(rp.getPanel());
	}
	private void initializeTurtle(){

		tvm = new TurtleAnimator(new TurtleView(myDefault.getImageString(),myDefault.getPenColor()), gc,myPalette);

		activeTurtles = new HashMap<Integer, TurtleViewManager>();
		activeTurtles.put(0, tvm);
		configureStateDisplay(tvm);
	}
	private List<Label> getStateLabels(TurtleViewManager tvm){
		return tvm.getStateLabels();
	}
	private void showStates(List<Label> turtleStates){	
		wrapperPane.getChildren().addAll(turtleStates);
		stateLabels=turtleStates;
	}
	private void removeStates(TurtleViewManager tvm){
		wrapperPane.getChildren().removeAll(stateLabels);
	}

	private void drawTurtle(TurtleViewManager tvm){	
		tvm.setX(background.getBoundsInLocal().getWidth()/2);
		tvm.setY(background.getBoundsInLocal().getHeight()/2);
	}
	private void createCanvas(){
		canvas = new Canvas(background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight());
		gc = canvas.getGraphicsContext2D();
		wrapperPane.getChildren().add(canvas);	
	}
	private void createTextArea(){
		textArea = new TextArea();
		//Ratio chosen to impose symmetry,
		textAreaWriter = new TextAreaWriter(textArea);
		textArea.setMaxWidth(GUI_WIDTH/3);
		textArea.setMinWidth(GUI_WIDTH/3);
		textArea.setPromptText("Enter Code Here");
		bottomPanel.setLeft(textArea);
		myRoot.setBottom(bottomPanel);
	}
	private void createRoot() {
		createTextArea();
		lp = new LeftPanel(GUI_WIDTH,GUI_HEIGHT,currentWorld);
		rp = new RightPanel(textAreaWriter, runButton, GUI_WIDTH,GUI_HEIGHT);	
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
		return textArea.getText();
	}

	private Object makeClass(Class<?>clazz,TurtleView myHomie,Palette p) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<?> ctor = clazz.getDeclaredConstructor(TurtleView.class,GraphicsContext.class,Palette.class);
		Object o = ctor.newInstance(myHomie, gc,p);
		return o;
	}

	public void handleRunButton(UnmodifiableWorld w) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, CommandException{
		currentWorld=w;
		rp.getScrollPane().addText();
		Trajectory updates = w.getTrajectoryUpdates();
		if (updates.getLast()!=null){
			
			for(SingleTurtleState turtle: updates.getLast()){
				if(!activeTurtles.keySet().contains(turtle.getID())){
					TurtleView myHomie = new TurtleView(myDefault.getImageString(),myDefault.getPenColor());
					Class<?>clazz=Class.forName(activeTurtles.get(0).getClass().getName());
					TurtleViewManager newTurtle = (TurtleViewManager) makeClass(clazz,myHomie,myPalette);
					placeTurtle(newTurtle);
					activeTurtles.put(turtle.getID(), newTurtle);
					configureStateDisplay(newTurtle);
				}
			}
			TurtleUpdater tu = new TurtleUpdater();
			tu.moveTurtles(updates,activeTurtles);
			
		}
		updateVariables();
		updateUserCommands();
		DisplayUpdater du = new DisplayUpdater();
		du.updatePalette(currentWorld, myPalette);
		du.updateBackground(currentWorld, background, myPalette);
		textArea.clear();
	}
	private void updateVariables() throws CommandException{
		lp.updateVariables(currentWorld);
	}
	private void updateUserCommands() throws CommandException{
		lp.updateCommands(currentWorld);
	}
	private void createButtons(){
		Button play = runButton;
		Button clear = buttonMaker.createButton("Clear", e -> {
			textArea.clear();
			textArea.setText("clear");
			gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

		});   
		Button load= buttonMaker.createButton("Load Preferences",e-> handleLoad());
		Button save=buttonMaker.createButton("Save Preferences",e->handleSave());
		Button loadCommand=buttonMaker.createButton("Load Command", e->handleLoadCommand());
		Button newW=newTab;
		otherButtons = Arrays	.asList(play, clear,newW,load,save,loadCommand);
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
			textArea.setText(fileAsString);
			textAreaWriter.setText(fileAsString);
			
			
		}
		catch(Exception e){
			SlogoAlert alert=new SlogoAlert("Not a valid file",e.getMessage());
			alert.showAlert();
		}
	}
	private void handleSave(){	
		XMLWriter xmlWriter=new XMLWriter(myDefault);
		xmlWriter.getXML(realInput.getCurrentTurtleImage(), background.getFill(), realInput.getCurrentPenColor(), realInput.getCurrentLanguage());
	}
	private void handleLoad() {
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