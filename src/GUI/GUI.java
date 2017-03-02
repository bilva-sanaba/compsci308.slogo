package GUI;


import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.Arrays;
import java.util.List;


import GUI_BackgroundColorChooser.ColorButton;
import GUI_BackgroundColorChooser.ColorPickDefault;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private TextArea textArea=new TextArea();
	private Canvas canvas;
	private CommandScrollPane commandScrollPane=new CommandScrollPane(textArea);
	private Pane wrapperPane = new Pane();
	private ColorButton cb = new ColorPickDefault(wrapperPane);


	private TurtleViewManager tvm = new TurtleViewManager(new TurtleView());

	private List<Button> otherButtons;
	private Stage myStage;
	private String currentLanguage = "English";
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
	private static final String HELP_WINDOW_TITLE="Syntax";
	private static final String HELP_URL="help.html";
	public GUI(Stage stage){
		wrapperPane.setStyle("-fx-background-color: black;");
		myRoot=createRoot();
		myStage = stage;
		myStage.setScene(createScene());
		show();
		initializeTurtle();
	}
	

	private ComboBox<ImageView> selectTurtle(){
		ComboBox<ImageView> turtleChoice=new ComboBox<ImageView>();
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"))));
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle2.gif"))));
		turtleChoice.valueProperty().addListener((x, y, newValue) -> {
			tvm.getImage().setImage(newValue.getImage());
		});
		return turtleChoice;
	}
	
	
	private void initializeTurtle(){
		tvm.setX(wrapperPane.getBoundsInLocal().getWidth()/2);
		tvm.setY(wrapperPane.getBoundsInLocal().getHeight()/2);
		wrapperPane.getChildren().add(tvm.getImage());

	}
	
	private void createCanvas(){
		canvas = new Canvas(wrapperPane.getBoundsInLocal().getWidth(),wrapperPane.getBoundsInLocal().getHeight());
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
        createScrollPane();
        bp.setRight(commandScrollPane.getScrollPane());
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
	
	private void createScrollPane(){
		commandScrollPane=new CommandScrollPane(textArea);
		commandScrollPane.getScrollPane().setPrefSize(SCENE_WIDTH/4,SCENE_HEIGHT);
		commandScrollPane.getScrollPane().setLayoutX(SCENE_WIDTH*3/4);
		commandScrollPane.getScrollPane().setLayoutY(0);
	}
	
	 private Node initInputPanel() {
		 	createButtons();
	        HBox inputPanel = new HBox();
	        textArea = new TextArea("Enter code here");
	        inputPanel.getChildren().add(textArea);
	        inputPanel.getChildren().addAll(otherButtons);
	        inputPanel.getChildren().add(cb.getButton());

	       ComboBox<ImageView>turtleChoice=selectTurtle();
	       inputPanel.getChildren().add(turtleChoice);
	        inputPanel.getChildren().add(createLanguageBox());

	        return inputPanel;
	 }
	 private void handleRunButton(){
		commandScrollPane.addText();

		textArea.clear();		

	 }
	 private void createButtons(){
		    Button play = createButton("Run", e -> handleRunButton());
	        Button clear = createButton("Clear", e -> {
	        	textArea.clear();
	        	commandScrollPane.clearScrollPane();
	        });
	        Button help=createButton("Help",e -> handleHelpButton());
	        otherButtons = Arrays.asList(play, clear,help);
	 }
	 private Button createButton(String label, EventHandler<ActionEvent> e) {
	        Button b = new Button();
	        b.setText(label);
	        b.setOnAction(e);
	        return b;
	 }
	 private void handleHelpButton(){
		
		 
         Group root = new Group();
         WebView browser = new WebView();
         Scene helpScene = new Scene(root);
         Stage helpStage = new Stage();
         helpStage.setTitle("Help Menu");
         helpStage.setScene(helpScene);
         URL url = getClass().getResource(HELP_URL);
         browser.getEngine().load(url.toExternalForm());
         root.getChildren().add(browser);
         helpStage.show();
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
