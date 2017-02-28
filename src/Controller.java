import GUI.GUI;
import javafx.stage.Stage;

public class Controller {
	private GUI gui;
	public Controller(Stage stage){
		SlogoModel backend = new SlogoModel();
		RunButton r = new RunButton(e -> onRun());
		gui = new GUI(stage, r);
		gui.setTurtle(new Trajectory());
	}
	private void onRun(){
		String text = GUI.getText();
		try {
			GUI.setTurtle(backend.getTrajectory(text,GUI.getCurrentLanguage()));
		} catch (CommandException e){
			alert (e.getmessage);
		}
	}
	
}
