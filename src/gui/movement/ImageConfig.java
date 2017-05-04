package gui.movement;

import javafx.scene.image.Image;
/**
 * SLogo Adddition
 * @author jwei528
 *
 */
public class ImageConfig {

	private Image image;
	private double x;
	private double y;
	
	public ImageConfig(Image image, double x, double y){
		this.image=image;
		this.x=x;
		this.y=y;
	}
	
	public Image getImage(){
		return image;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}
