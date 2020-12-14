import java.util.*; 

class ProductsSubarray { 
	// Returns product of maxium product subarray 
	static int maxSubArrayProduct(int arr[], int li, int ri) //li = left index, ri = right index
	{ 
	// Base Case: Only one element 
	if (li == ri) 
		return arr[li]; 

	// m = middle point of array
	int m = (li + ri)/2; 

    /* Return maximum of the following cases: 
    - left half has the largest subarray
    - right half has the largest subarray
    - the maximum subarray includes the middle point */
	return Math.max(
        Math.max(maxSubArrayProduct(arr, li, m), maxSubArrayProduct(arr, m+1, ri)), 
        maxCrossingProduct(arr, li, m, ri)); 
	} 


	// Find the maximum possible product in arr[] such that arr[m] is part of it 
	static int maxCrossingProduct(int arr[], int li, int m, int ri) //li = left index, ri = right index, m = midpoint
	{ 
		// Include elements on left of mid. 
        int prod = 1;
        // looks for min and max from middle in case both sides becomes negative as "largest"
        int left_prod_max = Integer.MIN_VALUE; 
        int left_prod_min = Integer.MAX_VALUE;
		for (int i = m; i >= li; i--) 
		{ 
            prod = prod * arr[i]; 
            if (prod > left_prod_max) 
            left_prod_max = prod; 
            if (prod < left_prod_min) 
			left_prod_min = prod; 
		} 

		// Include elements on right of mid 
		prod = 1; 
        int right_prod_max = Integer.MIN_VALUE; 
        int right_prod_min = Integer.MAX_VALUE; 
		for (int i = m + 1; i <= ri; i++) 
		{ 
            prod = prod * arr[i];
			if (prod > right_prod_max) 
            right_prod_max = prod; 
            if (prod < right_prod_min) 
            right_prod_min = prod; 
		} 

        /* Return maximum of the following cases:
        - left max is largest
        - right max is largest
        - right max * left max is largets
        - right min * left min is largest
        */
        return Math.max(left_prod_max, Math.max(right_prod_max, Math.max(left_prod_max * right_prod_max, left_prod_min * right_prod_min)));
    } 


    //------ Help functions -------
    // Generates random array with 2^k elements filled with +- (1 to max)
    static int[] generateArray(int k, int max) {
        int size = 1;
        for (int i = 0; i < k; i++){
            size = size*2;
        }
        int[] arr = new int[size];
        Random r = new Random();
        for(int i=0; i < size; i++){ // fills list with random numbers between 1 and max as well as -1 and -max
            int rand = r.nextInt(max*2)-max;
            while (rand == 0){
                rand = r.nextInt(max*2)-max;
            }
            arr[i] = rand;
        }
        return arr;
    }

    //Prints all elements in list
    public static void printList(int[] list) { 
        System.out.print("List: ");
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }


	public static void main(String[] args) 
	{ 
    //int arr[] = {1, 1, -9, 2, 2, -9, 1, 1};
    int arr[] = generateArray(5, 10); 
    printList(arr);
	int max_prod = maxSubArrayProduct(arr, 0, arr.length-1); 
	System.out.println("Maximum contiguous product is "+ max_prod); 
	} 
} 
