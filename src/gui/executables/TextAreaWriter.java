package gui.executables;

import javafx.scene.control.TextArea;
/**
 * Encapsulated TextArea, giving access to only entered text and width properties 
 * @author Alex
 *
 */
public class TextAreaWriter {
private TextArea myTextArea;
public TextAreaWriter(TextArea textArea){
myTextArea=textArea;
}
/**
 * 
 * @return Text entered in TextArea
 */
public String getText(){
	return myTextArea.getText();
}
/**
 * 
 * @param s String to be placed into TextArea
 */
public void setText(String s){
	myTextArea.setText(s);
}
/**
 * 
 * @param d Double to update TextArea's MaxWidth property
 */
public void setMaxWidth(double d){
	myTextArea.setMaxWidth(d);
}
/**
 * 
 * @param d Double to update TextArea's MaxWidth property
 */
public void setMinWidth(double d){
	myTextArea.setMinWidth(d);
}
/**
 * Clears TextArea
 */
public void clear(){
	myTextArea.clear();
}
}
