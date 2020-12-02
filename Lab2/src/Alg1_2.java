public class Alg1_2 {
    
    public void sort(int[] arr, int start, int end) {   // Input array, start index, end index
        if (end <= start) {  // Check for length 0 or 1, base case
            return;
        }
            int mid = (end+start)/2;    // Midpoint for splitting array

            sort(arr, start, mid);  // Sort left
            sort(arr, (mid+1), end);  // Sort right

            merge(arr, start, mid, end);    // Merge subarrays

    }

    public void merge(int[] arr, int start, int mid, int end) { // Input array, start index, middle index, end index
        int leftSize = mid-start+1;
        int rightSize = end-mid; // Maybe +1 here dno

        // Copy arrays into subarrays, left and right
        int[] left = new int[leftSize];
        for(int i = 0; i < leftSize; i++) {
            left[i] = arr[i+start];
        }

        int[] right = new int[rightSize];
        for (int i = 0; i < rightSize; i++) {
            right[i] = arr[mid+1+i];
        }

        int i = 0, j = 0;
        int k = start;

        // Merge the subarrays into arr
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Remainder of subarrays copied over
        while (i < leftSize) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    static void maxFraction(int[] arr) {    // Returns the two values which form the biggest value when divided
        Alg1_2 sort = new Alg1_2();
        sort.sort(arr, 0, arr.length-1);
        System.out.println("The biggest and smallest numbers are: " + arr[arr.length-1] + ", " + arr[0]);
        System.out.println("They form the fraction: " + arr[arr.length-1] + "/" + arr[0] + " = " + (arr[arr.length-1]/arr[0]));
    }

    static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        int arr[] = { 50, 4, 99, 23, 65, 12, 46, 15 };
        maxFraction(arr);
    }
}
