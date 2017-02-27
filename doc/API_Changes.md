API Changes
===========
## Outer View:
	*add changes here*
## Inner View:
	*add changes here*
## Outer Model:
	*add changes here*
## Inner Model:

1. Since the rest of the model needs to know whether each command needs the trajectory object to perform, I added this method: - Dhruv
	``` java
	/**
	 * Returns true if Command needs the turtle's trajectory,
	 * false if not.
	 */
	public boolean needTrajectory();
	```