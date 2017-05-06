package gui.executables.turtleimage;

import java.util.ResourceBundle;
import gui.ButtonMaker;
import gui.executables.ExecutableButton;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import javafx.scene.Node;

public class ViewChangeButton extends ExecutableButton implements IDisplayNode{
	private Integer turtleID;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources/";
	public static final String BUTTONS="ButtonNames";
	private ResourceBundle myButtonResources;
	private IChooser myChooser;
	
	public ViewChangeButton(TextAreaWriter t, FireableButton rb, Language l, Integer id, Integer size) {
		super(t, rb, l);
		myButtonResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+BUTTONS);
		turtleID = id;
		myChooser = new RotationalChooser(size);
	}
	public Node getNode(){
		return new ButtonMaker().createButton(myButtonResources.getString("TurtleViewButton"), e->activate());
	}
	@Override
	protected String getText() {
		return createText();
	}
	
	private String createText(){
		String command=myResources.getString("Ask").split("\\|")[0];
		command+=(" [ ");
		command+= turtleID;
		command+=(" ] [ ");
		command+=(myResources.getString("SetShape").split("\\|")[0]);
		command += (" " + myChooser.getNext());
		command+=(" ]");
		return command;
	}

}
