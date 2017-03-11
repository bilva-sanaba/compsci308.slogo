package error;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * General alert message 
 * @author Alex
 *
 */
public class SlogoAlert {
	private Alert alert;
public SlogoAlert(String title,String content){
	alert=new Alert(AlertType.ERROR);
	alert.setTitle(title);
	alert.setContentText(content);
	
}
/**
 * displays alert
 */
public void showAlert(){
	alert.showAndWait();
}
}
