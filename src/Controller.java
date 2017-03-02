import GUI.GUI;
import configuration.TurtleState;
import configuration.UnmodifiableTurtleState;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
	private GUI gui;
	public Controller(Stage stage){
//		SlogoModel model = new SlogoModel();
		Button r = new Button("Run");
		r.setOnAction(e -> onRun());
		gui = new GUI(stage, r);
	}
	private void onRun(){
		String text = gui.getText();
		gui.handleRunButton();
//		try {
//			GUI.handleRunButton(model.getTrajectory(text,gui.getCurrentLanguage()));
//		} catch (CommandException e){
//			alert (e.getmessage);
//		}
	}
	
}
