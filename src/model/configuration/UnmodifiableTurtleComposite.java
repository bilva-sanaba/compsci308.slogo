package model.configuration;

import java.util.Iterator;
import java.util.Map;

/**
 * Using this interface, you can restrict ability to change composite turtle states.

 * @author DhruvKPatel
 *
 */
public interface UnmodifiableTurtleComposite extends Iterable<SingleTurtleState> {
	
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
	 * Returns turtle ID
	 * @return
	 */
	public int getID();
	
	public int getPenSize();
	
	public int getShape();
	
	public int getPenColor();
	
	/*
	 * Composite stuff
	 */
	
	/**
	 * Returns viewable map of single states
	 */
	public Map<Integer, SingleTurtleState> getAllStates();

	/**
	 * Returns copy of composite state
	 */
	public CompositeTurtleState getCompositeCopy();
	
	/**
	 * Returns generic copy of turtle state
	 * @return
	 */
	public TurtleState getModifiableCopy();
	
	/**
	 * Returns unmodifiable iterator
	 */
	public Iterator<SingleTurtleState> iterator();

}
