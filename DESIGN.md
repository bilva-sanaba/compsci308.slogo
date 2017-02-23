
#Plan

##Introduction

The goal of this project is to build an IDE that can compile SLogo code into Java methods.  The program should be able to generate a user interface that can take in text commands, handle a list of given SLogo commands and display the correct corresponding turtle movements, and generate error messages for unfamiliar commands without crashing.  On the front end, we intend for the specific buttons available to be flexible,  allowing other programmers to create their own ways to allow users to choose Turtle images, pen color, and background color.  On the back end,  we intend for the program to be easily extended to allow for multiple turtles and additional commands other than the ones initially given.  The GUI, the text-to-command parser, and the turtle-position calculator should all be closed to modification.  However,  within the GUI,  many of the buttons given to the user will be abstracted to allow for extension.  The parser should use reflection translate text commands, allowing for easy extension simply by adding new classes corresponding to new commands.  The turtle-position calculator should not be open.

##Design Overview 
	
	The main control loop of this design will be initiated by a “Controller” class. This class will contain an instance of the back-end “model” and the front-end “view”. The purpose of the controller is to monitor the interaction between the model and view - mainly when the “run” button is pressed. In such a scenario, the Controller obtains a trajectory of the Turtle’s motion from the backend (given the GUI input) and sends the new trajectory to the GUI. Other than that, the model and view are completely isolated from one another.  The four APIs are Internal GUI, External GUI, Internal Model, External Model.  The External GUI API contains methods needed to pass String commands to the parser, and to receive Trajectory information from the Model.  The Internal GUI API contains methods to get and alter the various colors and images on the display, to allow for extension to different objects offering different choice sets.

###View
The view is the GUI, which has a text input region where the user can type, a scrollpane where the user can see past commands entered.  There is a RunButton with an onClick() method that passes the text inputs to the parser, and ComboBoxes that allow changing the background color, turtle image, and pen color.  A basic view of this is shown in the User Interface section. 

###Model
The model is in charge of two main functionalities:
Parsing the user input code into a list of commands
Using the list of commands to create a new trajectory

####Commands
	Commands are created using the “Factory” design pattern, where each command is a Product that can be “executed” through a common interface. This (using reflection) allows commands to be added to a registry of possible commands without a switch statement. It also allows for new commands to be added during runtime (for example when defining functions)

####Parser
	The Parser iterates through a String input and searches for keywords (Command IDs). When it finds a key word, it searches for lists of arguments. Each list of arguments is mapped to the command ID, and added to a packed data structure that encapsulates commands and specific arguments/Commands in the Parameters of each command. 

####Execution
	The packed data structure from the parser is unpacked in the Execution. Each Command is executed recursively through the unpacked structure. For commands that alter the trajectory of the Turtle, the Execution adds new Turtle States to the trajectory. After Execution, the final trajectory is returned to the Controller
	


##User Interface

The User Interface will look similar to the image above. It will have a run and clear button as well as a help button. The run button will execute code written in the left text area. If the code is invalid a popup will appear saying that the code could not compile. The clear button can be pressed at any time to clear all pen drawings and reset the turtle. The help button will pull up the HTML help link as stated in the Sprint 2 implementation. There will be multiple combo boxes such as the Choose Language (as well as Turtle Color, Pen Color, and Turtle Image boxes) which can be interacted with by the user to dynamically change the environment display. Finally on the left side, inside the black box will be a list of current variables and on the right side will be a list of recently executed code which can be recalled. 

##API Details 
	
###Front-end external API

public interface GUIAPI {
	/*
	 * This API represents the External API for the front-end of SLogo. This API
	 * contains the methods with which the GUI will communicate with the
	 * Controller, and consequently, the back-end. There are two methods in this
	 * API, one to allow the GUI to get information, and the other to pass
	 * information from the GUI into the back-end.
	 */
	public void setTurtle(Trajectory T,;
	/*
	 * This method should be called specifically by the Controller class. As a
	 * parameter, it takes a Trajectory, which is a Collection of TurtleData.
	 * Each TurtleData is an object consisting of the state variables of the
	 * turtle (X,Y,Heading, Penup, Visible) . This method will call other,
	 * private methods to create a smooth display of the turtle moving from
	 * state to state in the GUI.  This method allows the GUI to receive the
	 *movements of the turtle from the backend.
	 */

	public String getCommands();
	/*
	 * This method is also intended to be called by the Controller class. This
	 * method will take whatever is in the GUI's TextInputWindow and pass it to
	 * the controller class, which will send it to the Model's parser.  This is to allow the
	 * back-end access to inputs into the GUI.
	 */
}

	
###Front-end Internal API
	/*
	 * This API represents the Internal API for the front-end of SLogo. This API
	 * contains the methods with which the GUI operates. 
	 */

/* Class used to store info on a Turtle’s image and pen color
/* The GUI class uses all these methods for changing a Turtle (which it contains an instance of)
Public class TurtleView();
	/* This method is called by the GUI in order to update the displayed turtle image when the user 
/* selects a new turtle image
Public void changeTurtle(Image i);

/* The GUI uses this method in order to display the current Turtle Image
Public Image getTurtleImage();


/* This method is called in the GUI on a button click by the user for the combo box attributed      /* with pen color
Public void changePenColor(Color c);

/*The GUI uses this method in order to determine what color a line should be drawn as
Public void getPenColor(Color c); 


### Back-end API
####External
``` java
public interface Model {
	
	/**
	 * Returns a trajectory object that encapsulates
	 * the Turtle's future trajectory of states
	 * give a string that represents commands entered
	 * into GUI text box
	 */
	Trajectory getTrajectory(String commands);
}
```
####Internal
``` java
public interface Command {
	
