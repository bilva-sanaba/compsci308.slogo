package GUI;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GUI_BackgroundColorChooser.ColorButton;
import GUI_BackgroundColorChooser.ColorPickDefault;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private Canvas canvas = new Canvas(500,500);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private Pane wrapperPane = new Pane();
	private ColorButton cb = new ColorPickDefault(wrapperPane);
	private TurtleView t = new TurtleView();
	private List<Button> otherButtons;
	private Stage myStage;
	private String currentLanguage = "English";
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
	public GUI(Stage stage){
		wrapperPane.setStyle("-fx-background-color: black;");
		myRoot=createRoot();
		myStage = stage;
		myStage.setScene(createScene());
		show();
	}
	
	private void setTurtle(){
		wrapperPane.getChildren().add(t.getImage());
	}
	
	private void createCanvas(){
		wrapperPane.getChildren().add(canvas);
	}
	
	private Scene createScene() {
        Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
	
	private BorderPane createRoot() {
        BorderPane bp = new BorderPane();
        bp.setBottom(initInputPanel());
        bp.setLeft(new Rectangle(100,100,Color.RED));
        bp.setRight(new Rectangle(100,100,Color.BLUE));
        bp.setCenter(wrapperPane);
        createCanvas();
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
	 private Node initInputPanel() {
	        createButtons();
	        HBox inputPanel = new HBox();
	        TextArea t = new TextArea("Enter code here");
	        inputPanel.getChildren().add(t);
	        inputPanel.getChildren().addAll(otherButtons);
	        inputPanel.getChildren().add(cb.getButton());
	        inputPanel.getChildren().add(createLanguageBox());
	        return inputPanel;
	 }
	 private void createButtons(){
		    Button play = createButton("Run", e -> setTurtle());
	        Button clear = createButton("Clear", e -> {
	        	gc.setFill(Color.CYAN);
	        	wrapperPane.setStyle("-fx-background-color: blue;");
	        });
	        
	        Button help = createButton("Help", e -> gc.setFill(Color.BLUE));
	        
	        otherButtons = Arrays.asList(play, clear,help);
	 }
	 private Button createButton(String label, EventHandler<ActionEvent> e) {
	        Button b = new Button();
	        b.setText(label);
	        b.setOnAction(e);
	        return b;
	 }

	 private Label createLabel(String text) {
	        Label label = new Label(text);
	        label.setTextFill(Color.WHITE);
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
