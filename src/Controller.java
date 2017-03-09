import GUI.GUI;
import GUI.GUI_Configuration;
import configuration.TurtleState;
import configuration.UnmodifiableTurtleComposite;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.SlogoModel;
import model.commands.CommandException;

public class Controller {
	private GUI_Configuration myGUIBuilder;
	int counter;
	//private SlogoModel model;
	private Alert alert;
	public Controller(Stage stage) throws CommandException{
		counter=1;
		GUI gui=initializeGUI();
		myGUIBuilder = new GUI_Configuration(gui,stage);
		
		
		 
		
		//Button w=new Button("New WorkSpace");
		//w.setOnAction(e->onNew());
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
		try {
			gui.handleRunButton(model.getTrajectory(text));
		} catch (CommandException|NumberFormatException e){
			alert= new Alert(AlertType.ERROR);
			alert.setTitle("Learn to Code: https://www.codeschool.com/");
			alert.setContentText(e.getMessage()); 
			alert.showAndWait();
		}
	
	}
	private void onTab(GUI_Configuration g) {
		try{
			counter++;
			GUI nextGUI=initializeGUI();
		g.addTab(nextGUI,counter);
		
		}
		catch(CommandException e1){
			alert= new Alert(AlertType.ERROR);
			alert.setTitle("Unable to make new Workspace");
			alert.setContentText(e1.getMessage()); 
			alert.showAndWait();
		
		}
	}

}
