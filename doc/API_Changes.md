
API Changes
===========
## Outer View:
###GUI
This method gives the model access to the commands entered into the GUI's textarea
```  java
	/**
	 * 
	 * @return String entered into GUI's text area
	 */
	public String getText()
	
```
This method allows the parser to know what input language to expect
``` java
	/**
	 * 
	 * @return String representation of GUI's current language
	 */
	public String getCurrentLanguage()
```
This method allows the Controller to update various state with the GUI when the run button is pressed
``` java
/**
 * Takes a world variable from Model and updates relevant states using new world
 * @param w world variable from model
 * 
 */
	public void handleRunButton(UnmodifiableWorld w)
```
## Inner View:
###SlogoAlert
This method allows various classes to display SlogoAlerts after generating them
``` java
/**
 * displays Alert 
 */
public void showAlert()
```
###GUI_Configuration
This method allows new tabs containing GUIs to be added to the displayed TabPane to allow for multiple workspaces
``` java
/**
 * 
 * @param gui  GUI to be dsiplayed
 * @param counter which instance of GUI this tab represents, for differentiation purposes
 */
public void addTab(GUI gui,int counter)
```
###GUI
Since the Scene exists in GUI_Configuration, this allows the scene to communicate to the currently selected GUI that there was a key input and allow the GUI to process it
``` java
/**
	 * Processes keystrokes and takes appropriate actions
	 * @param code contains the Key pressed 
	 */
	public void handleKeyInput(KeyCode code)
```
This turns the GUI display into a tab that can be added to GUI_Configuration's tabpane, again to allow for multiple workspaces
``` java
 /**
 * Generates a tab containing all Nodes in GUI
	 * @return tab containing all nodes in GUI
	 */
	public Tab getTab()
```
This method allows this to function as a factory, returning a button with the desired features
###ButtonMaker
``` java
/**
	 * @param label String intended to be displayed on button
	 * @param e Lambda expression containing method that should be executed on click; 
	 * @returns the Button generated
	 */
	public Button createButton(String label, EventHandler<ActionEvent> e)
```
Could have been static, this allows the buttonmaker to generate a label from a string
``` java
/**
	 * Turns String into label for display on Button
	 * @param text desired to be displayed
	 * @return Label generated
	 */
	public Label createLabel(String text) 
```
###GUITab
This allows the GUI to be retrievable from a tab, so that when events happened to the GUITab, the correct GUI could be notified
``` java
/**
 * 
 * @return GUI used to generate Tab
  */
	public GUI getGUI()
```

###TextAreaWriter
This allows for encapsulation of the textwriter while still letting various classes view it's inputs
``` java
/**
 * 
 * @return Text entered in TextArea
 */
public String getText()
```
This allowed for encapsulation of textArea while still allowing various classes to execute commands using it
```java
/**
 * 
 * @param s String to be placed into TextArea
 */
public void setText(String s)
```
This allowed for resizing of the TextArea as needed by the GUI and InputPanel
```java
/**
 * 
 * @param d Double to update TextArea's MaxWidth property
 */
public void setMaxWidth(double d)
```
This allowed for resizing of the TextArea as needed by the GUI and InputPanel
``` java
/**
 * 
 * @param d Double to update TextArea's MaxWidth property
 */
public void setMinWidth(double d)
```  java
This allowed for encapsulation of textArea while letting objects clear it before writing to it
**
 * Clears TextArea
 */
public void clear()
```

#Palette

This allows various panels to get the combobox for display
`` `  java
/**
	 * 
	 * @return ComboBox containg available color palette
	 */
	public ComboBox<String> getPalette()
```
This allows new palettes to be input from resource files or other locations

``` java
/**
	 * Configures ComboBox to display new palette
	 * 
	 * @param newPalette Map containing Integer keys ArrayList rgb representation of
	 * Color 
	 */
	public void setPalette(Map<Integer, ArrayList<Integer>> newPalette)
