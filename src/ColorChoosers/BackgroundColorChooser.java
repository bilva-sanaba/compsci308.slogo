package ColorChoosers;

import GUI_Objects.ButtonMaker;
import javafx.scene.shape.Shape;

public abstract class BackgroundColorChooser extends ColorChooser{
	protected Shape background;
	protected ButtonMaker buttonMaker = new ButtonMaker();
	
	public BackgroundColorChooser(Shape rect){
		super();
		background=rect;
		displays.add(buttonMaker.createLabel("Pick Background Color: "));
	}
	
	protected void setColor(){
		 background.setFill(generateColor());
	 }
}
