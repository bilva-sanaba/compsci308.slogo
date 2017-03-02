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
	public double getX();
	
	/**
	 * Turtle y coordinate
	 * @return
	 */
	public double getY();
	
	/**
	 * Turtle heading (degrees) [0 points up, clockwise is positive]
	 * @return
	 */
	public double getHeading();
	
	/**
	 * Returns true if pen is down (drawing activated)
	 * @return
	 */
	public boolean isPenDown();
	
	/**
	 * Returns true if turtle is visible
	 * @return
	 */
	public boolean isShowing();
	
	/**
	 * Returns modifiable copy of turtle state
	 */
	public TurtleState getModifiableCopy();
}
