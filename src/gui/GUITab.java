package gui;

import javafx.scene.control.Tab;

public class GUITab extends Tab{
	private GUI myGUI;
public GUITab(GUI gui, int counter){
	myGUI=gui;
	this.setContent(myGUI.getTab().getContent());
	this.setText(String.format("WorkSpace (%d)",counter));
}
public GUI getGUI(){
	return myGUI;
}
}
