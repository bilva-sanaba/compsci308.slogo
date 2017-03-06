package ColorChoosers;

import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;

public abstract class PenColorChooser extends ColorChooser {
		protected TurtleViewManager myTVM;
		public PenColorChooser(TurtleViewManager tvm){
			super();
			myTVM = tvm;
		}
		protected void setColor(){
			 myTVM.getTurtleView().setPenColor(generateColor());
		 }
	
}
