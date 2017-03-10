API Changes
===========
## Outer View:
	*add changes here*
## Inner View:
	*add changes here*
## Outer Model:
	
### Model
This used to be a method that only returned the next Trajectory of a turtle. But, we needed to send other information, so we are instead returning a world object to hold that information
	```java
	/**
	 * Returns world of turtles and background
	 */
	public UnmodifiableWorld getWorld(String commands) throws CommandException;
	```
	
We needed a way for the controller to set the language of the model.
	```java
	/**
	 * Sets language for model to interpret with
	 */
	public void setLanguage(String language);
	
### UnmodifiableWorld
This allows the front-end to access new additions to the trajectory
	```java
	/**
	 * Returns Trajectory composed of all turtle
	 * trajectories
	 */
	public Trajectory getTrajectoryUpdates();
	```

This is to implement command background color changing
	```java
	/**
	 * Gets background color index of world
	 * @param newColor
	 */
	public int getBackground();
	```
	
This is so commands can define palletes
	```java
	/**
	 * Gets pallete updates
	 */
	public Map<Integer, ArrayList<Integer>> getPalleteUpdates();
	```
	
So the front-end knows when to clear lines between trajectories
	```java
	/**
	 * Returns whether world should clear this round
	 */
	public boolean shouldClear();
	```
	
So the front-end can display variables
	```java
	/**
	 * Returns global variables
	 */
	public VariableContainer getVariables();
	```

So the front-end can display runtime-created commands	
	```java
	/**
	 * Returns all user-set commands
	 */
	public Collection<String> getCommandNames();
	```
	
### Unmodifiable Turtle Composite
So multiple turtles can be viewed by front-end
	/**
	 * Returns viewable map of single states
	 */
	public Map<Integer, SingleTurtleState> getAllStates();

Allows states to be copied without mutation to originals
	/**
	 * Returns copy of composite state
	 */
	public CompositeTurtleState getCompositeCopy();

This is from turtleState interface extension	
	/**
	 * Returns generic copy of turtle state
	 * @return
	 */
	public TurtleState getModifiableCopy();
	
This allows it to be iterable through singleTurtleStates (Main access point for frontend)
	/**
	 * Returns unmodifiable iterator
	 */
	public Iterator<SingleTurtleState> iterator();
	

## Inner Model:
### Token
Allows generic holder to access type of token
	/**
	 * Returns type of Token.
	 */
	public TokenType getType();
	
Generically allows tree to be evaluated
	/**
	 * Evaluates Token given its arguments.
	 * 
	 * Throws error if arguments don't match up.
	 * @throws CommandException 
	 */
	public Token evaluate(Arguments args, Scope scope) throws CommandException;
	
For displaying errors on GUI pop-ups
	/**
	 * Describes token for error messages
	 */
	public String toString();
	
Allows token to ask for information (only information it requires)
	/**
	 * Asks which information it will need
	 */
	public Scope getScopeRequest();
	
### Arguments - gives commands information they need from string input
Allows argument to be added dynamically
	/**
	 * Adds argument to end of list
	 */
	public void add(Token arg);
	
Allows argument to be recalled dynamically
	/**
	 * Gets argument from list index
	 * @return 
	 */
	public Token get(int index);
	
Allows last item in args to be obtained.
	/**
	 * Gets last argument in the list
	 * @param index
	 * @param t
	 */
	public Token getLast();

Sets value at certain point
	public void set(int index, Token t);
	
For error checking and command stuff
	/**
	 * Get number of arguments
	 */
	public int numArgs();
	
This checks for difference between command args and other args
	/**
	 * Compares argument types of two sets of arguments
	 * 
	 * To return true, set must have same length, and all
	 * corresponding arguments must have same type.
	 * @throws CommandException 
	 */
	public void checkForTypeDifferences(Arguments input) throws CommandException;
	}
	
So things can iterate through arguments
	/**
	 * Allows to be iterable
	 * @return
	 */
	@Override
	public Iterator<Token> iterator();

### Command

Allows command to request information from interpreter	
	/**
	 * Gets scope request of command (what information the command will need)
	 */
	public Scope getScopeRequest();
	

Null command does nothing when created
	/**
	 * Tests for null command
	 */
	public boolean isNullCommand();
	
For unlimited args extension
	/**
	 * Tests for unlimited args
	 */
	public boolean hasUnlimitedArgs();



