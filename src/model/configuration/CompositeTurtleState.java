package model.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class follows the Composite Design pattern, for implementing multiple turtles.
 * 
 * There is a lot of repeated code in this section that I could simplify using 
 * lamdas, which I will do if I have time later.
 * 
 * @author DhruvKPatel
 *
 */
/**
 * SLogo Addition
 * @author jwei528
 *
 */
public class CompositeTurtleState implements TurtleState, UnmodifiableTurtleComposite, Iterable<SingleTurtleState> {
	private Map<Integer, SingleTurtleState> singleTurtles;

	/**
	 * Constructor for Composite of TurtleStates.
	 */
	public CompositeTurtleState(SingleTurtleState initialTurtle){
		singleTurtles = new HashMap<>();
		singleTurtles.put(initialTurtle.getID(), initialTurtle);
	}
	
	/**
	 * Copy constructor
	 */
	public CompositeTurtleState(CompositeTurtleState m){
		singleTurtles = new HashMap<>();
		for(SingleTurtleState t : m.singleTurtles.values()){
			singleTurtles.put(t.getID(), new SingleTurtleState(t));
		}
	}
	
	
	/*
	 * The following methods are selectively visible to TurtleState instances:
	 */
	
	/**
	 * Returns x coordinate of head turtle
	 */
	@Override
	public double getX() {
		return getHeadActiveTurtle().getX();
	}

	/**
	 * Returns y coordinate of head turtle
	 */
	@Override
	public double getY() {
		return getHeadActiveTurtle().getY();
	}

	/**
	 * Returns heading of head turtle 
	 */
	@Override
	public double getHeading() {
		return getHeadActiveTurtle().getHeading();
	}

	/**
	 * Returns pendown of head turtle
	 */
	@Override
	public boolean isPenDown() {
		return getHeadActiveTurtle().isPenDown();
	}

	/**
	 *  Returns showing of head turtle
	 */
	@Override
	public boolean isShowing() {
		return getHeadActiveTurtle().isShowing();
	}

	/**
	 * Returns ID of head turtle
	 */
	public int getID(){
		return getHeadActiveTurtle().getID();
	}
	

	@Override
	public int getPenSize() {
		return getHeadActiveTurtle().getPenSize();
	}

	@Override
	public int getShape() {
		return getHeadActiveTurtle().getShape();
	}

	@Override
	public int getPenColor() {
		return getHeadActiveTurtle().getPenColor();
	}
	
	/** 
	 * Returns modifiable copy of state
	 */
	@Override
	public CompositeTurtleState getModifiableCopy() {
		return this.getCompositeCopy();
	}

	
	@Override
	public double setPosition(double x, double y) {
		ArrayList<Double> distancesTraveled = new ArrayList<>();
		for(TurtleState t : getActiveTurtleStates()){
			distancesTraveled.add(t.setPosition(x, y));
		}
		return distancesTraveled.get(headTurtleIndex());
	}

	@Override
	public boolean setPenDown(boolean newPenDown) {
		for(TurtleState t : getActiveTurtleStates()){
			t.setPenDown(newPenDown);
		}
		return true;
	}

	@Override
	public boolean setShowing(boolean newShowing) {
		for(TurtleState t : getActiveTurtleStates()){
			t.setShowing(newShowing);
		}
		return true;
	}

	@Override
	public double setHeading(double newHeading) {
		ArrayList<Double> degreesMoved = new ArrayList<>();
		for(TurtleState t : getActiveTurtleStates()){
			degreesMoved.add(t.setHeading(newHeading));
		}
		return degreesMoved.get(headTurtleIndex());
	}

	@Override
	public double moveForward(double distance) {
		for(TurtleState t : getActiveTurtleStates()){
			t.moveForward(distance);
		}
		return distance;
	}
	
	public double right(double degrees){
		for(TurtleState t : getActiveTurtleStates()){
			t.right(degrees);
		}
		return degrees;
	}
	
	public double left(double degrees){
		for(TurtleState t : getActiveTurtleStates()){
			t.left(degrees);
		}
		return degrees;
	}

	@Override
	public double setTowards(double x, double y) {
		ArrayList<Double> degreesMoved = new ArrayList<>();
		for(TurtleState t : getActiveTurtleStates()){
			degreesMoved.add(t.setTowards(x, y));
		}
		return degreesMoved.get(headTurtleIndex());
	}
	
	@Override
	public void setPenSize(int index) {
		for(TurtleState t : getActiveTurtleStates()){
			t.setPenSize(index);
		}
	}

	@Override
	public void setShape(int index) {
		for(TurtleState t : getActiveTurtleStates()){
			t.setShape(index);
		}
	}

	@Override
	public void setPenColor(int index) {
		for(TurtleState t : getActiveTurtleStates()){
			t.setPenColor(index);
		}
	}
	
	/*
	 * The following methods are visible to composite turtle states only
	 */

	/**
	 * Returns "head" active turtle. (TurtleState that will represent group in singular getter methods)
	 * @return
	 */
	private TurtleState getHeadActiveTurtle(){
		return getActiveTurtleStates().get(this.headTurtleIndex());
	}
	
	/**
	 * Returns "head" turtle index
	 * @return
	 */
	private int headTurtleIndex(){
		return getActiveTurtleStates().size() - 1; // I chose this to be the first turtle, so all group getters will return for first turtle
	}
	
	/**
	 * Returns modifiable map (copy) of all turtles
	 */
	public Map<Integer, SingleTurtleState> getAllStates() {
		Map<Integer, SingleTurtleState> copy = new HashMap<>();
		for(Entry<Integer, SingleTurtleState> e : singleTurtles.entrySet()){
			copy.put(e.getKey(), new SingleTurtleState(e.getValue()));
		}
		return copy;		
	}
	
	private List<SingleTurtleState> getActiveTurtleStates(){
		List<SingleTurtleState> activeTurtles = new ArrayList<>();
		for(SingleTurtleState turtle : singleTurtles.values()){
			if(turtle.isActive()) activeTurtles.add(turtle);
		}
		return activeTurtles;
	}

	public void setState(int id, SingleTurtleState s){
		singleTurtles.put(id, s);
	}
	
	public SingleTurtleState getState(int id){
		return singleTurtles.get(id);
	}
	
	@Override
	public CompositeTurtleState getCompositeCopy() {
		return new CompositeTurtleState(this);
	}
	
	/**
	 * This method exists to allow the class to be iterable
	 */
	@Override
	public Iterator<SingleTurtleState> iterator() {
		return this.singleTurtles.values().iterator();
	}

	/**
	 * Returns string for display
	 */
	public String toString(){
		String s = "";
		for(SingleTurtleState t: singleTurtles.values()){
			s += String.format("Turtle: %d\n%s", t.getID(), t.toString());
		}
		return s;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void setActive(boolean active) {
		return;
	}

	@Override
	public int getStampCount(){
		return getHeadActiveTurtle().getStampCount();
	}

	@Override
	public void addStamp() {
		getHeadActiveTurtle().addStamp();
	}

	@Override
	public void clearStamps() {
		getHeadActiveTurtle().clearStamps();
	}

}
