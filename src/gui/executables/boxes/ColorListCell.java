package gui.executables.boxes;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * ListCell used for ComboBox's CellFactory
 * @author Alex
 *
 */
public class ColorListCell extends ListCell<String>{
	protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if(item!=null){
            Color color = Color.valueOf(item.split(":")[1]);
            Rectangle rect=new Rectangle();
            //Values chosen to match regular button height for asthetic purposes
            rect.setWidth(20);
            rect.setHeight(20);
            rect.setFill(color);
            setGraphic(rect);
            setText(item);
        }
    }
}