	/**
	 * Executes a command with "Message" being a 
	 * data structure for arguments and returns with a variable
	 * type and quantity
	 */
	public Message execute(Message arguments);
}
```

##API Example Code

The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.

The user presses the RunButton, which was created in Controller and passed as a parameter to the GUI class.  The RunButton is set on click to call the private Controller method onClick();  
onClick() contains a try catch, where it attempts to call GUI.getCommands() to get a string Commands, Controller.onClick() then calls Model.getTrajectory(Commands), to get a Trajectory T consisting of the TurtleData from the original position of the Turtle and the position after advancing 50 pixels.   This trajectory is tabulated in the back-end using private methods.  Controller.onClick() then calls GUI.setTurtle(T).  GUI then uses private methods to create a fluid movement across the stage.

###Bilva Use Cases: 
The user chooses a Language from the choose language ComboBox. 
	The GUI currentResourceFile instance variable is changed to  the appropriate  language 
resource file. 
When the Run button is pressed, Controller.onClick() is called which performs: 
try{
GUI.setTurtle(Model.getTrajectory(GUI.getCommands()));
}
Catch(CommandException e){
Alert alert=new Alert(e.getMessage();)
alert.show();
}
in GUI.getCommands() 
	The method uses the currentResourceFile to translate the text currently in the text box to English.
This English string is what is passed to the backend as shown above. 

The user chooses a color from the Background Color ComboBox
The background color is created as a shape inserted into the scene at the appropriate location with default color in the GUI constructor. In the GUI constructor, a combobox is created with options to change the background color of the interpreter. When pressed the combobox has a listener which changes the background shape to appropriate color based on index. All of this code will not require public methods. 


###Alex use cases:
The user types “f 50” into the command window.  

Controller.onClick() is called, which performs
try{
GUI.setTurtle(Model.getTrajectory(GUI.getCommands()));
}
Catch(CommandException e){
Alert alert=new Alert(e.getMessage();)
alert.show();
}
The parser experiences an error, which is caught, resulting in a command exception “Command not Found”

The user types “seth 50” into the command window.

Controller.onClick()  is called, which performs
try{
GUI.setTurtle(Model.getTrajectory(GUI.getCommands()));
}
Catch(CommandException e){
Alert alert=new Alert(e.getMessage();)
alert.show();
}
No exceptions are experienced, so the text command is successfully passed to the Model, where it is parsed and processed, and a trajectory is made consisting of the TurtleData of the initial position of the turtle and the position after having the heading set.  This Trajectory is returned to the GUI, where private methods update the graphics.


###Jacob use cases:
Command is “back 50”
The controller class receives the text from the user in the command box. The controller sends the String of the command to the model for the text to be parsed. If the input is valid, the appropriate update to TurtleData will be made; otherwise, the controller will throw an exception and the GUI will display a user-friendly image. The parser will identify the String “BACK” as the ID and then make the appropriate update. The appropriate update is made by calling the execute() in the “Back class,” which implements the Command interface, to update the x and y locations held in turtle data. This is done by using the distance from the lesser cardinal direction (0/360, 90, 180, 270) to determine the angle  using vertical angles, and then using arcSin to find the change in the y-coordinate and using arcos to find the change in the x-coordinate. With the TurtleData object updated, it is then added to the Trajectory object, which is referenced through a public static method in the controller and then used by the GUI to visually update the turtle’s state.


Command is “xcor”
The controller class receives the text from the user in the command box. The controller sends the String of the command to the model for the text to be parsed. If the input is valid, the appropriate update to TurtleData will be made; otherwise, the controller will throw an exception and a user-friendly image will be displayed by the GUI. The parser will identify “xcor” as the ID and then make the appropriate update. The appropriate update is made by calling the execute() method in the “Xcor class,” which implements the command interface, to retrieve the current x-coordinate of the turtle held in turtle data. The execute() method uses .getX()—a public accessor method—on the TurtleData object. Next, the value is sent to the controller and then printed into a display box on the GUI.

###Dhruv use cases:

Command is  `DOTIMES[3] [FORWARD[40] LEFT[40]]`
As the parser is iterating through this String, it will first recognize `DOTIMES` as a command ID. It will search for lists of arguments and will find an argument of type `int` in first list initial list. It will add this to ordered data structure of commands and arguments. It will continue to search for commands, but will instead find a list, which it will interpret as a second set of arguments. It will add this set of arguments (two command arguments) as the second list of parameters. This data structure will be sent to the Execution.  
The Execution unwraps the packed data structure recursively. It will first execute the `DOTIMES` command with the two lists of arguments given. In this command, the `FORWARD` and `LEFT` commands will be executed, and they will return new Turtle Data to add to the trajectory. This will be repeated 3 times (in the `DOTIMES` command object) and the final trajectory will be returned by the `DOTIMES` execute method. This trajectory will be sent to the front end.  
  
Command is  `SUM 40 2`
The parser will iterate through the String and find the `SUM` command as a valid ID. It will then search for arguments and find only two integers. This will be added to the data structure of commands to be executed, with two integers as the arguments  
  
The Execution will iterate through the data structure and only call the `execute()` method of the `SUM` command. This method will use mathematical operations to add the two integers and return a new integer. Because nothing is expecting to handle the return, the returned integer “42” will not be used or displayed anywhere on screen.  


 
##Design Considerations
We decided to create a Controller class that creates new instances of both the View and the Model classes.  This is to avoid having a backend that only exists when called by the frontend, so that the two parts can be considered separate.  The downside of this is that the “Run” button, which is the link between the frontend and backend, needs to exist independently from either.  We decided that in the controller class, a new RunButton should be initialized.  This View will have a text window where commands will be entered.  The RunButton’s event handler sends  the model the string from the text window, where it is parsed and the next state of the turtle is determined and returned.  The RunButton is passed as an argument into the View class for display on the stage.

In relation to UI, we discussed how various buttons should affect display. We decided that the GUI would create an instance of BackgroundColorButton, PenColorButton, and TurtleImageButton which will all be comboboxes. These buttons will call methods in the GUI that change the GUI background object, as well as the Turtle object instantiated in the GUI. This way all button changes will not have to affect the backend. We considered how we could make these extendable, and realized that if we went this route the GUI could declare each button as an abstract class and instantiate each button as a specific class. This way, for other programmers, one could simply make a new class that implements the appropriate button and the instantiate that in the GUI. 
	We also decided to encapsulate all data from backend as a Trajectory class which stores and sends data for how a turtle should be updated. This is sent to the frontend which updates the turtle appropriately. We considered giving the backend access to the turtle but decided that this was a dependency we did not want as it should be clear that only the GUI is affecting what the appearance, position, etc are of the turtle. By sending information for updating the turtle to the front end from the backend, the backend does not have to directly change the turtle. This also allows for easier extensions such as multiple turtles as only the turtle data would have to be changed, rather than changing what methods affect which turtles in the backend. 
In our design, we employ error checking of user-input code syntax in the Parser, which is part of the “Model”. Because the View is isolated from the Model, this mean that the errors detected in the Model will be thrown up to the Controller, not the View. Conceptually, it makes sense for Alerts to be created in the View, because pop-ups are part of user-interface design. But, to do this we would have to create a public method in the GUI to display alerts, and, as a consequence, we would have to add this method to our public API. Because we do not want to give other classes the ability to create a pop-up in the GUI, we decided to employ our alerts in the Controller class instead of the View. Because alerts are not directly connected to any Stage or Window (in terms of JavaFX), it is possible to create alerts in the Controller without messing with the View class in any way.
In anticipation of an extension that would involve instantiating multiple “turtles” in the view window, we decided to organize relevant information about each turtle actor in a Map format. The map will consist of two objects. The first object—the key—is a TurtleImage object, which contains the ImageView object that is used for the turtle. The user will be able to select from a variety of image options, and the TurtleImage class will convert the provided String into an ImageView object. The basic implementation will feature a combo box that allows the user to select a gif file to represent the turtle on the screen. The second object—the value—is a TurtleData object, which consists of a double for the x-coordinate of the turtle, a double for the y-coordinate of the turtle, a Boolean to represent if the pen is “down,” a double for the heading of the turtle and a Boolean for the visibility of the turtle. The TurtleData class will naturally have accessor methods so that necessary data may be retrieved. The map will be “registered” using the factory design pattern. By corresponding the specific turtle image to the relevant data about the turtle, mutable information about the turtle can be readily and clearly affected by the user’s commands. Additionally, the use of the TurtleData class encapsulates and thus protects the actor’s data so that users do not make invalid changes to the turtle’s state.



##Team Responsibilities
Bilva - (Front End) Responsible for creating the User Interface, working together with Alex in order to create functionality with executable code, editable variable storage, working buttons, and appropriate sending of information to backend. 
Alex-(Front end) Responsible for creating the User Interface, working together with Bilva to allow for the user to alter the appearance of the GUI, type commands, send the information to backend, and view a fluid movement of the results.  
Jacob - Works with Dhruv to create factory design and all Commands. IN addition, I will work on the command parser that will translate the user input into formatted commands that Dhruv may then execute. Will work with Dhruv to implement extensive error checking for faulty user inputs.
Dhruv - Works with Jacob to create factory design and all Commands. Secondarily, will work on command execution after packed Command data structures are sent through the command parser. Will also work with Jacob to weave the Parser and Command Execution together with error checking.





