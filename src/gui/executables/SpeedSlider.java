package gui.executables;

import java.util.ArrayList;
import java.util.List;

import gui.ButtonMaker;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

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
	 * @return
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
	public List<Node> getButtons(){
		return extraButtons;
	}
}
