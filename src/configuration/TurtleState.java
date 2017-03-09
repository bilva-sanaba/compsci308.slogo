package configuration;

/**
 * Holds state of turtle. This includes:
 * - X coordinate
 * - Y coordinate
 * - Heading (degrees) [0 points up, clockwise is positive]
 * - Pen Down
 * - Turtle Showing 
 * 
 * This class also can contain commands to run on the turtles.
 * @author DhruvKPatel
 */
public interface TurtleState { 

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
	 * Returns true if pen is down
	 */
	public boolean isPenDown();
	
	/**
	 * Returns true if turtle is visible
	 * @return
	 */
	public boolean isShowing();
	
	public boolean isActive();
	
	public int getID();
	
	// - - - - - - - - - - - - - - - - Methods below will be inaccessable when unmodifiable
	
	/**
	 * Sets xy position
	 * 
	 * returns distance traveled
	 */
	public double setPosition(double x, double y);
	
	/**
	 * Sets pen down
	 */
	public boolean setPenDown(boolean newPenDown);
	
	/**
	 * Sets visibility
	 */
	public boolean setShowing(boolean newShowing);

	/**
	 * Sets heading
	 */
	public double setHeading(double newHeading);
	
	/**
	 * Moves turtle coordinates forward given heading
	 * @return
	 */
	public double moveForward(double distance);
	
	/**
	 * Sets turtle heading toward certain point
	 * 
	 * Returns degrees moved
	 */
	public double setTowards(double x, double y);
	
	public void setActive(boolean active);
	
	/**
	 * Compares to another TurtleState
	 */
	public boolean equals(Object t);
	
	public TurtleState getModifiableCopy();

}