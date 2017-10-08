# SLOGO (Simple Logo IDE)
Bilva Sanaba, Jacob Weiss, Alex Blumenstock, Dhruv Patel 
March 1 - March 21 2017  

## Get Started
Program is run through the main class, and no other classes contain main. 

## Testing
Several code files containing predefined methods are written. These can be loaded through the UI. 

## Resource Files
Resource files for GUI Text and CSS are included in the resource package. XML configuration files are stored in the data package. Additionally, language translation files are included to run the IDE in various documented languages. 

## Using the Program
* After the program starts, enter code and press run to visualize the code.
* Full code documentation can be found here: <http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php> 
* Front end allows for running various slogo commands without the need of code entering.
* Save and load features allow to easily store small programs.
* There is a new tab and clear button to improve user experience. 

## High Level Design
The project follow a Model-View-Controller design pattern and utilizes a stored 'turtle state' data object to update and communicate between the front end and backend. All the front end buttons create code which the backend interprets in order to control points of access to the state data.  

