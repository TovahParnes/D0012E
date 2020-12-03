package TEST;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class DAC {
	
	private static ArrayList<Integer> list; 
	//static int max = 0;
	//static int min = 10000;
	
	
	private static ArrayList<Integer> randomArray(){				// creat a random array.
		Random r = new Random();
		list = new ArrayList<Integer>();
		
		int low = 1;
		int high = 100;
		for (int n=0;n<32;n++) {
			 list.add(r.nextInt(high-low) + low);
		}
		return list;
	}
	
	public static int[] minAndMax(List<Integer> list2) {
		//System.out.println( list2);
		if (list2.size()<3) {								// To cheak if the list is atleast 2 in length. 
			int[] maxMin = new int[2];						// Creat a temporary list with max value in the first spot and min value in the seconed.
			if (list2.get(0) >list2.get(1)) {				// Looks for with of part of the array has the maximum value. 
				maxMin[0] = list2.get(0);					
				maxMin[1] = list2.get(1);					// If max value was in spot [0]
				
			}
			else 											// If max value was in spot [1]
			 {
				maxMin[1] = list2.get(0);
				maxMin[0] = list2.get(1);
			}
			
			return maxMin;									// return the array. 
		}
		else {
			
		int mid= (list2.size())/2;							// split the ranadomeArray in half. 
		
		int[] left =  minAndMax(list2.subList(0,mid));						// Takes the left side of the array: recursive function. 

		int[] right = minAndMax(list2.subList(mid,list2.size()));			// Takes the right side of the array: recursive function. 	
		
		int[] maxMin = new int[2];											// Cheak if the right side of the array or the left side has max and min 
																			// value 
		if (left[0] > right[0]) {
				maxMin[0]= left[0];
		}
		else {
			maxMin[0]= right[0];
		}
		
		if (left[1] < right[1]) {
			maxMin[1]= left[1];
	}
		else {
		maxMin[1]= right[1];
	}
		return maxMin;
		}
		
	}
	
	
	 public static void main(String[] args) {
		int[] answer =( minAndMax(randomArray()));
		int arr[] = { 50, 4, 99, 23, 65, 12, 46, 15 };
		//int[] answer =minAndMax(arr);
		System.out.println("Answer max = "+ answer[0]);
		System.out.println("Answer min = "+ answer[1]);
		System.out.println("Answer = "+ (double)answer[0]/answer[1]);
	    }
}

