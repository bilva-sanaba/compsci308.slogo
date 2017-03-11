package GUI_Objects;

import java.util.Map;

import GUI.ExecutableButton;
import GUI.Language;
import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.Button;
import model.UnmodifiableWorld;

public class ClickHandler extends ExecutableButton {
	private Map<Integer, TurtleViewManager> existingTurtles;
	public ClickHandler (TextAreaWriter textAreaWriter,Button rb,Language l,Map<Integer, TurtleViewManager>activeTurtles){
		super(textAreaWriter,rb,l);
		existingTurtles=activeTurtles;
		for (int id : activeTurtles.keySet()){
			activeTurtles.get(id).getImage().setOnMouseClicked(e -> activate(id));	
		}
	}
	public void update(Map<Integer, TurtleViewManager> activeTurtles) {
		for (int id : activeTurtles.keySet()){
			activeTurtles.get(id).getImage().setOnMouseClicked(e -> activate(id));	
		}
	}
	
	private void activate(int id){
		myTextAreaWriter.setText(getText(id)); 
		runButton.fire();
	}
	
	private String getText(int id){
		String command=myResources.getString("Tell").split("\\|")[0];
		command+=" [ " + Integer.toString(id) + " ]" ;
		return command;
	}
	@Override
	protected String getText() {
		return null;
	}
}
