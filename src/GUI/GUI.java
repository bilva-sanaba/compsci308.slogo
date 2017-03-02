package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Arrays;
import java.util.List;

import GUI_BackgroundColorChooser.ColorButton;
import GUI_BackgroundColorChooser.ColorPickDefault;
import GUI_PenColorButton.PenColorButton;
import GUI_PenColorButton.PenColorWheel;
import GUI_PenColorButton.RandomPenColorButton;
import configuration.Trajectory;
import configuration.TurtleState;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private TextArea textArea=new TextArea();
	private Canvas canvas;
	private GraphicsContext gc;
	private CommandScrollPane commandScrollPane=new CommandScrollPane(textArea);
	private Rectangle background;
	private Pane wrapperPane = new Pane();
	private ColorButton cb = new ColorPickDefault(wrapperPane);
	private TurtleViewManager tvm;
	private PenColorButton pb;
	private List<Button> otherButtons;
	private Stage myStage;
	private String currentLanguage = "English";
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	private HBox inputPanel;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
	
	public GUI(Stage stage){
		wrapperPane.setStyle("-fx-background-color: white;");
		myRoot=createRoot();
		myStage = stage;
		myStage.setScene(createScene());
		show();
		createCanvas();
		initializeTurtle();
		pb = new PenColorWheel(tvm);
		addPenButton();
	
	}
	
	
	private void initializeTurtle(){
		tvm = new TurtleViewManager(new TurtleView(), gc);
		System.out.println(wrapperPane.getBoundsInLocal().getHeight()/2);
		
		tvm.setX(wrapperPane.getBoundsInLocal().getWidth()/2);
		tvm.setY(wrapperPane.getBoundsInLocal().getHeight()/2);
		wrapperPane.getChildren().add(tvm.getImage());
//		System.out.println(wrapperPane.getBoundsInLocal().getHeight()/2);
	}
	
	private void createCanvas(){
//		canvas = new Canvas(wrapperPane.getBoundsInLocal().getWidth(),wrapperPane.getBoundsInLocal().getHeight());
//		
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		testDraw(gc);
//		background = new Rectangle(SCENE_WIDTH-commandScrollPane.getScrollPane().getWidth()-100,SCENE_HEIGHT-inputPanel.getHeight(),Color.PURPLE);
		canvas = new Canvas(SCENE_WIDTH-commandScrollPane.getScrollPane().getWidth()-100,SCENE_HEIGHT-inputPanel.getHeight());
//		wrapperPane.getChildren().add(background);
		wrapperPane.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();
		 
		gc.setStroke(Color.BLUE);
		gc.setFill(Color.BLUE);
		 gc.strokeLine(40, 10, 10, 40);
		
//		System.out.println(wrapperPane.getBoundsInLocal().getHeight()/2);
		
	}
	private void testDraw(GraphicsContext gc){
		gc.setFill(Color.BLUE);
		gc.fillRect(wrapperPane.getBoundsInLocal().getWidth()/2,wrapperPane.getBoundsInLocal().getHeight()/2,100,100);
	}
	private Scene createScene() {
        Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
	
	private BorderPane createRoot() {
        BorderPane bp = new BorderPane();
        bp.setBottom(initInputPanel());
        bp.setLeft(new Rectangle(100,100,Color.RED));
        createScrollPane();
        bp.setRight(commandScrollPane.getScrollPane());
        
//        System.out.println(wrapperPane.getBoundsInLocal().getHeight()/2);
        bp.setCenter(wrapperPane);
        
//        System.out.println(wrapperPane.getBoundsInLocal().getHeight()/2);
        return bp;
    }
	
	private ChoiceBox<String> createLanguageBox() {
		ChoiceBox<String> language = createChoiceBox(Languages, (observable, oldValue, newValue) -> {
            setLanguage(newValue.toString());
            });
		return language;
	}
	private void setLanguage(String language){
		currentLanguage=language;
	}
	public String getCurrentLanguage(){
		return currentLanguage;
	}
	
	private void createScrollPane(){
		commandScrollPane=new CommandScrollPane(textArea);
		commandScrollPane.getScrollPane().setPrefSize(SCENE_WIDTH/4,SCENE_HEIGHT);
		commandScrollPane.getScrollPane().setLayoutX(SCENE_WIDTH*3/4);
		commandScrollPane.getScrollPane().setLayoutY(0);
	}
	private void addPenButton(){
        inputPanel.getChildren().add(createLabel("Pick Pen Color: "));
		inputPanel.getChildren().add(pb.getButton());
	}
	 private Node initInputPanel() {
		 	createButtons();
	        inputPanel = new HBox();
	        textArea = new TextArea("Enter code here");
	        inputPanel.getChildren().add(textArea);
	        inputPanel.getChildren().addAll(otherButtons);
	        inputPanel.getChildren().add(createLabel("Pick Background Color: "));
	        inputPanel.getChildren().add(cb.getButton());
	        inputPanel.getChildren().add(createLanguageBox());
	        return inputPanel;
	 }
	 private void handleRunButton(){
		commandScrollPane.addText();
		textArea.clear();
		Trajectory T= new Trajectory();
		TurtleState nextState= new TurtleState(0,0,90,true,true);
		T.addLast(nextState);
		tvm.moveTurtle(T);
	 }
	 private void createButtons(){
		    Button play = createButton("Run", e -> handleRunButton());
	        Button clear = createButton("Clear", e -> {
	        	textArea.clear();
	        });       
	        otherButtons = Arrays.asList(play, clear);
	 }
	 private Button createButton(String label, EventHandler<ActionEvent> e) {
	        Button b = new Button();
	        b.setText(label);
	        b.setOnAction(e);
	        return b;
	 }

	 private Label createLabel(String text) {
	        Label label = new Label(text);
	        label.setTextFill(Color.BLACK);
	        return label;
	        
	    }
	    
	    private ChoiceBox<String> createChoiceBox(List<String> items, ChangeListener<? super Number> listener) {
	        ChoiceBox<String> cb = createChoiceBox(items);
	        cb.getSelectionModel().selectedIndexProperty().addListener(listener);
	        return cb;
	    }
	    private ChoiceBox<String> createChoiceBox(List<String> items) {
	        ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(items));
	        cb.getSelectionModel().selectFirst();
	        return cb;
	    }
	private void show(){
		myStage.show();
	}
}
