#Part I
1. The API/Design is flexible in that the parser reads through every word in the user command the same way (i.e. adding a 'SlogoNode' to the tree structure. Additionally, the use of the factory API allows for the creation of objects using a generic interface. In this manner, when each SlogoNode is handled using a recursive depth first search, the factory class will instantiate a command or parameter object which will then have the appropriate specific effect on TurtleData and Trajectory.
2. The API/Design encapsulates design decisions by instantiating several different classes from the Command interface, which have specification, lower done. Because of this, each command object may be handled the same way without knowledge of its specific properties and still carry out the intended effect.
3. There are a plethora of error cases that may occur because of the parser. Exceptions will need to be thrown for any command that is formatted incorrectly, has too many/too few parameters, has invalid parameters, has invalid syntax, etc. “Try and catch” will have to be used in the parser to handle such errors and then display a user friendly error message to the user.
4. The API/Design is good because of its use of encapsulation to hide functionality. Additionally, for all the reasons mentioned in Parts 1 and 2, the parser is effective in translating user commands into actual commands, which impact the turtle through TurtleData and Trajectory.


#Part I
1. Design Patterns are currently utilized in the project through Factory, Chain of Command and Builder. Factory, as explained, determines which specific objects are used. Such objects have been created through the builder design pattern, and chain of command allows the parser to send a command without knowing what builder-made object will receive and handle it.
2. Reflection and lambda expressions will certainly be useful in implementation. These advanced Java features will be efficient in making the design for the backend. In particular, reflection will be useful in determining which specific command class to use.
3. The feature that I am most excited to work on is the reading and interpretation of commands from the tree structure. I already have a pretty good idea of how to implement this aspect of the program and I think it will be a good opportunity to familiarize myself with reflection.
4. The feature that I am most worried about working on is the exception handling for the parser. There is so much potential for error via user command so I am fairly confident that implementation of error checking will be a massive undertaking. Additionally, checking for errors might be difficult because the user has complete license to type anything into the command window.
5. 
Five use cases:
1.	User inputs nothing- parser checks if commandList is null, if so, throw error
2.	fd 50- command and then parameter are added to tree, recursion creates command and paramter object, TurtleData is updated
3. xcor y- parameter error is checked when the tree is handled
4. fd- no parameter is used so default 0 is added to coordinate according to heading
5. sum 1- missing a parameter so default 0 is summed with 1