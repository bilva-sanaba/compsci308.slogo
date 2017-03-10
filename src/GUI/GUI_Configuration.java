package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Model;

public class GUI_Configuration {
	public static final int SCENE_WIDTH = 1200; 
	public static final int SCENE_HEIGHT = 800;
	public static final String DEFAULT_RESOURCE_PACKAGE="resources/";
	public static final String STYLESHEET="default.css";
TabPane window=new TabPane();
	private Scene myScene;
public GUI_Configuration(GUI gui,Stage stage){
	addTab(gui,1);
	createScene();
	myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE+STYLESHEET);
	stage.setScene(myScene);
	stage.show();
	
}
public void addTab(GUI gui,int counter){
	GUITab tab=new GUITab(gui,counter);
	window.getTabs().add(tab);
}
private void createScene() {
	Scene scene = new Scene(window, SCENE_WIDTH, SCENE_HEIGHT);
	myScene= scene;
	myScene.setOnKeyPressed(e->handleKeyInput(e.getCode()));
}
private void handleKeyInput(KeyCode code){
	GUITab currentTab=(GUITab)window.getSelectionModel().getSelectedItem();
	GUI currentGUI=currentTab.getGUI();
	currentGUI.handleKeyInput(code);
}

}
