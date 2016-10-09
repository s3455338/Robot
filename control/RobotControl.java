package control;
import java.util.Scanner;
import robot.Robot;

// Robot Assignment for Programming 1 s2 2016
// Adapted by Caspar from original Robot code in RobotImpl.jar written by Dr Charles Thevathayan
public class RobotControl implements Control
{
   private Robot robot;

   private static final int MAX_HEIGHT = 13;

   private static final int MAX_WIDTH = 10;

   private static final int MAX_DEPTH = 0;

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
      
      // initialize barHeiraght array as required in specs for PART B
   // int[] barHeights = new int[]{2,3,5,7,1,3};            
      
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
      
      // initalize blockHeights array as required in specs for PART B
      
            //int[] blockHeights = new int[]{3,1,2,3,1,1,1};             
      
      // initialise the robot
      robot.init(barHeights, blockHeights, height, width, depth);

   // assignment PART B
      
      int maxblocks= blockHeights[0];
      int maxbars= barHeights[0];
      
      int[] tempbarHeights=barHeights;
      
   //max blocks      
      for(int i = 0; i < blockHeights.length; i++) {
            if(blockHeights[i] > maxblocks) {
              maxblocks = blockHeights[i];
            }
      }
   //max bars  
      for(int i = 0; i < barHeights.length; i++) {
            if(barHeights[i] > maxbars) {
              maxbars = barHeights[i];
            }
      }
      int barsfilled=3; // index of bars
      int blockCovered=0;  // blocks covered till now
      int targetSizeTwo=0; // height of  target 2
      int targetSizeOne=0; //height of target 1
      int comapreHeight=0; // remaing source height
      
 for(int j=0;j<blockHeights.length;j++){
      int temp=0;
      
      //max blocks height     
            for(int i = 0; i < blockHeights.length; i++) {
                  if(blockHeights[i] > maxblocks) {
                    maxblocks = blockHeights[i];
                  }
            }
         //max bars height
            for(int i = 0; i < tempbarHeights.length; i++) {
                  if(tempbarHeights[i] > maxbars) {
                    maxbars = tempbarHeights[i];
                  }
            }
      
   //block heights   
      for (int i = 0; i < blockHeights.length; i++) {
         temp=temp+blockHeights[i];
      }
      
      if(j==0)
      comapreHeight=temp;
      
      if(j!=0){   
      blockCovered=blockCovered+blockHeights[blockHeights.length-j];
      }
      if(j==0)
      moveToHeight(temp+1);   // move to height
      else if(height<=maxbars)  //check height
      moveToHeight(maxbars+1);   
      moveToWidth (MAX_WIDTH);  // expand
      if(j==0)
      moveToDepth (height-(temp)-1);  
      else
      moveToDepth (height-(temp-blockCovered)-1);
      
      robot.pick();  //pick block
      moveToDepth(0);

      if((comapreHeight-blockHeights[blockHeights.length-1-j]+1<10)&&height<11){
      moveToHeight(11);
      }
      
      /// conditons for block size 3
if (blockHeights[blockHeights.length-1-j]==3) {
      
   moveToWidth (barsfilled);
   
   moveToDepth (height-1-blockHeights[blockHeights.length-1-j]-barHeights[barsfilled-3]);  
   
   robot.drop();
   
   moveToDepth (0);

//check conditions   
   comapreHeight=comapreHeight-blockHeights[blockHeights.length-1-j];
   if(comapreHeight>barHeights[barsfilled-3])
   moveToHeight(comapreHeight+1);
   else  
   moveToHeight(barHeights[barsfilled-3]+blockHeights[blockHeights.length-1-j]+1);   
   barsfilled++;
}

/// conditons for block size 2

if (blockHeights[blockHeights.length-1-j]==2) {
   
   moveToWidth (2);
   
   moveToDepth (height-1-blockHeights[blockHeights.length-1-j]-targetSizeTwo);
   
   robot.drop();
   
   moveToDepth (0);

   //check conditions
   comapreHeight=comapreHeight-blockHeights[blockHeights.length-1-j];
   if(comapreHeight>targetSizeTwo)
   moveToHeight(comapreHeight+1);
   else
   moveToHeight(targetSizeTwo+blockHeights[blockHeights.length-1-j]+1);
   
   targetSizeTwo=targetSizeTwo+blockHeights[blockHeights.length-1-j];
}


/// conditons for block size 1

if (blockHeights[blockHeights.length-1-j]==1) {

   
   moveToWidth (1);
   
   moveToDepth (height-1-blockHeights[blockHeights.length-1-j]-targetSizeOne);
   
   robot.drop();
   
   moveToDepth (0);
   
   ///check conditions
   comapreHeight=comapreHeight-blockHeights[blockHeights.length-1-j];
   if(comapreHeight>targetSizeOne)
   moveToHeight(comapreHeight+1);
   else
   moveToHeight(targetSizeOne+blockHeights[blockHeights.length-1-j]+1);
   
   targetSizeOne=targetSizeOne+blockHeights[blockHeights.length-1-j];
}

      // Arms rested at the lowest possible height after the operation is completed.

}
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
   
   
/// height up and down methods
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
         this.height--;
      }
   }
   /// Width extend and contract methods
   private void moveToWidth(int width)
      {
         while (this.width > width)
         {
            robot.contract();
            this.width--;
         }
         while (this.width < width)
         {
            robot.extend();
            this.width++;
         }
      }
   /// depth lower and raise  methods
     private void moveToDepth(int depth)
     {
        while (this.depth < depth)
        {
           robot.lower();
           this.depth++;
        }
        while (this.depth > depth)
        {
           robot.raise();
           this.depth--;
        }
     }
     
     
   
}
