package gui.executables.keymouse;

import java.util.Map;

import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleView;
import gui.movement.TurtleViewManager;
import model.UnmodifiableWorld;

/**
 * Class which makes all existing turtles clickable and when a turtle is clicked it becomes the active turtle
 * @author Bilva
 *
 */
public class ClickHandler extends ExecutableButton {
	private Map<Integer, TurtleViewManager> existingTurtles;
	/**
	 * Loops through all existing turtles and makes them clickable
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param activeTurtles object storing a list of all turtles that exist and their ids
	 */
	public ClickHandler (TextAreaWriter textAreaWriter,FireableButton rb,Language l,Map<Integer, TurtleViewManager>activeTurtles){
		super(textAreaWriter,rb,l);
		existingTurtles=activeTurtles;
		for (int id : activeTurtles.keySet()){
			activeTurtles.get(id).getImage().setOnMouseClicked(e -> activate(id));	
		}
	}
	/**
	 * Each time the list of existing Turtles is changed the turtles can be made clickable
	 * @param activeTurtles
	 */
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
