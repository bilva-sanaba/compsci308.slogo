package gui.panels;

import java.util.Map;

import gui.movement.TurtleViewManager;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageListCell extends ListCell<Integer> {
	private Map<Integer,TurtleViewManager> myTurtles;
	public ImageListCell(Map<Integer,TurtleViewManager> turtles){
		myTurtles=turtles;
		
	}
	
	protected void updateItem(Integer item, boolean empty){
	super.updateItem(item, empty);
    setGraphic(null);
    setText(null);
    if(item!=null&&!empty){
    	System.out.println(item+" "+myTurtles.get(this.getIndex()).getImage().getImage());
    	ImageView imageView = new ImageView(myTurtles.get(this.getIndex()).getImage().getImage());
    	//Values chosen to match regular button height for asthetic purposes
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
      
        setGraphic(imageView);
        setText(Integer.toString(this.getIndex()));
    }
    
}
}
