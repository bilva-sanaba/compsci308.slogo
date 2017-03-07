package GUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import GUI_RetrievableCode.VariableScrollPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.Constant;
import model.Model;
import model.Variable;
import model.VariableContainer;
import model.commands.CommandException;

public class LeftPanel {
	private VariableScrollPane variableScrollPane;
	private Pane returnPanel = new Pane();
	public LeftPanel(double width, double height, Model model){
		try {
			createVariableScroller(width,height,model);
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			Alert a=new Alert(AlertType.ERROR);
			a.showAndWait();
		}
		returnPanel.getChildren().add(variableScrollPane.getScrollPane());
	}
	public Pane getPanel(){
		return returnPanel;
	}
	
	private void createVariableScroller(double width, double height,Model model) throws CommandException{	
		HashMap<String,Double>map=new HashMap<String,Double>();
		VariableContainer variables=model.getGlobalVariables();
		Set<String> variableNames=variables.getVariableNames();
		for(String s:variableNames){
			map.put(s, variables.get(new Variable(s)).getVal());
		}
		variableScrollPane=new VariableScrollPane();
		variableScrollPane.getScrollPane().setPrefSize(width/8,height*.73);
		variableScrollPane.getScrollPane().setLayoutX(0);
		variableScrollPane.getScrollPane().setLayoutY(0);
		variableScrollPane.add(map);
	}
}
