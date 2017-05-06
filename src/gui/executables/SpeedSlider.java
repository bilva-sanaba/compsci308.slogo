package gui.executables;

import java.util.ArrayList;
import java.util.List;

import gui.ButtonMaker;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
/**
 * A GUI object which does not follow the ExecutableButton pattern but instead changes parameters which only the
 * front end needs access to
 * @author Bilva
 *
 */
public class SpeedSlider {
	private static final int DEFAULT_SPEED = 1000;
	private int speed=DEFAULT_SPEED;
	private ButtonMaker buttonMaker = new ButtonMaker();
	private Slider slider;
	private Label speedLabel;
	private List<Node> extraButtons = new ArrayList<Node>();
	public SpeedSlider(){
		createSpeedSlider();
		createSpeedChooser();	
	}
	/**
	 * Needed so TVM can get the speed it should be at. 
	 * @return speed that an animation should last
	 */
	public int getSpeed(){
		return speed;
	}
	private void createSpeedSlider() {
		speedLabel = buttonMaker.createLabel("Animation Speed : " + Integer.toString(DEFAULT_SPEED) + " milliseconds");
		slider = new Slider(DEFAULT_SPEED/30, 2*DEFAULT_SPEED, DEFAULT_SPEED);
		slider.setMajorTickUnit(DEFAULT_SPEED/3);
		slider.setShowTickMarks(true);
		slider.setSnapToTicks(false);
		extraButtons.add(speedLabel);
		extraButtons.add(slider);
	}
	private void createSpeedChooser() {
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			slider.setValue(newValue.intValue());
			speedLabel.setText(String.format("Animation Speed : " + Integer.toString(newValue.intValue()) + " milliseconds"));
			speed = newValue.intValue();
		});
	}
	/**
	 * Used so that the GUI can display these elements
	 * @return List of all nodes (Label and slider) that will be displayed in the GUI
	 */
	public List<Node> getButtons(){
		return extraButtons;
	}
}
