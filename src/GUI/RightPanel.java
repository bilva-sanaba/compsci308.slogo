package GUI;

import GUI_RetrievableCode.CommandScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;


public class RightPanel {
	private CommandScrollPane commandScrollPane;
	private Pane returnPanel;
	private Button play;
	private double width;
	private double height;
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
	private void createScrollPane(){
		commandScrollPane=new CommandScrollPane(myTextArea,play);
		commandScrollPane.getScrollPane().setPrefSize(width/4,height);
		commandScrollPane.getScrollPane().setLayoutX(width*3/4);
		commandScrollPane.getScrollPane().setLayoutY(0);
	}
}
