package gui.panels;

import gui.ButtonMaker;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.panels.dynamic.CommandScrollPane;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * Class which creates Panel to be displayed on right side of display
 * @author Bilva
 *
 */
public class RightPanel {
	private CommandScrollPane commandScrollPane;
	private Pane returnPanel = new Pane();
	private FireableButton play;
	private double width;
	private double height;
	private TextAreaWriter myTextArea;

	public RightPanel(TextAreaWriter ta, FireableButton run, double w, double h){
		play=run;
		myTextArea=ta;
		width=w;
		height =h;
		createScrollPane();
		returnPanel.getChildren().add(commandScrollPane.getScrollPane());
		returnPanel.getStyleClass().add("pane");
	}
	/**
	 * USed by GUI to  display right panel
	 * @return
	 */
	public Pane getPanel(){
		return returnPanel;
	}
	/**
	 * Needed to execute commands in scrollpane
	 * @return
	 */
	public CommandScrollPane getScrollPane(){
		return commandScrollPane;
	}

	private void createScrollPane(){
		commandScrollPane=new CommandScrollPane(myTextArea,play);
		commandScrollPane.getScrollPane().setPrefSize(width/4,height*.71);
		commandScrollPane.getScrollPane().setLayoutX(0);
		commandScrollPane.getScrollPane().setLayoutY(0);
	}
}
