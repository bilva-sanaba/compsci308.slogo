package GUI_Objects;

import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.TextField;

public class PenSizeTextInput extends PenSizeChooser {

	public PenSizeTextInput(TurtleViewManager tvm) {
		super(tvm);
	}

	@Override
	protected void createPenSizeChooser() {
		penSizeButton = new TextField();
		((TextField) penSizeButton).setPromptText("Enter Pen Size");
		((TextField) penSizeButton).setOnAction(e -> {
	    	try{
	    		myTVM.setPenSize(Double.parseDouble(((TextField) penSizeButton).getText()));
	    		((TextField) penSizeButton).setText("");
	    	}catch(IllegalArgumentException y){
	    		
	    	}
	    	catch(NullPointerException i){}
		});
	}
}
		
	    

