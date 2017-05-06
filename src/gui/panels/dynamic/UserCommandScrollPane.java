package gui.panels.dynamic;


import java.util.Collection;
import java.util.Set;

import javafx.scene.Group;

import javafx.scene.control.ScrollPane;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import javafx.scene.text.Text;


public class UserCommandScrollPane {
	private Group root;
private ScrollPane myScrollPane;
private int startingHeight;
private Collection<String> myCommands;

public UserCommandScrollPane() {
	root=new Group();
	myScrollPane=new ScrollPane();
	myScrollPane.setContent(root);
	startingHeight=0;
}
public void update(Collection<String>commands){
	root=new Group();
	myScrollPane.setContent(root);
	myCommands=commands;
for(String s:commands){
		
	StackPane entry=new StackPane();
	FlowPane textPane=new FlowPane();	
	
		RetrievableStackPane stackpane=new RetrievableStackPane(s);
		
		
		 
		 textPane.getChildren().add(stackpane.getStackPane());
		
		 entry.setMaxWidth(myScrollPane.getPrefWidth()-1);
		 entry.getChildren().add(textPane);
		 entry.setLayoutY(startingHeight);
		 startingHeight+=entry.getBoundsInLocal().getHeight()+12;
		 root.getChildren().add(entry);
	}

}
/**
 * For panel display
 * @return
 */
public ScrollPane getScrollPane(){
	return myScrollPane;
}
/**
 * For GUI to clear screen
 */
public void clearScrollPane(){
	root.getChildren().clear();
}


}
