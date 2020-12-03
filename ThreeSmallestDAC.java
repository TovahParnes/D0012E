public class ThreeSmallestDAC {

    public static int[] generateArray(int size, int max) // generates a list of a specific size
	{
        int[] list = new int[size];
		for(int i=0; i < size; i++){ // fills list with random numbers between 0 and max
            double random = Math.random()*max;
            list[i] = (int)random;
        }
        return list;
    }

    public static int determineSize(double k) // generates the size of the list according to the formula 3*2^(k-1)
	{
        int size = 3;
        for (int i = 0; i < k-1; i++){
            size = size*2;
        }
        return size;
    }

    public static void printList(int[] list) { //Prints all elements in list
        System.out.print("List: ");
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    public static boolean checkSmallestThree(int[] list, int thirdMin){ // checks if there are two smaller elements in the list than the third smallest
        int count = 0;
        for (int i = 0; i < list.length; i++){
            if (list[i] < thirdMin){
                count++;
            }
        }
        return count<3;
    }

    public static int[] TwoListToOne(int[] a, int[] b){ // Combine two lists of the three smallest to one of the three smallest
        // runs 2^(k-1)-1 times with 3 comparisons
        // worst case = best case: 3*2^(k-1)-3 = n-3 comparisons
        int[] min = new int[3];
        int aCurr, bCurr;
        aCurr = bCurr = 0;

        for (int i = 0; i < 3; i++){ // compare the smallest elements and add the smallest to the list, three times
            if (a[aCurr] <= b[bCurr]){ // if list a has the smallest element
                min[i] = a[aCurr]; 
                aCurr++;
            } else{                     // if list b has the smallest element
                min[i] = b[bCurr];
                bCurr++;
            }
        }
        return min;
    }
    
    public static int[] SortThree(int a, int b, int c){ // runs 2^(k-1) times with 2-3 comparisons
        // worst case: 2^(k-1)*3 = n comparisons
        // best case: 2^(k-1)*2 = n*2/3 comparisons
        int[] sorted = new int[3];
        if (a <= b) { // Comprate first two elements and place them
            sorted[0] = a;
            sorted[1] = b;
        } else {
            sorted[0] = b;
            sorted[1] = a;
        }
        // Compare third element and place in list
        if (c >= sorted[1]) { // if third element is largets
            sorted[2] = c;
        } else if (c >= sorted[0]){ // if third element is second largest
            sorted[2] = sorted[1];
            sorted[1] = c;
        } else{ // third element is smallest
            sorted[2] = sorted[1];
            sorted[1] = sorted[0];
            sorted[0] = c;
        }
        return sorted;
    }

    public static int[] ThreeSmallest(int i, int j, int[] list) { //i - begining of list, j - end of list
        if(j-i <= 3){ // base case: the interval of the list is 3 elements
            // runs once for each call of the algorithm, a total of 2^(k-1)+2^(k-1)-1 with one comparison
            // worst case = best case: 2^(k-1)+2^(k-1)-1 = 2n-1 comparisons
            return SortThree(list[i], list[i+1], list[i+2]); // Sorts the elements and returns them
        }
        int mid = (i+j)/2; 
        int[] minLeft = ThreeSmallest(i, mid, list); //recursively does the left side
        int[] minRight = ThreeSmallest(mid+1, j, list); // recursively does the right side
        return TwoListToOne(minLeft, minRight); // compares the two lists and returns the three smallest
}
	public static void main(String[] args) {
        int size = determineSize(4); // generates the size
        int[] list = generateArray(size, 100); // generates the list of given size with a max for each value
        printList(list);
        int[] min = ThreeSmallest(0, size-1, list); // find the three smallest
        System.out.println(checkSmallestThree(list, min[2])); // Check that the algorithm is correct
        System.out.println("Three min values: " + min[0] + " " + min[1] + " " + min[2]); // print the result
    }
    
    // total comparisons (worst case): 2n-1 + n-3 + n = 4n-4 = O(n)
    // total comparisons (best case): 2n-1 + n-3 + n*2/3 = (11n-12)/n < (12n-12)/3 = 4n-4 = 0(n)

}
