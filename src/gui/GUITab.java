package gui;

import javafx.scene.control.Tab;
/**
 * Tab using GUI's root as own root
 * @author Alex
 *
 */
public class GUITab extends Tab{
	private GUI myGUI;
	public GUITab(GUI gui, int counter){
	myGUI=gui;
	this.setContent(myGUI.getTab().getContent());
	this.setText(String.format("WorkSpace (%d)",counter));
}
/**
 * 
 * @return GUI used to generate Tab
  */
	public GUI getGUI(){
	return myGUI;
}
}
