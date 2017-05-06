package model.configuration;

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
/**
 * SLogo Addition
 * @author jwei528
 *
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
	
	/**
	 * Returns true if turtles is active
	 * @return
	 */
	public boolean isActive();
	
	/**
	 * Returns turtle's integer ID number
	 * @return
	 */
	public int getID();

	public int getPenSize();
	
	public int getShape();
	
	public int getPenColor();

	public double right(double degrees);
	
	public double left(double degrees);
	
	
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
	 * Sets stamp
	 * @return 
	 */
	public int getStampCount();
	
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
	
	public void setPenSize(int index);
	
	public void setShape(int index);
	
	public void setPenColor(int index);
	
	/**
	 * Compares to another TurtleState
	 */
	public boolean equals(Object t);
	
	public TurtleState getModifiableCopy();

	public void addStamp();
	
	public void clearStamps();

}