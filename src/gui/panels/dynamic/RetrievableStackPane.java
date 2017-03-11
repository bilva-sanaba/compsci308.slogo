package gui.panels.dynamic;

import javafx.scene.layout.StackPane;

public class RetrievableStackPane {
private StackPane myStackPane;
private String myString;
public RetrievableStackPane(String s){
	myString=s;
	myStackPane=new StackPane();
}
/**
 * For panel display
 * @return
 */
public StackPane getStackPane(){
	return myStackPane;
}
public String getString(){
	return myString;
}
}
