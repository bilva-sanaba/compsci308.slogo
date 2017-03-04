package ColorChoosers;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorChooser extends ColorChooser{
	protected Shape background;
	
	public BackgroundColorChooser(Shape rect){
		background=rect;
	}
	
	protected void setColor(){
		 background.setFill(generateColor());
	 }
}
