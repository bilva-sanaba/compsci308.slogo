Slogo Addition
==============

#### Dhruv K. Patel
-------------------

## Estimation

**Length:** 1 hour

**Number of Files:** 3 

* I will have to create a new class for the STAMP command, modify the world class to allow for the command to happen, and modify some some sort of view class to read the stamps from the model.

## Review

**Actual Length:** 1.5 hours

**Actual Number of Files:** 4

* I was correct about the first three, but I forgot about a class for the `ClearStamps` command.

* No, in the model I was able to very quickly make the proper additions, but I had difficulties adding the proper modifications in the view.

* I did not get this completely correct on the first try. In the model, I was able to get it working correctly, but, because I also had to modify the view to account for these changes (code which I did not write) it took me much longer to understand how to work the code. 

## Analysis

* The ability to add a new Command in the model was extremely straightforward to do, even after the long time away from my code. It was well documented, and I was able to jog my memory pretty quickly. However, documentation linking the model to the view and in the view itself was very sparse (if it existed at all). On top of that, the code in the view was very unorganized - it was like navigating a maze to add a simple feature. 
* To improve this, I could once again go over the API with my teammates to ensure that the link between the view and model is solid and well documented.
* If I were not familiar at all with the code, the parts that were documented (like commands) would be very easy to pick up and implement, but the parts that were not documented or written well (like the view API) would be difficult to extend.

