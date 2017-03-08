package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.Arrays;
import java.util.List;
import GUI_TurtleMovers.TurtleAnimator;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;
import configuration.Trajectory;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Model;
import xml.Default;
import xml.XML;

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
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");

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


		background=new Rectangle(800,490,Color.valueOf(myDefault.getBackgroundColor()));

		//background=new Rectangle(800,480,Color.WHITE);


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

		tvm = new TurtleAnimator(new TurtleView(myDefault.getImageString()), gc);

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
		tvm.moveTurtle(T,750,480);
		textArea.clear();
	}
	private void createButtons(){
		Button play = runButton;
		Button clear = createButton("Clear", e -> {
			textArea.clear();
			textArea.setText("clear");
			play.fire();
		});     
		Button newW=newTab;
		otherButtons = Arrays	.asList(play, clear,newW);
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
