import GUI.GUI;
import configuration.TurtleState;
import configuration.UnmodifiableTurtleState;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.SlogoModel;
import model.commands.CommandException;

public class Controller {
	private GUI gui;
	private SlogoModel model;
	private Alert alert;
	public Controller(Stage stage) throws CommandException{
		model = new SlogoModel();
		Button r = new Button("Run");
		r.setOnAction(e -> onRun());
		gui = new GUI(stage, r);
	}
	private void onRun(){
		String text = gui.getText();
		model.setLanguage(gui.getCurrentLanguage());
		try {
			gui.handleRunButton(model.getTrajectory(text));
		} catch (CommandException e){
			alert= new Alert(AlertType.ERROR);
			alert.setTitle("Learn to Code: https://www.codeschool.com/");
			alert.setContentText(e.getMessage()); 
			alert.showAndWait();
		}
	}
	
}
