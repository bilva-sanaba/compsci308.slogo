package GUI;

import GUI_RetrievableCode.CommandScrollPane;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class RightPanel {
	private CommandScrollPane commandScrollPane;
	private Pane returnPanel = new Pane();
	private Button play;
	private double width;
	private double height;
	private Label label;
	private TextArea myTextArea;
	
	
	public RightPanel(TextArea ta, Button run, double w, double h){
		play=run;
		myTextArea=ta;
		width=w;
		height =h;
		
		createScrollPane();

		
		returnPanel.getChildren().add(commandScrollPane.getScrollPane());
		
	}
	
	public Pane getPanel(){
		return returnPanel;
	}
	public CommandScrollPane getScrollPane(){
		return commandScrollPane;
	}
	private void createLabel(){
		label=GUI.createLabel("test");
	}
	private void createScrollPane(){
		commandScrollPane=new CommandScrollPane(myTextArea,play);
		commandScrollPane.getScrollPane().setPrefSize(width/4,height*.73);
		commandScrollPane.getScrollPane().setLayoutX(0);
		commandScrollPane.getScrollPane().setLayoutY(0);
	}
}
