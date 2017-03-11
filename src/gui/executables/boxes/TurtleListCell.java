package gui.executables.boxes;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * ListCell for TurtleComboBox's CellFactory property
 * @author Alex
 *
 */
public class TurtleListCell extends ListCell<String> {
	 protected void updateItem(String item, boolean empty){
	        super.updateItem(item, empty);
	        setGraphic(null);
	        setText(null);
	        if(item!=null){
	            ImageView imageView = new ImageView(new Image(item));
	            //Values chosen to match regular button height for asthetic purposes
	            imageView.setFitWidth(20);
	            imageView.setFitHeight(20);
	            setGraphic(imageView);
	            setText(Integer.toString(this.getIndex()));
	        }
	    }
	 }
