API Descriptions
==========
Bilva Sanaba
Alex Blumenstock
Dhruv K Patel
Jacob Weiss
### Frontend Internal 
To add a front end feature, a new interactable object will have to be created as a class extending an abstract superclass for GUIObjects. This will have to be added into the GUI method which adds all objects. In the subclass of GUIObjects a method will need to be included to update a turtle in someway when some event occurs in relation to the subclass. 

### Frontend External
The frontend communicates with the backend simply by in the text input box, recording what is entered by the user and then sending, as a string, to the parser backend class. 

### Backend Internal
In order to add new methods, create a new subclass which extends the class Methods, a superclass containing an abstract returnNewStates() method. This subclass must be added to the Parser class, which takes a string from the frontend and parses appropriately, and then gives the parsed string to the appropriate subclass. In the subclass, the class would need a public method returnNewStates() which appropriately returns the new states of a turtle based on the input for the method. 

### Backend External
The backend receives information from the front end in the form of a string. A parser class would need to take this string and parse it appropriately. When doing so, it would parse the string and identify which method needs to be called. This method is conducted in a method subclass. The parsed string is sent to that subclass. The subclass must determine possible future TurtleStates and return this.

SLogo Architecture Design
=============
1. **When does parsing need to take place and what does it need to start properly?**

	Parsing takes place in the back end. When the correct button is pressed in the GUI, a backend `getTrajectory` method is called. This method takes in a String of the current command and sends it to a Parser (Also in the back end)
2. **What is the result of parsing and who receives it?**

	The Parser works closely with a set of classes in charge of commands. When known commands are parsed, these commands input the current turtle state and output a List of future states depending on how the command changes the trajectory of the Turtle. The main back end controller, using the parser, stitches all trajectories together into one final trajectory, and this trajectory (a list of Turtle states) is returned to the GUI instance.
	
3. **When are errors detected and how are they reported?**

	Errors are detected mostly by the parser, in which case they throw a certain type of `LanguageError`. These errors are thrown up to the GUI via the `getTrajectory` method, and they are displayed on the screen, with a message about which command did not get parsed properly.  

4. **What do commands know, when do they know it, and how do they get it?**

	Commands know the input state of the turtle, and whatever parameters they require. They get these through their parameters, and when they are called to 'execute', they return their changes to the turtle state trajectory.
5. **How is the GUI updated after a command has completed execution?**

   The final turtle trajectory is output to the GUI via a list of turtle states. The GUI iterates through the trajectory of turtle states and updates its javaFX object accordingly in each iteration.