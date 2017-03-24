package gui.executables.boxes;
///This code is part of my MasterPiece
//This is the factory that generates the display for the ComboBox
//A rectangle of the proper color is set as the graphic for the ListCell
//The text is set as the palette index and a string representation of the color, seperated by a colon
//This ListCell is an implementation of the factory design pattern
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
