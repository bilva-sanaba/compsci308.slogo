package gui.executables.turtleimage;
/**
 * Allows for choosing a numbers
 * @author Bilva
 *
 */
public class RotationalChooser implements IChooser {
	private Integer mySize;
	private Integer current;
	public RotationalChooser(Integer size){
		mySize = size;
		current =0;
	}
	/**
	 * Returns number one greater than last and resets to 0
	 * if equal to mySize
	 */
	@Override
	public int getNext() {
		current = (current+1)%mySize;
		return current;
	}

}
