
import java.util.Arrays;
import java.util.List;

import GUI.TurtleView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GUI {
	private BorderPane myRoot = new BorderPane();
	private Shape background = new Rectangle(500,500,Color.WHITE);
	private TurtleView t = new TurtleView();
	private List<Button> otherButtons;
	private Stage myStage;
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 680;
	public GUI(Stage stage){
		myRoot=createRoot();
		myStage = stage;
		myStage.setScene(createScene());
		myStage.show();
	}
	private void setTurtle(){
		myRoot.setCenter(t.getImage());
	}

	
	private Scene createScene() {
        Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
	private BorderPane createRoot() {
        BorderPane bp = new BorderPane();
        bp.setBottom(initInputPanel());
        bp.setCenter(background);
        bp.setRight(new Rectangle(200,500,Color.BLACK));
        bp.setLeft(new Rectangle(200,500,Color.BLACK));
        return bp;
    }
	 private Node initInputPanel() {
	        createButtons();
	        HBox inputPanel = new HBox();
	        TextArea t = new TextArea("Enter code here");
	        inputPanel.getChildren().add(t);
	        inputPanel.getChildren().addAll(otherButtons);
	        
	        
	        return inputPanel;
	 }
	 private void createButtons(){
		    Button play = createButton("Run", e -> setTurtle());
	        Button pause = createButton("Clear", e -> background.setFill(Color.PURPLE));
	        Button language = createButton("Choose Language", e -> background.setFill(Color.PURPLE));
	        Button help = createButton("Help", e -> background.setFill(Color.PURPLE));
	        
	        otherButtons = Arrays.asList(play, pause,language,help);
	 }
	 private Button createButton(String label, EventHandler<ActionEvent> e) {
	        Button b = new Button();
	        b.setText(label);
	        b.setOnAction(e);
	        return b;
	    }
	public void show(){
		myStage.show();
	}
}
