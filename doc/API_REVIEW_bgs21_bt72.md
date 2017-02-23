#Frontend External
##Part 1
###Flexibility
The design can be easily extended to include other possible data information. If new parameters besides the position,penup, and pendown needed to be changed by the User Input, all that would needed to be added is an additional field in TurtleData and in the TurtleUpdate method a private method that is called to change the Turtle in accordance to the new data must be added. Nothing needs to be changed with how the backend actually communicates with the frontend. 
###Encapuslation
Our API only communicates with the backend through a few lines of code in the Controller.
```
String text = GUI.getText();
GUI.setTurtle(backend.getTrajectory(text);)
```
The methods used are: 
```
GUI.setTurtle(TurtleData t)
``` 
and 
```
Backend.getTrajectory(String text)
```
The getText method is called in the Controller in order to create a string of what is currently in the GUI UserInput Box. The getText method is called by the Run button created in controller. After this, the backend takes this String, and returns a TurtleData object. This is used by the GUI in order to update the Turtle's position and create appropriate lines or visibility. The update occurs by the GUI calling Turtle.Update on its instance of Turtle to appropriately move the turtle.I believe this implementation is good for encapsulation because it means that the Controller only has a button to call a method for backend and front end communication, and the backend only receives a String while the frontend only receives an object with a new TurtlePosition. This design prevents the backend from needing access to the Turtle and viceversa. 

###Exceptions
As for the frontend external API, the only exception that we foresee as a possibility is if the user input an invalid command. In order to deal with this, when the backend parses, if it returns an error, the Controller class catches this and returns an exception class as shown below. 
```
public onRun(){
	string text = GUI.getText();
	try{
		GUI.setTurtle(backend.getTrajectory(text);)
		}catch(CommandException e){
			alert(e.getmessage);
		}	
```
###Overall 
Currently, my idea of a good API is one that creates little use for duplicated code, has restricted public methods to only those that are necessary, and the public methods used allow for communication between classes but only for what is vitally important for multiple classes to have access to. In that regard, I believe that our API is good because the methods defined by the API only involve communication between the frontend and backend with a few methods for retrieving the user input, something vitally important for the backend that should be found by the GUI, and for updating the Turtle through a data info class rather than directly touching the data. Additionally, while the API does not show this, these methods can be used by the Controller with a small amount of code that clearly shows how the dependency between front and back end work. 

##Part 2
###Design Patterns
Currently both of us have very similar API's and both seem to lack too much of an emphasis on specific Design Patterns. One interesting idea we can up with was using the Chain of Responsibility Design Pattern. Currently, we have a GUI.setTurtle(TurtleData) method. This calls the TurtleView.update(TurtleData) method. We decided that this is currently not too extendable because it causes the Turtle to only be able to update in one way. If we made the TurtleView a interface handler, we could make different instantiations of it which handle update in different ways. RegularTurtleView could update normally, while DotTurtleView could create dots each time is moves. This simple change allows for huge extendability in how TurtleData is interpretted. 
###Advanced Features
For extensions, the group thought (althought not on the API) that making the different buttons into interfaces would allow for extendability in terms of making new button classes that act appropriately. One possibility would be a combobox for choosing colors. In order to have more options, the combobox could be dynamically updated to include more colors through a string input, however this is more on the front end. A similar idea could be applied to saving history. Each valid command can be added to a list which then updates the GUI display of previously used valid commands. This would have to be handled in the Controller as this is where the OnRun command is called. When done so, a GUI method that utilizes binding can be applied so that the new command is added to a stored list in GUI of commands and then updated to the GUI display of them. 
###Exciting Feature
The feature I am most excited to work on is the TurtleView interface and extended classes. For the last two weeks I struggled to think of Design Patterns in my own code and could only simply see them when others mentioned it. As we did this lab I saw the possibility of using it and I hope to be able to apply my thought into code. 
###Least Favorite Feature
I am not excited to work on the JavaFX UI. I signed up to do frontend because I had no experience with it and with that I also did not have any experience with JavaFX/learning a new language without guidance. I am excited to use design principles to make a good front end but based on the past few days, I am not too excited to make the UI as I now realize how tough and frustrating it is to learn a language quickly and without guidance. 
###Use Cases
*fd 50*
```
Controller.onClick() 
	try{
	GUI.setTurtle(Model.getTrajectory(GUI.getCommands()));
}
Catch(CommandException e){
Alert alert=new Alert(e.getMessage();)
alert.show();
}
//GUI.getCommands sends fd 50
//getTrajectory sends back the next Turtle Position
//setTurtle moves the turtle to appropriate location by
// 			sending data to Turtle.update
```

*change Language*
```
Language Map =different map (String: String)(Eng:Lang)
GUI.getCommands{
	GUI.translate()
}
GUI.translate{
	change English String to Spanish
}
```

*pen Up*
```
Same as fd 50
getTrajectory returns Trajectory
Trajectory contains a boolean for penUp
```
*UserInputs: i dont want to code anymore*
```
Controller.onClick() 
	try{
	GUI.setTurtle(Model.getTrajectory(GUI.getCommands()));
}
Catch(CommandException e){
Alert alert=new Alert(e.getMessage();)
alert.show();
}
//Alert is shown 
```
*UserInputs: setheading 50*
```
Same as fd 50
getTrajectory returns Trajectory
Trajectory contains a int for heading
Turtle update uses this to change turtle
```