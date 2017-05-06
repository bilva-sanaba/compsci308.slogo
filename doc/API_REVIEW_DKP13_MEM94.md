API Review
==========
Dhruv K Patel   
Mina Mungekar

## Part 1
1. The View and Model are very isolated in the way our API currently works. This allows either side to be drastically altered, without sacrificing functionality on the other side.


2. The API is an interface on both ends. This forces the Model and the View to contain certain methods and implementations, given a certain set of information. For example, for our case, the model must implement a method that takes in a String (commands in the text box) and outputs a trajectory for the Turtle to follow. As long as the model does so correctly, the View will funcition correctly - it doesn't matter how the model will do so.


3. Commands could be given the wrong number or type of arguments. To solve this issue, each new Command class must have a `getDefaultArgs()` method gives commands the ability to check arguments. If arguments do not follow the standard, it throws an CommandException, with a message that describes which Command and parameters were flawed.

4. The API is simple. It reduces communication between differents ends to the bare minimum. This allows other classes to interact easily and add new features quickly.

## Part 2
1. The Model uses a Factory to initialize Commands and link specific command Classes with their syntax ID. This allows new commands to be instatiated dynamically when needed.

2. Reflection can be used in a Factory to eliminated the need to "hard code" instantiation of all new commands. This improves the design because it allows a programmer to add a new Command to the system without changing any of the internals of the software.

3. I am most excited to work on the Command Factory and instantiation of Commands using reflection. The goal when I'm doing this is to make it as easy as possible to create commands and reduce repeated code. This will hopefully make my job much easier when I have to create the huge list of Commands.

4. I am most worried about how to handle parameters and lists as parameters for commands. This seems difficult because parameters could have different types. Also, when I have to deal with dynamic definition of commands, I think some of my code will not be suited for it, so I am not looking forward to figuring out a design that can encapsulate that.

5. **Use Cases**
	1.  **Command is  `DOTIMES[3] [FORWARD[40] LEFT[40]]`**:
As the parser is iterating through this String, it will first recognize DOTIMES as a command ID. It will search for lists of arguments and will find an argument of type int in first list initial list. It will add this to ordered data structure of commands and arguments. It will continue to search for commands, but will instead find a list, which it will interpret as a second set of arguments. It will add this set of arguments (two command arguments) as the second list of parameters. This data structure will be sent to the Execution.
	The Execution unwraps the packed data structure recursively. It will first execute the DOTIMES command with the two lists of arguments given. In this command, the FORWARD and LEFT commands will be executed, and they will return new Turtle Data to add to the trajectory. This will be repeated 3 times (in the DOTIMES command object) and the final trajectory will be returned by the DOTIMES execute method. This trajectory will be sent to the front end.
	
	2. **Command is  `SUM 40 2`**: The parser will iterate through the String and find the SUM command as a valid ID. It will then search for arguments and find only two integers. This will be added to the data structure of commands to be executed, with two integers as the arguments
The Execution will iterate through the data structure and only call the execute() method of the SUM command. This method will use mathematical operations to add the two integers and return a new integer. Because nothing is expecting to handle the return, the returned integer “42” will not be used or displayed anywhere on screen.

	3. **Command is `Fd Fd 50`**: The parser will form an expression tree in which Fd is the head and it contains Fd as a parameter, which contains 50 as a parameter. The tree is traversed in post order. The 50 gets fed as a parameter into a Fd command, which moves the turtle forward and returns 50, which gets fed up into the first Fd command, which moves the turtle 50 again.

	4. **Command is `Right 10`**: The parser forms a tree with Right as its head and 10 as the paramter. This Right command is activated with the parameter and it moves the heading of the turtle right by 10 degrees.

	5. **Command is `Less? 5 4`**: The parser will form a tree with the static command and 2 parameters. Here, the tree will simply execute the command with the parameters and return 0. Nothing will recieve this returned value.

