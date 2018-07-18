import java.util.Scanner;
import java.io.*;
import java.util.*;

public class bucketSort {
	
	private int size = 0;
	private int lastIndex;
	private int smallest;
	private int largest;
	private int index;
	private int[] BucketAry;
	
	public bucketSort(){
		BucketAry = new int[size];
	}
	
	public bucketSort(int min, int max){
		smallest = min;									//saves the min and max value into the class
		largest = max;
		size = max - min + 1;							//size is determined by the min and max integer
		lastIndex = max - min + 1;						
		BucketAry = new int[size];						//initializes the array to a specific size
	}
	
	public int getIndex(int data){
		return data - smallest;
	}
	
	public void readFile(File f){						//reads the file and stores the integers
		try{
			Scanner read = new Scanner(f);
			int current;								//used to store the integers
			while(read.hasNextInt()){					//while there is another integer in the file
				current = read.nextInt();				//saves the integer into a variable
				index = getIndex(current);					//calculates and saves the assigned index for this integer
				BucketAry[index]++;							//increments the count in the array at this particular index
			}											//moves on to the next integer
			read.close();								//closes the file after all integers have been read
		}
		catch(IOException e){
			System.out.println("File error! Try again.");
			System.exit(0);
		}
		
	}
	
	public void printSortData(FileWriter w){
		PrintWriter pw = new PrintWriter(w);			//opens the output file
		if(size == 0){									//size is zero when the constructor that assigns the size of array is not called	
			return;										//does nothing
		}
		int count;										//saves the count integer stored at that index
		for(int point=0; point<lastIndex; point++){	    //goes through the BucketSort array
			count = BucketAry[point];					//gets the count
			int number = point + smallest;				//number is the orig integer written on the text file
			while(count > 0){							//while there is an existence of this integer
				pw.println(number);						//writes this integer into the output text file
				count--;								//count decrements
			}
		}
		pw.close();
	}
	
	
	
}

