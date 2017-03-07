package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class GUI_Configuration {
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 800;
TabPane window=new TabPane();
public GUI_Configuration(GUI gui,Stage stage){
	addTab(gui,1);
	stage.setScene(createScene());
	stage.show();
}
public void addTab(GUI gui,int counter){
	Tab tab=gui.getTab();
	tab.setText(String.format("WorkSpace (%d)",counter));
	window.getTabs().add(tab);
}
private Scene createScene() {
	Scene scene = new Scene(window, SCENE_WIDTH, SCENE_HEIGHT);
	return scene;
}


}
