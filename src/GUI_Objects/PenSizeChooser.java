package GUI_Objects;

import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public abstract class PenSizeChooser {
	private static final double DEFAULT_PEN_SIZE = 4;
	protected double penSize = DEFAULT_PEN_SIZE;
	protected Node penSizeButton;
	protected TurtleViewManager myTVM;
	PenSizeChooser(TurtleViewManager tvm){
		myTVM = tvm;
		createPenSizeChooser();
	}
	public Node getPenButtons(){
		return penSizeButton;
	}
	protected abstract void createPenSizeChooser();

}
