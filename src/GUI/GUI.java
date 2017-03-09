package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import GUI_TurtleMovers.TurtleAnimator;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;
import configuration.Trajectory;
import error.SlogoAlert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
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
	BorderPane bottomPanel=new BorderPane();
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;

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
		myRoot.setLeft(lp.getPanel());	
		myRoot.setRight(rightScreen);
		rightScreen.getChildren().add(rp.getPanel());
		background = new Rectangle(SCENE_WIDTH-lp.getPanel().getWidth()-rp.getPanel().getWidth(),SCENE_HEIGHT-bottomPanel.getBoundsInLocal().getHeight(),Color.WHITE);


		background=new Rectangle(750,480,Color.valueOf(myDefault.getBackgroundColor()));


		wrapperPane.setClip(new Rectangle(background.getLayoutX(),background.getLayoutY(),background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight()));;
		wrapperPane.getChildren().add(background);
		createCanvas();
		initializeTurtle();
		realInput = new InputPanel(tvm, otherButtons,background,SCENE_WIDTH,SCENE_HEIGHT,myDefault);
		bottomPanel.setCenter(realInput.getBottomPanel());
		myRoot.setBottom(bottomPanel);
		placeTurtle();
		
	}
	private void placeTurtle(){
		drawTurtle();
		wrapperPane.getChildren().add(tvm.getImage());
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
		textArea = new TextArea();
		textArea.setPromptText("Enter Code Here");
		bottomPanel.setLeft(textArea);
		myRoot.setBottom(bottomPanel);
	}
	private void createRoot() {
		createTextArea();
		lp = new LeftPanel(SCENE_WIDTH,SCENE_HEIGHT,model);
		rp = new RightPanel(textArea, runButton, SCENE_WIDTH,SCENE_HEIGHT);	
		myRoot.setCenter(wrapperPane);
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
	public void handleRunButton(Trajectory T){
		rp.getScrollPane().addText();
		tvm.moveTurtle(T,background.getBoundsInLocal().getWidth(),background.getBoundsInLocal().getHeight());
		textArea.clear();
	}
	private void createButtons(){
		Button play = runButton;
		Button clear = createButton("Clear", e -> {
			textArea.clear();
			textArea.setText("clear");
			gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
			
		});   
		Button load= createButton("Load Preferences",e-> handleLoad());
		Button save=createButton("Save Preferences",e->handleSave());
		Button newW=newTab;
		otherButtons = Arrays	.asList(play, clear,newW,load,save);
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
	private Button createButton(String label, EventHandler<ActionEvent> e) {
		Button b = new Button();
		b.setText(label);
		b.setOnAction(e);
		return b;
	}
	public static Label createLabel(String text) {
		Label label = new Label(text);
		label.setTextFill(Color.BLACK);
		return label;  
	}

}
