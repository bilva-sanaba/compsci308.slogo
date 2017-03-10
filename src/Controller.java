import GUI.GUI;
import GUI.GUI_Configuration;
import error.SlogoAlert;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.SlogoModel;
import model.exceptions.CommandException;

public class Controller {
	private GUI_Configuration myGUIBuilder;
	int counter;
	//private SlogoModel model;
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
		GUI gui=new GUI(r,newTab, model);
		r.setOnAction(e -> onRun(gui, model));
		
		newTab.setOnAction(e-> onTab(myGUIBuilder));
		return gui;
		
	}
	private void onRun(GUI gui,SlogoModel model){
		String text = gui.getText();
		model.setLanguage(gui.getCurrentLanguage());
		System.out.println(gui.getCurrentLanguage());
		try {
			gui.handleRunButton(model.getWorld(text));
		} catch (CommandException|NumberFormatException e){
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
