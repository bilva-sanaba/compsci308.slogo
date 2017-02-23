
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    

    @Override
    public void start(Stage stage) throws Exception {
        Controller myController = new Controller(stage);
       
    }
    
    public static void main(String[] args) {
        launch();
    }

}
