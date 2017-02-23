##Part 1
	What about your API/design is intended to be flexible?
Our API is designed to be easily extendable to allow for multiple turtles to be added to the functionality.  It also is trying to easily allow for additional design customization to the user (upload an image for turtle, upload a background, etc) by ensuring that the visuals exist solely in the GUI and are never seen by the backend.

	How is your API/design encapsulating your implementation decisions?
Our API is all about encapsulation.  The frontend only sends strings to the backend and never sees what it does with them.  It only receives back a queue of TurtleData, which is a collection of state variables.  The backend never concerns itself with how the frontend is using those states to draw lines or place turtles.  There is no other communication between the two ends of the program

	What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
If a user tries to press run without anything being typed in the text box, 
the program handle it by merely doing nothing.
The major error that could happen  is if the user types an invalid command that the parser can not read.  The public methods in my API will be called in a try-catch in the controller class.  The controller will throw up an alert telling the user that the command they typed was invalid
	
	Why do you think your API/design is good (also define what your measure of good is)?
I think our API/design is pretty good: It has minimal interactions between the front and back end.  This makes it very flexible, as the different pieces have almost no dependencies on each other so can be substituted out easily.

##Part 2
	How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
I don't currently plan to use any of the design patterns, none of them really seem to apply to my assignment.  If I use any, it would be the Iterator design pattern, since the program will need to iterate over the TurtleData in each trajectory.  However,  there are already enough built-in objects the implement Iterator, like Queues, that I don't anticipate needing to write my own
	
	How do you think the "advanced" Java features will help you implement your design?
My team plans to use reflection to convert from the Strings entered in the textbox to the Commands that will each reside in their own classes.  This will be implemented in the parser class.  
	
	What feature/design problem are you most excited to work on?
I am most excited about getting the turtle to move fluidly across the screen and leave behind an accurate trail.
	What feature/design problem are you most worried about working on?
I am most worried about figuring out all of the potential exceptions,  I feel like I will definitely miss a lot of them, so a lot of testing will be needed
	Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).


