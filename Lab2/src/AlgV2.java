public class AlgV2 {
    public double minMax(int[] arr) {
        //Base case if length is 2
        if (arr.length == 2) {
            double out = (double)arr[1]/arr[0];
            return out;
        }
        //Midpoint of array
        int mid = arr.length/2;

        //Split into left half
        int[] leftArr = new int[mid];
        for (int i = 0; i < mid; i++) {
            leftArr[i] = arr[i];
        }

        //Split into right half
        int[] rightArr = new int[mid];
        for (int i = mid; i < arr.length; i++) {
            rightArr[i-mid] = arr[i];
        }

        //Recursive calls
        double leftF = minMax(leftArr);
        double rightF = minMax(rightArr);

        //Find min in left half, max in right half
        int minL = min(leftArr);
        int maxR = max(rightArr);
        
        //Compare and output the biggest fraction
        double[] out = {leftF, rightF, (double)maxR/minL};
        return max(out);
    }

    //Help function to find max in array of double
    private double max(double[] arr) {
        double out = 0;
        for (double d : arr) {
            if (d > out) {
                out = d;
            }
        }
        return out;
    }

    //Help function to find max in array of int
    private int max(int[] arr) {
        int out = 0;
        for (int d : arr) {
            if (d > out) {
                out = d;
            }
        }
        return out;
    }

    //Help function to find min in array of int
    private int min(int[] arr) {
        int out = Integer.MAX_VALUE;
        for (int d : arr) {
            if (d < out) {
                out = d;
            }
        }
        return out;
    }

    public static void main(String args[]) {
        int arr[] = { 76, 43, 67, 33, 31, 56, 11, 81 };
        AlgV2 nice = new AlgV2();
        double out = nice.minMax(arr);
        System.out.println(out);
    }
}
