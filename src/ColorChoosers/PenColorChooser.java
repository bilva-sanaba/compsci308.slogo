package ColorChoosers;

import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.paint.Color;
import xml.Default;

public abstract class PenColorChooser extends ColorChooser {
		protected TurtleViewManager myTVM;
		public PenColorChooser(TurtleViewManager tvm,Default d){
			super();
			myTVM = tvm;
			 myTVM.getTurtleView().setPenColor(Color.valueOf(d.getPenColor()));
		}
		protected void setColor(){
			 myTVM.getTurtleView().setPenColor(generateColor());
		 }
	
}
