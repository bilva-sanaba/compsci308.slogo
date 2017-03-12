import controller.Controller;
import gui.GUI;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Main class for application which constructs a new controller
 * @author Bilva
 *
 */
public class Main extends Application {



	@Override
	public void start(Stage stage) throws Exception {
		Controller c = new Controller(stage);
	}

	public static void main(String[] args) {
		launch();
	}

}
