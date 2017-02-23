package configuration;

/**
 * Using this interface, you can restrict ability to change turtle states.
 * @author DhruvKPatel
 *
 */
public interface UnmodifiableTurtleState {
	
	/**
	 * Turtle x coordinate
	 * @return
	 */
	public int getX();
	
	/**
	 * Turtle y coordinate
	 * @return
	 */
	public int getY();
	
	/**
	 * Turtle heading (degrees) [0 points up, clockwise is positive]
	 * @return
	 */
	public double getHeading();
	
	/**
	 * Returns true if pen is down (drawing activated)
	 * @return
	 */
	public boolean penDown();
	
	/**
	 * Returns true if turtle is visible
	 * @return
	 */
	public boolean isShowing();
}
