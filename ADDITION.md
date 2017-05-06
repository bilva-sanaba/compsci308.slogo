CompSci 308: SLogo Addition
===================

## Estimation
 
#### How long do you think it will take you to complete this new feature?

I think that implementing this new feature should only take about an hour.

#### How many files will you need to add or update?

I should only need to update the turtle state to include a boolean parameter to check if there is a stamp and to add an ImageView in TurtleAnimator when there is a stamp (i.e., the boolean is true). To clear stamps, I will just check the booleans and remove the ImageViews (stored in a List) from the Group that the turtle is drawing on. Because our code in the backend was so extendable, this should not take very long to implement.

## Review
 
### How long did it take you to complete this new feature?

It took me about 2.5 hours.


#### How many files did you need to add or update? Why?

I needed to update adapter so that it instantiated a turtle which included the added parameter for stamps (an int value), SingleTurtleState to include the parameter for stamps, as well as an accessor, an addStamp() method and a clearStamps() method, the Turtle State interface to include those methods, I had to add turtlecommands for Stamp and ClearStamps so that the user could write it in SLogo, and I had to add to the resource file so that parsing of the new commands could occur.

#### Did you get it completely right on the first try?

I did not get it completely right on the first try because I had initially thought I could use a Stamp Boolean. With our design, using a Boolean meant that every step, the turtle would stamp while the Boolean was True. I decided to use an int value so that I could add stamps when stamp was called and compare it to the number of stamps currently placed on the GraphicsContext to determine if another should be added. Additionally, this made the return statement for ClearStamps easy to do because I was now keeping track of the number of stamps placed in the TurtleState.It also took some effort to add to and clear the images (stamps) from the graphics context. I had to access the image from TurtleView and then draw it on with correct location, as opposed to simply adding the Image, and clearing entailed me finding the Rectangle surrounding the stamp and then clearing that Rectangle on the GraphicsContext. Sometimes, image translation is a bit off.

## Analysis
 
### Was it as good (or bad) as you remembered?

The backend design was as good as I remembered, as adding the commands and the state parameter were very easy to do, but the tricky pat was with front end.

#### What could be improved?

The front end could probably be designed a bit more clearly. I was relatively unsure of where to include the stamping portion, and the graphics context made this feature particularly difficult to implement. 


#### What would it have been like if you were not familiar with the code at all?

If I were completely unfamiliar with the code, it would have taken significantly longer, although I believe that the naming conventions were explicit enough and the commenting was frequent enough on the backend, which boasted very good and very clear design, for me to have eventually figured it out. 


