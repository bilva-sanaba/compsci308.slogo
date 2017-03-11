package GUI_Objects;

import java.util.Map;

import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleView;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.Button;
import model.UnmodifiableWorld;

public class ClickHandler {
	private TextAreaWriter myTextArea;
	private Button runButton;
	private Map<Integer, TurtleViewManager> existingTurtles;
	public ClickHandler(TextAreaWriter textAreaWriter,Button rb,Map<Integer, TurtleViewManager>activeTurtles){
		myTextArea = textAreaWriter;
		runButton = rb;
		existingTurtles=activeTurtles;
		for (int id : activeTurtles.keySet()){
			
			activeTurtles.get(id).getImage().setOnMouseClicked(e -> activate(id));	
		}
		
	}
	private void activate(int id){
		myTextArea.setText(getText(id)); 
		runButton.fire();
	}
	private String getText(int id){
		String returnString ="tell [ " + Integer.toString(id) + " ]" ;
		return returnString;
	}

	public void update(Map<Integer, TurtleViewManager> activeTurtles) {
		for (int id : activeTurtles.keySet()){
			activeTurtles.get(id).getImage().setOnMouseClicked(e -> activate(id));	
		}
	}
	
}
