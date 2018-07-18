import java.util.Scanner;
import java.io.*;
import java.util.*;

public class main {
	public static int min;
	public static int max;
	
	public static void main(String [] args){	
		try{
			File file = new File(args[0]);									//takes in the input file
			String fileName = args[0];
			findMinMax(file);												//reads the file to setup min and max
			bucketSort x = new bucketSort(min, max);						//creates the bucketSort object with size initialized
			x.readFile(file);
			FileWriter fw = new FileWriter(args[1]);						//gets the text file for output results
			x.printSortData(fw);
		}
		catch(IOException e){										
			System.out.println("File Error");
		}
		
		
	}
		
	public static void findMinMax(File file){								//read and finds minimum and maximum integer from the input file
		try{
			Scanner read = new Scanner(file);								//creates scanner object to read the input file
			if(read.hasNextInt()){
				while(read.hasNextInt()){									//if the scanner finds that there is another integer
					int current = read.nextInt();							//save the next integer into a variable for use
					if(current < 0){										//if the next int is negative
						System.out.println("No negative integer allowed!! Try again...");		//prints out an error message
						System.exit(0);										//then exits the program
					}
					if(current < min){										//if new int is smaller than the saved min value
						min = current;										//set min as the new int
					}
					if(current > max){										//if new int is bigger than the saved max value
						max = current;										//set max as the new int
					}
				}//continues to the next existing integer
				read.close();
			}
			else{
				System.out.println("No integers!! Try again...");
				System.exit(0);
			}
		}
		catch(IOException e){
			System.out.println("File Error");
		}
	}
	
	
}
