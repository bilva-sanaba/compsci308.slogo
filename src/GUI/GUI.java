package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import GUI_RetrievableCode.CommandScrollPane;
import GUI_RetrievableCode.VariableScrollPane;
import GUI_TurtleMovers.TurtleAnimator;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;
import configuration.Trajectory;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.Model;

public class GUI {
	private TabPane myRut=new TabPane();
	private BorderPane myRoot = new BorderPane();
	private TextArea textArea=new TextArea();
	private Canvas canvas;
	private InputPanel realInput;
	private GraphicsContext gc;
	private LeftPanel lp;
	private RightPanel rp;
	private CommandScrollPane commandScrollPane;
	private Shape background;
	private VariableScrollPane variableScrollPane;
	private Button runButton;
	private Button newTab;
	private Pane wrapperPane = new Pane();
	private TurtleViewManager tvm;
	private List<Button> otherButtons;
	private Stage myStage;
	BorderPane bottomPanel=new BorderPane();
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
	private List<Label> stateLabels;
	private Model model;
	public GUI(Button b,Button n, Model m){
		runButton = b;
		newTab=n;
		model=m;
		createButtons();
		createRoot();
		//myStage = stage;
		//myStage.setScene(createScene());
	
		myRoot.setLeft(lp.getPanel());
		myRoot.setRight(rp.getPanel());
		background = new Rectangle(SCENE_WIDTH-lp.getPanel().getWidth()-rp.getPanel().getWidth(),SCENE_HEIGHT-bottomPanel.getBoundsInLocal().getHeight(),Color.WHITE);
		background=new Rectangle(800,490,Color.WHITE);
		wrapperPane.getChildren().add(background);
		createCanvas();
		initializeTurtle();
		realInput = new InputPanel(tvm, otherButtons,background,SCENE_WIDTH,SCENE_HEIGHT);
		bottomPanel.setCenter(realInput.getBottomPanel());
		myRoot.setBottom(bottomPanel);
		placeTurtle();
	}
	private void placeTurtle(){
		drawTurtle();
		wrapperPane.getChildren().add(tvm.getImage());
	}
	private void initializeTurtle(){
		tvm = new TurtleRegularMover(new TurtleView(), gc, SCENE_WIDTH-lp.getPanel().getWidth()-rp.getPanel().getWidth());
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
//		gc.clearRect(0, 0, wrapperPane.getWidth(), wrapperPane.getHeight());
		tvm.moveTurtle(T,wrapperPane.getBoundsInLocal().getWidth(),wrapperPane.getBoundsInLocal().getHeight());
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
		otherButtons = Arrays.asList(play, clear,newW);
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

	private void show(){
		myStage.show();
	}
}
