package ColorChoosers;

import GUI.TurtleViewManager;

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
