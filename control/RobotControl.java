package control;

import robot.Robot;

// Robot Assignment for Programming 1 s2 2016
// Adapted by Caspar from original Robot code in RobotImpl.jar written by Dr Charles Thevathayan
public class RobotControl implements Control
{
	private Robot robot;

	private static final int MAX_HEIGHT = 13;

	private int height = 2;
	private int width = 1;
	private int depth = 0;

	// called by RobotImpl
	// the unused arrays are based on cmd line params to RobotImpl not used in this assignment
	@Override
	public void control(Robot robot, int barHeightsUnused[],
			int blockHeightsUnused[])
	{
		// save robot so we can access it from other methods
		this.robot = robot;

		// ASSIGNMENT PART A
		// replace this code with a console based menu to populate the arrays
		int barHeights[] = new int[]
		{ 7, 7, 7, 7, 7, 7 };
		int blockHeights[] = new int[]
		{ 3, 3, 3, 3 };

		// initialise the robot
		robot.init(barHeights, blockHeights, height, width, depth);

		// a simple private method to demonstrate how to control (assignment PART B)
		moveToHeight(MAX_HEIGHT);
		
		// assignment part B implemented here
	}

	private void moveToHeight(int height)
	{
		while (this.height < height)
		{
			robot.up();
			this.height++;
		}
	}
	
	// assignment part B methods implemented here
}
