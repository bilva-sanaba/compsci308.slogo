package configuration;

/**
 * Holds state of turtle. This includes:
 * - X coordinate
 * - Y coordinate
 * - Heading (degrees) [0 points up, clockwise is positive]
 * - Pen Down
 * - Turtle Showing 
 * @author DhruvKPatel
 */
public class TurtleState implements UnmodifiableTurtleState{
	
	private double x, y, heading;
	private boolean penDown, showing;
	
	/**
	 * Constructs new turtle state with given parameters
	 * @param x
	 * @param y
	 * @param heading (degrees) [0 points up, clockwise is positive]
	 * @param penDown
	 * @param showing
	 */
	public TurtleState(double x, double y, double heading, boolean penDown, boolean showing){
		this.x = x;
		this.y = y;
		this.heading = heading;
		this.penDown = penDown;
		this.showing = showing;
	}
	
	/**
	 * Constructs new default turtle state (Starting position)
	 * x = 0, y = 0, heading = 0, pen is down, is showing
	 */
	public TurtleState(){
		this(0, 0, 0, true, true);
	}
	
	/**
	 * Turtle x coordinate
	 * @return
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Turtle y coordinate
	 * @return
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Turtle heading (degrees) [0 points up, clockwise is positive]
	 * @return
	 */
	public double getHeading(){
		return heading;
	}
	
	/**
	 * Returns true if pen is down (drawing activated)
	 * @return
	 */
	public boolean penDown(){
		return penDown;
	}
	
	/**
	 * Returns true if turtle is visible
	 * @return
	 */
	public boolean isShowing(){
		return showing;
	}
	
	/**
	 * Returns a modifiable copy of turtle state
	 */
	public TurtleState getModifiableCopy(){
		return new TurtleState(x, y, heading, penDown, showing);
	}
	
	// - - - - - - - - - - - - - - - - Methods below will be inaccessable when unmodifiable
	
	/**
	 * Sets X coordinate
	 */
	public double setX(double newX) {
		return x = newX;
	}
	
	/**
	 * Sets Y coordinate
	 */
	public double setY(double newY) {
		return y = newY;
	}
	
	/**
	 * Sets pen down
	 */
	public boolean setPenDown(boolean newPenDown){
		return penDown = newPenDown;
	}
	
	/**
	 * Sets visibility
	 */
	public boolean setShowing(boolean newShowing){
		return showing = newShowing;
	}

	/**
	 * Sets heading
	 */
	public double setHeading(double newHeading){
		return heading = newHeading;
	}
	
	/**
	 * Moves turtle coordinates forward given heading
	 * @return
	 */
	public double moveForward(double distance){
		setX((getX() + distance * Math.sin(Math.toRadians(getHeading()))));
		setY((getY() + distance * Math.cos(Math.toRadians(getHeading()))));
		return distance;
	}
	
	/**
	 * Compares to another TurtleState
	 */
	public boolean equals(Object t){
		if (t instanceof TurtleState){
			UnmodifiableTurtleState t1 = (UnmodifiableTurtleState) t;
			return(x == t1.getX() && y == t1.getY() && heading == t1.getHeading() && penDown == t1.isPenDown() && showing == t1.isShowing());
		}
		return false;
	}
	
	/**
	 * Useful for printing states
	 */
	public String toString(){
		return String.format("TurtleState:\n\tpos: (%f, %f)\n\theading: %f\n\tpen down:%b\n\tshowing: %b\n" , x, y, heading, penDown, showing);
	}

	@Override
	public boolean isPenDown() {
		return penDown;
	}
}