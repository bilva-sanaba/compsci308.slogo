package gui.executables.turtleimage;

public class RotationalChooser implements IChooser {
	private Integer mySize;
	private Integer current;
	public RotationalChooser(Integer size){
		mySize = size;
		current =0;
	}
	@Override
	public int getNext() {
		current = (current+1)%mySize;
		return current;
	}

}
