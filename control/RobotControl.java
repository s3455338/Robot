package control;
import java.util.Scanner;
import robot.Robot;

// Robot Assignment for Programming 1 s2 2016
// Adapted by Caspar from original Robot code in RobotImpl.jar written by Dr Charles Thevathayan
public class RobotControl implements Control
{
	private Robot robot;

	private static final int MAX_HEIGHT = 13;

   private static final int MAX_WIDTH = 1;

   private static final int MAX_DEPTH = 12;

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
		Scanner input = new Scanner(System.in);
		
		/////////////Bar Section//////////
		System.out.println("Enter number of bars (min 1/max 6:");  
		int bars = input.nextInt(); 
		 
		if(bars <= 0) // User can not go less than 0
		{
		    System.out.println("Too low");
		     return;
		}
		else if(bars >=7) //User can not go higher than 7
		{
		   System.out.println("Too high");
         return; 
		}
		
		
		int[] barHeights = barDoWhile(input, bars);// Loops until user enters numbers between 0-7    
		//  ** } while (bars > 7 && bars < 0); doesnt work??
		
		///////////Block Section////////////
		System.out.println("Enter number of blocks (min 1/max 6:");  
      int blocks = input.nextInt(); 
       
      if(blocks <= 0)
      {
          System.out.println("Too low");
           return;
      }
      else if(blocks >=7)
      {
         System.out.println("Too high");
         return; 
      }
      
		   
		int[] blockHeights = doWhile(input, blocks); // Loops until user enters numbers between 0-3
		// initialise the robot
		robot.init(barHeights, blockHeights, height, width, depth);

		// a simple private method to demonstrate how to control (assignment PART B)
		moveToHeight(MAX_HEIGHT);
		
		moveToWidth (MAX_WIDTH);
		moveToDepth (MAX_DEPTH);
		
		
	}

   private int[] barDoWhile(Scanner input, int bars)
   {
      int[] barHeights = new int[bars];
		for (int i =0;i<barHeights.length;i++)
		  
		do {
		   System.out.println("Enter Height of bar(min 1/max 7:");
		 bars = input.nextInt();
		 barHeights[i]=bars;
		   } while (bars > 7);
      return barHeights;
   }

   private int[] doWhile(Scanner input, int blocks)
   {
      int blockHeights[] = new int[blocks];
		for (int i =0;i<blockHeights.length;i++)
		   
	      do {
	         System.out.println("Enter Height of blocks(min 1/max 3:");
	       blocks = input.nextInt();
	       blockHeights[i] = blocks;
	         } while (blocks > 3);
      return blockHeights;
   }

	private void moveToHeight(int height)
	{
	   
		while (this.height < height)
		{
			robot.up();
			this.height++;
			
		}
      while (this.height > height)
      {
         robot.down();
         this.height++;
      }
   }
	  private void moveToWidth(int width)
	   {
	      while (this.width < width)
	      {
	         robot.contract();
	         this.width++;
	      }
	      while (this.width < width)
         {
            robot.extend();
            this.width++;
         }
	   }
	  
     private void moveToDepth(int depth)
     {
        while (this.depth < depth)
        {
           robot.lower();
           this.depth++;
        }
        while (this.depth < depth)
        {
           robot.raise();
           this.depth++;
        }
     }
     
     
	// assignment part B methods implemented here
}
