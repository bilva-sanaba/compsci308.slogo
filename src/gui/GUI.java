package gui;
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
import error.SlogoAlert;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.executables.keymouse.ClickHandler;
import gui.executables.keymouse.InputHandler;
import gui.executables.keymouse.WASDMover;
import gui.language.Language;
import gui.movement.TurtleAnimator;
import gui.movement.TurtleView;
import gui.movement.TurtleViewManager;
import gui.panels.InputPanel;
import gui.panels.LeftPanel;
import gui.panels.RightPanel;
import gui.updaters.DisplayUpdater;
import gui.updaters.TurtleUpdater;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.UnmodifiableWorld;
import model.configuration.SingleTurtleState;
import model.configuration.Trajectory;
import model.exceptions.CommandException;
import xml.Default;
import xml.XML;
import xml.XMLWriter;

/**
 * This is the class which creates the display for each workspace created.
 * @author Bilva
 * @author Alex
 *
 */
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
	private FireableButton fireableButton;
	private Button newTab;
	private Pane wrapperPane = new Pane();
	private TurtleViewManager tvm;
	private List<Button> otherButtons;
	private ButtonMaker buttonMaker = new ButtonMaker();
	private TextAreaWriter textAreaWriter;
	BorderPane bottomPanel=new BorderPane();
	private Map<Integer, TurtleViewManager> existingTurtles;
	private UnmodifiableWorld currentWorld;
	private InputHandler inputHandler = new WASDMover();
	private Palette myPalette = new Palette();
	private ClickHandler clickHandler;
	public static final int GUI_WIDTH = GUI_Configuration.SCENE_WIDTH; 
	public static final int GUI_HEIGHT = GUI_Configuration.SCENE_HEIGHT-120;
	public static final double BACKGROUND_WIDTH = GUI_WIDTH*5/8;
	public static final double BACKGROUND_HEIGHT =GUI_HEIGHT*12/17;

	public static final String DEFAULT_FILE="data/Defaults.xml";

	private List<Label> stateLabels;
	private XML xml;
	private Default myDefault;
	/**
	 * When created a GUI is created in a new workspace with a turtle at the center
	 * @param b Button which will be placed in the GUI and when fired run the handleRunButton method
	 * @param n Button which will be placed in the GUI and when fired create a new workspace
	 */
	public GUI(Button b,Button n){
		runButton = b;
		fireableButton = new FireableButton(runButton);
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
		initializeClickHandler();
	}
	/**
	 * Creates an object which allows for actions to occur when a turtle is clicked
	 */
	private void initializeClickHandler(){
		clickHandler = new ClickHandler(textAreaWriter,fireableButton,new Language(realInput.getCurrentLanguage()),existingTurtles);
	}
	private void placeTurtle(TurtleViewManager tvm){
		drawTurtle(tvm);
		wrapperPane.getChildren().add(tvm.getImage());
	}
	/**
	 * Processes keystrokes and takes appropriate actions
	 * @param code contains the Key pressed 
	 */
	public void handleKeyInput(KeyCode code){
		if (currentWorld!=null){
			inputHandler.handleKeyInput(code,textArea,fireableButton,currentWorld);
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
	private String getPenColorString(String backgroundColor){
		return Color.valueOf(myDefault.getBackgroundColor()).invert().toString();
	}
	private void createInputPanel(){
		realInput = new InputPanel(tvm, otherButtons,myDefault,textAreaWriter,runButton,myPalette);
		bottomPanel.setCenter(realInput.getBottomPanel());
		tvm.addTurtleComboBox(realInput.getTurtleComboBox());
		myRoot.setBottom(bottomPanel);
	}
	private void initializeRightScreen(){
		rightScreen.getChildren().add(rp.getPanel());
	}
	private void initializeTurtle(){

		tvm = new TurtleAnimator(new TurtleView(myDefault.getImageString().get(0),getPenColorString(myDefault.getBackgroundColor())), gc,myPalette);

		existingTurtles = new HashMap<Integer, TurtleViewManager>();
		existingTurtles.put(0, tvm);
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
		rp = new RightPanel(textAreaWriter, fireableButton, GUI_WIDTH,GUI_HEIGHT);	
		myRoot.setCenter(wrapperPane);
		myRoot.setLeft(lp.getPanel());	
		myRoot.setRight(rightScreen);
	}
	/**
	 * Generates a tab containing all Nodes in GUI
	 * @return tab containing all nodes in GUI
	 */
	public Tab getTab(){
		Tab tab=new Tab();
		tab.setContent(myRoot);
		return tab;
	}
	/**
	 * 
	 * @return String representation of GUI's current language
	 */
	public String getCurrentLanguage(){
		return realInput.getCurrentLanguage();
	}
	/**
	 * 
	 * @return String entered into GUI's text area
	 */
	public String getText(){
		return textArea.getText();
	}

	private Object makeClass(Class<?>clazz,TurtleView myHomie,Palette p) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<?> ctor = clazz.getDeclaredConstructor(TurtleView.class,GraphicsContext.class,Palette.class);
		Object o = ctor.newInstance(myHomie, gc,p);
		return o;
	}
	/**
	 * Takes a world variable from Model and updates relevant states using new world
	 * Updates include adding text to command scroll pane, updating turtle states, creating new turtles,
	 * updating variable and user command display, and updating background color, pensize, and pencolor. 
	 * @param w world variable from model
	 * 
	 */
	public void handleRunButton(UnmodifiableWorld w) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, CommandException{
		currentWorld=w;
		rp.getScrollPane().addText();
		Trajectory updates = w.getTrajectoryUpdates();
		if (updates.getLast()!=null){

			for(SingleTurtleState turtle: updates.getLast()){
				if(!existingTurtles.keySet().contains(turtle.getID())){
					TurtleView myHomie = new TurtleView(myDefault.getImageString().get(0),getPenColorString(myDefault.getBackgroundColor()));

					Class<?>clazz=Class.forName(existingTurtles.get(0).getClass().getName());
					TurtleViewManager newTurtle = (TurtleViewManager) makeClass(clazz,myHomie,myPalette);
					newTurtle.addTurtleComboBox(realInput.getTurtleComboBox());
					placeTurtle(newTurtle);
					existingTurtles.put(turtle.getID(), newTurtle);
					clickHandler.update(existingTurtles);
					configureStateDisplay(newTurtle);
				}
			}
			TurtleUpdater tu = new TurtleUpdater();
			tu.moveTurtles(updates,existingTurtles);

		}
		updateVariables();
		updateUserCommands();
		DisplayUpdater du = new DisplayUpdater();
		du.updateDisplay(w, myPalette, background, gc, existingTurtles);
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
			textArea.setText("clearscreen");

			gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
			runButton.fire();


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
		xmlWriter.getXML(realInput.getTurtleComboBox().getTurtleChooser().getItems(), background.getFill(), realInput.getCurrentLanguage());
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

	}


	private void updateInputPanel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		realInput.updateDefaults(myDefault);
	}
}