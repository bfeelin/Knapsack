import java.util.Scanner;

class knapsack
{
  	 int capacity; 											     //original capacity
  	 int[] notStored = new int[25]; 							 //original possible integers
  	 int[] stored = new int[25];								//accepted integers								
	 
  	 knapsack()														//default constructor
  	 {};

	knapsack(int capacity, int[] possible, int[] accepted)
	{
		setCapacity(capacity);										//constructor
		setPossible(possible);
		setAccepted(accepted);
	}
	
   public void setCapacity(int capacity)
	{ this.capacity = capacity; }
	
   public void setPossible(int[] possible)
	{ this.notStored = possible; }
   
   public void setAccepted(int[] accepted)
   { this.stored = accepted; }
	  
	public void knapProblem()
	{
	  int tempCapacity;                                               //next capacity
	  int[] tempNotStored = new int[25];		                       //next possible integers 
     int[] tempStored = new int[25];	                            //next accepted set of ints						            
     
      int possible = notStored[0];								      	 //set possible to the next possible value
      tempCapacity = capacity - possible;                   		//decrement tempCapacity by the next possible 
      
      for(int i = 0 ; i < notStored.length-1; i++)
     	 if(notStored[i] == 0)				
     		 break;											   	     	//shift remaining possible values to exclude value being considered
     	 else													           //tempNotStored will be used in both of the next function calls
     		 tempNotStored[i] = notStored[i+1];		
     
      for(int i = 0; i < tempStored.length-1; i++)
      {
     	 if(stored[i] != 0)                             			//copy stored --> tempStored
     		 tempStored[i] = stored[i];                  
     	 else if(stored[i] == 0)
     	 {
     		 tempStored[i] = possible;                    //copy the next possible value into the first available space in tempStored
     	 	 break;										         //use tempStored in the next knapsack containing the next possible value
     	 }
      }
      
      
      if(capacity == 0)
      {
         for(int i = 0; i < stored.length; i++)					      //print array if capacity == 0
            if(stored[i] != 0)
        	   System.out.print(stored[i] + " ");
         	
         System.out.println();
        	   return;
      }	
      else if(capacity < 0)												//return if remaining capacity is negative
    	  return;
      else if(possible == 0)										         //return if next possible == 0 (end of array)
    	  return;
      else if(possible <= capacity)								      	//recurse if  next possible <= remaining capacity
      {
         knapsack sackWith = new knapsack(tempCapacity, tempNotStored, tempStored);						//recursive call including next possible
         sackWith.knapProblem();

         knapsack sackWithout = new knapsack(capacity, tempNotStored, stored);	                 //recursive call not including next possible			
         sackWithout.knapProblem();																										
      }
      else 
      {     
    	  	knapsack sackWithout = new knapsack(capacity, tempNotStored, stored);	                 //recursive call not including next possible			
    	  	sackWithout.knapProblem();	
      }
      	
	}
}

public class n00668550 
{
	public static void main(String[] args)
	{
		getInput();
	}
	public static void getInput()
	{
		int[] numbers = new int[26];
		String tempInput = null;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter input (capacity followed by weights): ");
      tempInput = input.nextLine();                      //store line of input as a String
      
      String[] array = tempInput.split(" ");            //split input and store into numbers array
         
         for(int i = 0; i < array.length; i++)
            if(array[i] == null)                       //parse array[] (String) --> numbers[] (ints)
              break;                                        
            else
               numbers[i] = Integer.parseInt(array[i]);                         
		initialize(numbers);
		input.close();
	}
	
	public static void initialize(int[] input)
	{
		int []possible = new int[input.length];
		int []accepted = new int[input.length];
		int capacity = input[0];

		for(int i = 0, j = 1; j < input.length-1; j++)			//copy the input into the possible array
			if(input[j] > capacity)								      //excluding values that are larger than the capacity
			{}
			else{
					possible[i] = input[j];					   	//only increment i if the value is the be accepted
					i++;										         //(if the value <= capacity)
				}
         
		knapsack newKnapsack = new knapsack(capacity, possible, accepted);					//create new knapsack object
		newKnapsack.knapProblem();	 
	}
}