```
This lets the the class affected by a colorchange determine what color it should become
```java
/**
	 * 
	 * @param index for evaluation
	 * @return Color corresponding to index
	 */
	public Color evalPalette(int index)
```

###TurtleComboBox
This allows the combobox to be given to panes for display
```java
/**
	 * 
	 * @return ComboBox containing shape choices
	 */
	public ComboBox<String> getTurtleChooser()
```
###LanguageFactory
Took the place of a resource file , contains current languages, allows additions
```java
/**
	 * 
	 * @return list of allowed languages
	 */
	 public List<String> getLanguages()
```


###Language
Allows Language to be passed to objects and update when other objects update it
```java
/**
 * 
 * @param lang new language to make object contain
 */
public void setLanguage(String lang)
```
Allows object to see language most recently entered into Language object
``` java
/**
 * 
 * @return language
 */
public String getLanguage()
```
###Fireable Button
Allows a button to be fired without passing the entire button 
```
public void fire()
```
###SpeedSlider
Creates a speed slider with methods for GUI to get display
```
public void getNodes()
```
###PenSizeChooser, PenToggle (same as above)
###BackgroundColorChooser
Allows a button to be fired without passing the entire button 
```
public void fire()
```
###ClickHandler
Allows clicking screen to have an effect and has an update method so  that screenclicks can affect turtles
```
public void update()
```
###InputHandler
Allows keyInputs to have an effect and has an update method so  that screenclicks can affect turtles
```
public void handleKeyInput()
```
###TurtleViewManager
Contains several methods so that different buttons and methods can update the turtles position. Image is needed for display 
```
setX(double xLoc) 
setY(double yLoc) 
getRotate()
boolean getPenBool()
setPenSize(double size)
ImageView getImage(){
TurtleView getTurtleView()
```
###TurtleView
Contains many methods for updating the actual image of the Turtle . Many are reminants of the basic implementation and should be removed or changed.
```
	public Paint getPenColor()
	public ImageView getImage()
	public void setX(double xCoor)
	public void setHeading(double heading)
	public void setPen(boolean x)
	public void setPenUp()
	public void setShape(String s)
	public boolean getPen()
	public void setVisibility(boolean v)
	public void move(Trajectory T)
	public void setPenColor(Paint p)
```
###Adapter
Has a static method for converting one data structure to one more suited for front end reading
```
public static Map<Integer,SingleTurtleTrajectory>getSingleTrajectories(Trajectory t)
```
###SingleTurtleTrajectory
Data class which stores all info needed to update one turtle. 
```
SingleTurtleTrajectory(SingleTurtleTrajectory original)
void addLast(SingleTurtleState nextState)
SingleTurtleTrajectory getMostRecentAdditions()
SingleTurtleState getLast()
void removeLast()
void clear()
int size()
Iterator<SingleTurtleState> iterator()
String toString()
```
###InputPanel
Class which returns pane of all interactable buttons for GUI
```
Language stored here which backend will eventually need
public String getCurrentLanguage()
public Palette getMyPalette()
public Pane getBottomPanel()
```
###LeftPanel and RightPanel
This similarity can be used to extract a superclass. Returns a Pane that will be displayed by the GUI
```
Pane getPane()
```
###CommandScrollPane
Scrollpane which displays the commands on the GUI 
```
This method is needed for the GUI to display the scroll pane

public ScrollPane getScrollPane()
```
###RetrievableStackPane 
Used in order to  associate a StackPane with a string identified 
```
getStackPane()
getString()
```
###UserCommandScrollPane
Used to make scroll pane which displays user commands
```
method needed to  display scroll pane on gui
getScrollPane()
```
###VariableScrollPane
Same as above but clearScrollPane() needed for the updater to use when clear is called. 

###DisplayUpdater
Class which contains methods to change variables representing background, pen color, size etc. 
```
updateDisplay()
```
###TurtleUpdater
Class which contains methods to change variables representing the turtle state
```
moveTurtle()
```




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
	
### Turtle State
This is used to check if console commands should be applied to a given turtle
	```java
	/**
	 *Returns true if turtles is active
	 */
	public boolean isActive();
	```

This is to used to compare turtle states
	```java
	/**
	 *returns true if turtle states are of equal value
	 */
	public boolean equals(Object t);
	```
	
This is used to access and consequently make changes to turtle state 
	```java
	/**
	 * Returns copy of specific turtle state
	 */
	public TurtleState getModifiableCopy();
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
	
### Trajectory

Allows TurtleState to be added dynamically
	/**
	 * Adds TurtleState to end of trajectory if different
	 */
	public void addLastComposite(UnmodifiableTurtleComposite nextState);;
	

Allows TurtleState to be added dynamically
	/**
	 * Adds TurtleState to end of trajectory if different, using addLastComposite method as helper
	 */
	public boolean addLast(TurtleState nextState);
	
Accesses most recent trajectory additions then resets list of recently added trajectories
	/**
	 * Returns a trajectory made up of most recent additions to trajectory
	 */
	public Trajectory getMostRecentAdditions();
	
This is used to find most recently added TurtleState
	/**
	 * Returns last state in trajectory
	 */
	public UnmodifiableTurtleComposite getLast();
	
Clears trajectory
	/**
	 * Clears entire trajectory
	 */
	public void clear();
	
This allows front end to move through TurtleStates and make appropriate view updates
	/**
	 * This method allows the class to be iterated
	 */
	public Iterator<UnmodifiableTurtleComposite> iterator();
	
### Interpreter

Recursive method does pre-order depth first search traversal of tokenNodes in parsed tree	
	/**
	 * Returns Token for recursive purposes (main purpose is to execute commands from console) 
	 */
	public Token evaluateTree(TokenNode root, Scope scope) throws CommandException;
	
### TokenNodeInterface

Adds TokenNode
	/**
	 * Adds TokenNode to tree in parser
	 */
	public void addChild(TokenNode child);
	
Adds TokenNode
	/**
	 * Adds TokenNode to tree in parser if only the Token is given
	 */
	public void addChild(Token childVal);

Retrieves Token from TokenNode
	/**
	 * Accesses Token
	 */
	public Token getToken();
	
Gets the children of the TokenNode in the tree for accessing and evaluation
	/**
	 * Returns list of TokenNodes
	 */
	public List<TokenNode> getChildren();
	
Finds parent-- useful for climbing the tree created in the parser
	/**
	 * Returns parent TokenNode of current TokenNode
	 */
	public TokenNodeInterface getParent();
	
### CommandReformatter

Reformats command by removing next line characters, superfluous white space and comments
	/**
	 * Returns String
	 */
	public String reformatCommand(String command);

Returns ProgramParser which may be used to determine symbol for a given String in the command (uses regular expressions)
	/**
	 * Returns ProgramParser
	 */
	public ProgramParser getParser();

### SlogoParser

Creates the tree to be traversed from the command in the console
	/**
	 * Returns head of tree
	 */
	public TokenNode parse(String command) throws CommandException;
	
### TokenNodeFactory

Creates new TokenNode to be added to tree
	/**
	 * Returns TokenNode for String in command (uses appropriate Token)
	 */
	public TokenNode genTokenNode(TokenNode parentNode, String word, boolean unlimitedParam) throws CommandException;
	
This is used to check how to add TokenNodes in SlogoParser and for command instantiation in TokenNodeFactory
	/**
	 * Returns list of commands which may take infinite args if grouped (e.g. sum, difference, product, etc.)
	 */
	public ArrayList<String> getInfiniteArgsCommand();

Sets language
	/**
	 * Sets language in which commands are read
	 */
	public void setLanguage(String language);
	
	
	


	

	





