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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private TextArea textArea=new TextArea();
	private Canvas canvas;
	private InputPanel realInput;
	private GraphicsContext gc;
	private CommandScrollPane commandScrollPane;
	private Shape background;
	private VariableScrollPane variableScrollPane;
	private Button runButton;
	private Pane wrapperPane = new Pane();
	private TurtleViewManager tvm;
	private List<Button> otherButtons;
	private Stage myStage;
	BorderPane bottomPanel=new BorderPane();
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");


	public GUI(Stage stage,Button b){
		runButton = b;
		createButtons();
		createRoot();
		myStage = stage;
		myStage.setScene(createScene());
		show();
		background = new Rectangle(SCENE_WIDTH-commandScrollPane.getScrollPane().getWidth()-variableScrollPane.getScrollPane().getWidth(),SCENE_HEIGHT-bottomPanel.getHeight(),Color.WHITE);
		wrapperPane.getChildren().add(background);
		createCanvas();
		initializeTurtle();
		realInput = new InputPanel(tvm, otherButtons,background);
		bottomPanel.setCenter(realInput.getBottomPanel());
		placeTurtle();
	}
	private void placeTurtle(){
		drawTurtle();
		wrapperPane.getChildren().add(tvm.getImage());
	}
	private void initializeTurtle(){
		tvm = new TurtleAnimator(new TurtleView(), gc, SCENE_WIDTH-commandScrollPane.getScrollPane().getWidth()-variableScrollPane.getScrollPane().getWidth());
	}
	private void drawTurtle(){
		tvm.setX(wrapperPane.getBoundsInLocal().getWidth()/2);
		tvm.setY(wrapperPane.getBoundsInLocal().getHeight()/2);
	}
	private void createCanvas(){
		canvas = new Canvas(SCENE_WIDTH-commandScrollPane.getScrollPane().getWidth()-variableScrollPane.getScrollPane().getWidth(),SCENE_HEIGHT-textArea.getHeight());
		gc = canvas.getGraphicsContext2D();
		wrapperPane.getChildren().add(canvas);	
	}
	private Scene createScene() {
		Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
		return scene;
	}
	private void createTextArea(){
		textArea = new TextArea();
		textArea.setPromptText("Enter Code Here");
		bottomPanel.setLeft(textArea);
		myRoot.setBottom(bottomPanel);
	}
	private void createRoot() {
		createTextArea();
		createVariableScroller();
		myRoot.setLeft(variableScrollPane.getScrollPane());
		createScrollPane();
		myRoot.setRight(commandScrollPane.getScrollPane());	
		myRoot.setCenter(wrapperPane);       
	}
	public String getCurrentLanguage(){
		return realInput.getCurrentLanguage();
	}
	public String getText(){
		return textArea.getText();
	}
	private void createScrollPane(){
		commandScrollPane=new CommandScrollPane(textArea,otherButtons.get(0));
		commandScrollPane.getScrollPane().setPrefSize(SCENE_WIDTH/4,SCENE_HEIGHT);
		commandScrollPane.getScrollPane().setLayoutX(SCENE_WIDTH*3/4);
		commandScrollPane.getScrollPane().setLayoutY(0);
	}
	private void createVariableScroller(){
		HashMap<String,Integer>map=new HashMap<String,Integer>();
		map.put("variable",1);
		map.put("new_number", 2);
		map.put("a",1);
		map.put("v", 2);
		map.put("n",1);
		map.put("2", 2);
		map.put("3",1);
		map.put("7", 2);
		variableScrollPane=new VariableScrollPane();
		variableScrollPane.getScrollPane().setPrefSize(SCENE_WIDTH/8,SCENE_HEIGHT);
		variableScrollPane.getScrollPane().setLayoutX(0);
		variableScrollPane.getScrollPane().setLayoutY(0);
		variableScrollPane.add(map);
	}
	public void handleRunButton(Trajectory T){
		commandScrollPane.addText();
//		gc.clearRect(0, 0, wrapperPane.getWidth(), wrapperPane.getHeight());
		tvm.moveTurtle(T,wrapperPane.getBoundsInLocal().getWidth(),wrapperPane.getBoundsInLocal().getHeight());
		textArea.clear();
	}
	private void createButtons(){
		Button play = runButton;
		Button clear = createButton("Clear", e -> {
			textArea.clear();
			commandScrollPane.clearScrollPane();
			textArea.setText("clear");
			play.fire();
		});     
		otherButtons = Arrays.asList(play, clear);
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
