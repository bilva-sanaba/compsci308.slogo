package model.configuration;

/**
 * This conceptually represents a "Turtle"
 * 
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
public class SingleTurtleState implements TurtleState{
	private int id;
	private double x, y, heading;
	private boolean penDown, showing;
	private boolean isActive;
	private int penSize;
	private int shape;
	private int penColor;
	private int stampCount;
	
	/**
	 * Constructs new turtle state with given parameters
	 * @param x
	 * @param y
	 * @param heading (degrees) [0 points up, clockwise is positive]
	 * @param penDown
	 * @param showing
	 */
	public SingleTurtleState(double x, double y, double heading, boolean penDown, boolean showing, int id, boolean isActive, int penSize, int shape, int penColor, int stampCount){
		this.x = x;
		this.y = y;
		this.heading = heading;
		this.penDown = penDown;
		this.showing = showing;
		this.id = id;
		this.isActive = isActive;
		this.penSize = penSize;
		this.shape = shape;
		this.penColor = penColor;
		this.stampCount = stampCount;
	}
	
	/**
	 * Constructs new default turtle state (Starting position)
	 * x = 0, y = 0, heading = 0, pen is down, is showing
	 */
	public SingleTurtleState(int id){
		this(0, 0, 0, true, true, id, true, 0, 0, 0, 0);
	}
	
	/**
	 * Copies state
	 */
	public SingleTurtleState(SingleTurtleState s){
		this(s.x, s.y, s.heading, s.penDown, s.showing, s.id, s.isActive, s.penSize, s.shape, s.penColor, s.stampCount);
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
	 * Returns true if pen is down
	 */
	@Override
	public boolean isPenDown() {
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
		return new SingleTurtleState(this);
	}
	
	// - - - - - - - - - - - - - - - - Methods below will be inaccessable when unmodifiable
	
	/**
	 * Sets turtle heading toward certain point
	 * 
	 * Returns new heading
	 */
	public double setPosition(double x, double y){
		this.x = x;
		this.y = y;
		double dx = x - getX();
		double dy = y - getY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
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
	 * returns distance moved
	 * @return
	 */
	public double moveForward(double distance){
		double newX = getX() + distance * Math.sin(Math.toRadians(getHeading()));
		double newY = getY() + distance * Math.cos(Math.toRadians(getHeading()));
		setPosition(newX, newY);
		return distance;
	}

	
	/**
	 * Sets turtle heading toward certain point
	 * 
	 * Returns new heading
	 */
	public double setTowards(double x, double y){
		double dx = x - getX();
		double dy = y - getY();
		return setHeading(90 - Math.toDegrees(Math.atan2(dy, dx)));
	}
	
	
	/**
	 * Compares to another TurtleState
	 */
	public boolean equals(Object t){
		if (t instanceof TurtleState){
			TurtleState t1 = (TurtleState)t;
			return(x == t1.getX() && y == t1.getY() && heading == t1.getHeading() && penDown == t1.isPenDown() && showing == t1.isShowing() && isActive == t1.isActive());
		}
		return false;
	}
	
	/**
	 * Useful for printing states
	 */
	public String toString(){
		return String.format("\tpos: (%f, %f)\n\theading: %f\n\tpen down:%b\n\tshowing: %b\n\tactive: %b\n\tpen color: %d\n" , x, y, heading, penDown, showing, isActive, penColor);
	}
	
	/*
	 * Methods below are unique to SingleTurtleState
	 */
	
	/**
	 * Sets turtle's ID
	 */
	public void setID(int id){
		this.id = id;
	}

	/**
	 * Returns turtle's ID
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Sets turtle's active state
	 */
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
	
	/**
	 * Gets turtle's active state
	 */
	public boolean isActive(){
		return isActive;
	}

	@Override
	public int getPenSize() {
		return penSize;
	}

	@Override
	public int getShape() {
		return shape;
	}

	@Override
	public int getPenColor() {
		return penColor;
	}

	@Override
	public void setPenSize(int index) {
		penSize = index;
	}

	@Override
	public void setShape(int index) {
		shape = index;
	}

	@Override
	public void setPenColor(int index) {
		penColor = index;
	}

	@Override
	public double right(double degrees) {
		return this.setHeading(this.getHeading() + degrees);
	}

	@Override
	public double left(double degrees) {
		return this.setHeading(this.getHeading() - degrees);
	}

	@Override
	public int getStampCount() {
		return stampCount;
		
	}

	@Override
	public void addStamp() {
		stampCount++;
		
	}

	@Override
	public void clearStamps() {
		stampCount=0;
		
	}
}