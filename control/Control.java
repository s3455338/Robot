package control;

import robot.Robot;

// Control interface by Caspar (used internally by Robot)
// do not modify this code!
public interface Control
{
	public void control(Robot robot, int barHeights[], int blockHeights[]);
}
