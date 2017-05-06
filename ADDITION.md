CompSci 308: SLogo Addition
===================

## Estimation
 
### How long do you think it will take you to complete this new feature?

I think that implementing this new feature should only take about an hour.

#### How many files will you need to add or update?

I should only need to update the turtle state to include a boolean parameter to check if there is a stamp and to add an ImageView in TurtleAnimator when there is a stamp (i.e., the boolean is true). To clear stamps, I will just check the booleans and remove the ImageViews (stored in a List) from the Group that the turtle is drawing on. Because our code in the backend was so extendable, this should not take very long to implement.