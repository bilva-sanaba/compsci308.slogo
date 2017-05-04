package controller;
import java.lang.reflect.InvocationTargetException;

import error.SlogoAlert;
import gui.GUI;
import gui.GUI_Configuration;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.SlogoModel;
import model.exceptions.CommandException;
/**
 * Controller class which creates a new GUI_Configuration (based on the GUI class)
 * A slogo model is also created as well as a run Button and new workspace button
 * These two buttons link the front end to the back end
 * In particular, when the run button is clicked the backend receives text from the front end
 * which is changed into readable data for the front end
 * @author Bilva
 *
 */
public class Controller {
	public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
	private GUI_Configuration myGUIBuilder;
	private int counter;
	private Alert alert;

	public Controller(Stage stage) throws CommandException{
		counter=1;
		GUI gui=initializeGUI();
		myGUIBuilder = new GUI_Configuration(gui,stage);	
	}

	private GUI initializeGUI() throws CommandException{
		SlogoModel model = new SlogoModel();
		Button r = new Button("Run");
		Button newTab=new Button("New Workspace");
		GUI gui=new GUI(r,newTab);
		r.setOnAction(e -> {
			onRun(gui, model);
		});
		newTab.setOnAction(e-> onTab(myGUIBuilder));
		return gui;
	}

	private void onRun(GUI gui,SlogoModel model) {
		String text = gui.getText();
		model.setLanguage(gui.getCurrentLanguage());
		try {
			gui.handleRunButton(model.getWorld(text));
		} catch (Exception e){
			SlogoAlert alert=new SlogoAlert("Learn to Code: https://www.codeschool.com/",e.getMessage());
			alert.showAlert();
		}

	}
	private void onTab(GUI_Configuration g) {
		try{
			counter++;
			GUI nextGUI=initializeGUI();
			g.addTab(nextGUI,counter);
		}
		catch(CommandException e1){
			SlogoAlert alert=new SlogoAlert("Unable to make new Workspace",e1.getMessage());
			alert.showAlert();

		}
	}

}
