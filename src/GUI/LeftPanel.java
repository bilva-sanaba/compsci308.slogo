package GUI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import GUI_RetrievableCode.UserCommandScrollPane;
import GUI_RetrievableCode.VariableScrollPane;
import error.SlogoAlert;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.Model;
import model.UnmodifiableWorld;
import model.exceptions.CommandException;
import model.tokens.Constant;
import model.tokens.Variable;
import model.tokens.VariableContainer;

public class LeftPanel {
	private VariableScrollPane variableScrollPane;
	private UserCommandScrollPane commandScrollPane;
	private UnmodifiableWorld myWorld;
	private Pane returnPanel = new Pane();
	public LeftPanel(double width, double height,UnmodifiableWorld world){
		myWorld=world;
		try {
			createVariableScroller(width,height);
		} catch ( CommandException e) {
		
			//do nothing, this wil happen when the world variable is null;
		}
		returnPanel.getChildren().add(variableScrollPane.getScrollPane());
		try{
			createCommandScroller(width,height);
		}
		catch(CommandException e){
			
		}
		returnPanel.getChildren().add(commandScrollPane.getScrollPane());
		returnPanel.getStyleClass().add("pane");
	}
	public Pane getPanel(){
		return returnPanel;
	}
	public void updateCommands(UnmodifiableWorld world) throws CommandException{
		myWorld=world;
		Collection<String>commands=world.getCommandNames();
		commandScrollPane.update(commands);
	}
	private void createCommandScroller(double width,double height) throws CommandException{
		
		
		commandScrollPane=new UserCommandScrollPane();
		commandScrollPane.getScrollPane().setPrefSize(width/8,height*.35);
		commandScrollPane.getScrollPane().setLayoutX(0);
		commandScrollPane.getScrollPane().setLayoutY(height*.35);
		if(myWorld!=null){
			Collection<String> commands=myWorld.getCommandNames();
			
		commandScrollPane.update(commands);
		}
	}
		
	public void updateVariables(UnmodifiableWorld world) throws CommandException{
		
		myWorld=world;
		HashMap<String,Double>map=new HashMap<String,Double>();
		for(String variable:myWorld.getVariables().getVariableNames()){
			map.put(variable, myWorld.getVariables().get(new Variable(variable)).getVal());
	}
	variableScrollPane.update(map);
	}
	private void createVariableScroller(double width, double height) throws CommandException{	
		HashMap<String,Double>map=new HashMap<String,Double>();
		if(myWorld!=null){
		for(String variable:myWorld.getVariables().getVariableNames()){
			map.put(variable, myWorld.getVariables().get(new Variable(variable)).getVal());
		}
		}
		variableScrollPane=new VariableScrollPane();
		variableScrollPane.getScrollPane().setPrefSize(width/8,height*.35);
		variableScrollPane.getScrollPane().setLayoutX(0);
		variableScrollPane.getScrollPane().setLayoutY(0);
		variableScrollPane.update(map);
	}
}
