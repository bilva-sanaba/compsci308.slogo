##Estimation:
I think that this extension will take me about an hour.  

I think that I will have to change the InputPanel class to give it an ImageChangeBox, which contains the display of the turtles and is clickable to toggle their images.  I will also have to write the ImageChangeBox class, and probably an ImageListCell to serve as its cell factory.  



##Review:
This feature took me about an hour and a half to implement.

As predicted,  I had to alter the InputPanel class to instantiate an ImageChangeBox.  I also had to write the ImageChangeBox and ImageListCell classes to perform the extension.  Unexpectedly, I also had to alter the GUI class, which was keeping a map of existing turtles.  I had to modify this to an ObservableMap, to allow the ImageChangeBox to keep track of how many turtles there are in existence.  

I did not get it completely right on my first try.  I was unable at first to get all of the existing turtles to appear in my ComboBox. Only after making the above change to the GUI class was I able to get each turtle in the ImageChangeBox as soon as it was created. additionally, in the InputPanel, I had to feed an instance of the TurtleComboBox to the ImageChangeBox so that it could add images to the "palette" that is kept track of in the TurtleComboBox.



##Analysis:
The design seems to be a little bit worse than I remembered.  Specifically for this feature, we tied the image state to the backend's World variable, so any change on the front end would be overwritten unless the backend was made aware of the change in images as well.  

It would have been better to keep an observer on the world variable and merely look for changes, instead of consulting it every time a command is run and overwriting any differences.  This would allow the front-end some autonomy on which images can be displayed while letting commands also make changes.  It also seems stupid in hindsight that the TurtleComboBox is responisible for every allowed "shape" a turtle can have, it would probably be better if these shapes were kept track of separately and the TurtleComboBox merely referred back to that.

This would have been much more difficult if I had no familiarity with the code.  Without knowing that all changes go through the backend, so frontend-only alterations are useless, this feature could not have been implemented.  I'm not sure whether someone looking at this code for the first time would be able to realize that.





