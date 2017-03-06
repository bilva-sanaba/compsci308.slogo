package GUI;

import java.util.HashMap;

import GUI_RetrievableCode.VariableScrollPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class LeftPanel {
	private VariableScrollPane variableScrollPane;
	private Pane returnPanel = new Pane();
	public LeftPanel(double width, double height){
		createVariableScroller(width,height);
		returnPanel.getChildren().add(variableScrollPane.getScrollPane());
	}
	public Pane getPanel(){
		return returnPanel;
	}
	private void createVariableScroller(double width, double height){
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
		variableScrollPane.getScrollPane().setPrefSize(width/8,height*.73);
		variableScrollPane.getScrollPane().setLayoutX(0);
		variableScrollPane.getScrollPane().setLayoutY(0);
		variableScrollPane.add(map);
	}
}
