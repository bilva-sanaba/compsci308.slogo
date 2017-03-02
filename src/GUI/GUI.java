package GUI;


import javafx.scene.canvas.Canvas;


import java.net.URL;

import javafx.scene.canvas.GraphicsContext;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import GUI_BackgroundColorChooser.ColorButton;
import GUI_BackgroundColorChooser.ColorPickDefault;
import GUI_PenColorButton.PenColorButton;
import GUI_PenColorButton.PenColorWheel;
import GUI_PenColorButton.RandomPenColorButton;
import configuration.Trajectory;
import configuration.TurtleState;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private TextArea textArea=new TextArea();
	private Canvas canvas;
	private GraphicsContext gc;
	private CommandScrollPane commandScrollPane;
	private VariableScrollPane variableScrollPane;
	private Rectangle background;
	private Button runButton;
	private Pane wrapperPane = new Pane();
	private ColorButton cb = new ColorPickDefault(wrapperPane);
	private TurtleViewManager tvm;
	private PenColorButton pb;
	private List<Button> otherButtons;
	private Stage myStage;
	private String currentLanguage = "English";
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	private Pane inputPanel;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");

	private static final String HELP_WINDOW_TITLE="Syntax";
	private static final String HELP_URL="help.html";

	public GUI(Stage stage,Button b){
		wrapperPane.setStyle("-fx-background-color: white;");
		runButton = b;
		myRoot=createRoot();
		myStage = stage;
		myStage.setScene(createScene());
		show();
		createCanvas();
		initializeTurtle();
		addPenButton();
		addOtherBoxes();
	
	}
	

	private ComboBox<ImageView> selectTurtle(){
		ComboBox<ImageView> turtleChoice=new ComboBox<ImageView>();
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"))));
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle2.gif"))));
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.valueProperty().addListener((x, y, newValue) -> {
			
		System.out.println(newValue.getImage());
			tvm.getImage().setImage(newValue.getImage());
			ComboBox<ImageView>ne=new ComboBox<ImageView>();
			ne.getItems().addAll(turtleChoice.getItems());
			turtleChoice.getItems().clear();
			System.out.println(turtleChoice.getItems());
			turtleChoice.getItems().addAll(ne.getItems());
		});
		return turtleChoice;
	}
	
	
	private void initializeTurtle(){
		tvm = new TurtleViewManager(new TurtleView(), gc);
		drawTurtle();
		wrapperPane.getChildren().add(tvm.getImage());
	}
	private void drawTurtle(){
		tvm.setX(wrapperPane.getBoundsInLocal().getWidth()/2);
		tvm.setY(wrapperPane.getBoundsInLocal().getHeight()/2);
	}
	
	private void createCanvas(){
		canvas = new Canvas(SCENE_WIDTH-commandScrollPane.getScrollPane().getWidth()-100,SCENE_HEIGHT-inputPanel.getHeight());
		gc = canvas.getGraphicsContext2D();
		wrapperPane.getChildren().add(canvas);	
	}

	private Scene createScene() {
        Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
	
	private BorderPane createRoot() {
        BorderPane bp = new BorderPane();
        
        
        
        createVariableScroller();
      
       bp.setLeft(variableScrollPane.getScrollPane());
        bp.setCenter(wrapperPane);
        bp.setBottom(initInputPanel());
        createCommandScroller();
        bp.setRight(commandScrollPane.getScrollPane());
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
	public String getText(){
		System.out.println(textArea.getText());
		return textArea.getText();
	}
	
	private void createCommandScroller(){
		commandScrollPane=new CommandScrollPane(textArea);
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
	private void addPenButton(){
		pb = new PenColorWheel(tvm);
        inputPanel.getChildren().add(createLabel("Pick Pen Color: "));
		inputPanel.getChildren().add(pb.getButton());
	}
	 private Node initInputPanel() {
		 	createButtons();
		 	BorderPane bottomPanel = new BorderPane();
	        inputPanel = new FlowPane();
	        textArea = new TextArea();
	        bottomPanel.setCenter(inputPanel);
	        bottomPanel.setLeft(textArea);
	        inputPanel.getChildren().addAll(otherButtons);
	        inputPanel.getChildren().add(createLabel("Pick Background Color: "));
	        inputPanel.getChildren().add(cb.getButton());
	        return bottomPanel;
	 }
	 private void addOtherBoxes(){
		 ComboBox<ImageView>turtleChoice=selectTurtle();
	       inputPanel.getChildren().add(turtleChoice);
	       inputPanel.getChildren().add(createLanguageBox());
	 }
	 public void handleRunButton(Trajectory T){
		commandScrollPane.addText();
		gc.clearRect(0, 0, wrapperPane.getWidth(), wrapperPane.getHeight());
		drawTurtle();
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
