package GUI_PenColorButton;

import GUI.TurtleViewManager;
import javafx.scene.Node;
import javafx.scene.paint.Paint;

public abstract class PenColorButton {
		protected Node colorPicker;
		protected TurtleViewManager myTVM;
		
		public PenColorButton(TurtleViewManager tvm){
			myTVM = tvm;
			
		}
		protected abstract Paint getPenColor();
		
		protected void setColor(){
			 myTVM.getTurtleView().setPenColor(getPenColor());
		 }
		public Node getButton(){
			return colorPicker;
		}
	
}
