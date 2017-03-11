package GUI_Objects;

import GUI.TextAreaWriter;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PenSizeTextInput extends PenSizeChooser {

	public PenSizeTextInput(TextAreaWriter t, Button rb) {
		super(t,rb);
	}

	@Override
	protected void createPenSizeChooser() {
		penSizeButton = new TextField();
		((TextField) penSizeButton).setPromptText("Enter Pen Size");
		((TextField) penSizeButton).setOnAction(e -> {
	    	try{
	    		 myTextAreaWriter.setText(getText()); 
	    		 runButton.fire();
	    	}catch(IllegalArgumentException y){
	    		
	    	}
	    	catch(NullPointerException i){}
		});
	}
	private String getText(){
		return "setpensize "+((TextField) penSizeButton).getText();
	}
}
		
	    

