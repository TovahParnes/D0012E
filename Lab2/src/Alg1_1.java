import java.util.Random;

// MaxHeap hard-coded to specifically deal with the problem, only works for k=3.
public class Alg1_1 {
    private int[] heap;
    private int size;
    private static int maxSize = 3;

    public Alg1_1() {
        heap = new int[4];  // Initialize the heap
        this.size = 0;      // Start at size 0
        heap[0] = Integer.MAX_VALUE;    // Top value is inf
    }

    private void swap(int i1, int i2) { // Function to swap two elements at indices
        int temp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = temp;
    }

    private void heapify() {    // Fixes the heap structure, very basic since only 3 elements
        if (heap[1] < heap[2]) {
            swap(1, 2);
            heapify();
        } else if (heap[1] < heap[3]) {
            swap(1, 3);
            heapify();
        }
    }

    public void replaceMax(int newMax) {    // Replaces the top element and checks heap
        heap[1] = newMax;
        heapify();
    }

    public void insert(int element) {   // Insert element at bottom and check heap
        if (size<maxSize) {
            this.size++;
            heap[size] = element;
            heapify();
        } else {
            System.out.print("Can't exceed max size!");
        }       
    }
    
    public int getMax() {
        return heap[1];
    }

    public int[] getSorted() {  // Check through the heap to find which order to put elements in
        int[] list = new int[3];
        list[2] = heap[1];
        if (heap[2] < heap[3]) {
            list[0] = heap[2];
            list[1] = heap[3];
        } else {
            list[0] = heap[3];
            list[1] = heap[2];
        }

        return list;
    }

    public void print() 
    { 
        System.out.println(heap[1] + " " + heap[2] + " " + heap[3]);  
    } 

    public static void main(String[] arg) {
        Alg1_1 maxheap = new Alg1_1();
        // Generate array of random integers between 0 and 50
        Random rd = new Random();
        int[] arr = new int[20];
        System.out.println("Original array: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(50) + 1;
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        // Initialize the heap with the first 3 values
        maxheap.insert(arr[0]);
        maxheap.insert(arr[1]);
        maxheap.insert(arr[2]);

        // Check through heap if the max value is bigger than array value, replace if needed
        for (int i = 3; i < arr.length; i++) {
            if (arr[i] < maxheap.getMax()) {
                maxheap.replaceMax(arr[i]);
            }
        }
        int[] result = maxheap.getSorted();

        System.out.println("Three smallest elements: ");
        for (int i : result) {
            System.out.print(i +  " ");
        }
        System.out.println("");
    } 

}
