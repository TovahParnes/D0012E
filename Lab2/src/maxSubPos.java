import java.util.Random;

public class maxSubPos {

    // Product stored in arr[0] of output
    static double[] maxPos(double[] input, int start) {
        double[] maxSub = new double[input.length+1];
        double[] nextSub = new double[input.length+1];
        maxSub[0] = 1;
        if (input.length == 1 || start == input.length-1) {
            maxSub[0] = input[start];
            maxSub[1] = input[start];
            return input;
        }
        for (int i = start; i < input.length; i++) {
            if (maxSub[0]*input[i] < maxSub[0]) {
                nextSub = maxPos(input, i+1);
                break;
            } else {
                maxSub[i+1] = input[i];
                maxSub[0] *= input[i];
            }
        }
        return (maxSub[0] > nextSub[0]) ? maxSub : nextSub;
    }
    // for i=start i<a.length i++
    //     if subarray*a[i] < subarray
    //         if(subarray > globalMax)
    //             globalMax = subarray
    //         nextSub = maxSubPos(a[i:])
    //         break
    //     else
    //         subarray *= a[i]
    // return max(subarray[0], nextSub[0]) [1:]
    
    public static void main(String[] args) {
        Random rand = new Random();
        double[] input = new double[16];
        System.out.println("Input array is: ");

        for (int i=0; i<input.length; i++) {
            input[i] = rand.nextDouble()*10;
            System.out.printf("%.2f  ", input[i]);
        }
        System.out.println("");

        double[] out = maxPos(input,0);
        System.out.println("Maximum Subarray: " + out[0]);

        for (int i = 1; i < out.length; i++) {
            if (out[i] != 0) {
                System.out.printf("%.2f  ", out[i]); 
            }       
        }
        System.out.println("");
        
    }
}
