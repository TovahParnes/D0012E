public class AlgV2 {
    // {leftMin, rightMax, rightMax/leftMin}
    public double minMax(int[] arr) {
        if (arr.length == 2) {
            double out = (double)arr[1]/arr[0];
            return out;
        }

        int mid = arr.length/2;

        int[] leftArr = new int[mid];
        for (int i = 0; i < mid; i++) {
            leftArr[i] = arr[i];
        }

        int[] rightArr = new int[mid];
        for (int i = mid; i < arr.length; i++) {
            rightArr[i-mid] = arr[i];
        }

        double leftF = minMax(leftArr);
        double rightF = minMax(rightArr);

        int minL = min(leftArr);
        int maxR = max(rightArr);

        double[] out = {leftF, rightF, (double)maxR/minL};
        return max(out);
    }

    private double max(double[] arr) {
        double out = 0;
        for (double d : arr) {
            if (d > out) {
                out = d;
            }
        }
        return out;
    }

    private int max(int[] arr) {
        int out = 0;
        for (int d : arr) {
            if (d > out) {
                out = d;
            }
        }
        return out;
    }

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
        int arr[] = { 50, 4, 99, 23, 65, 12, 46, 15 };
        AlgV2 nice = new AlgV2();
        double out = nice.minMax(arr);
        System.out.println(out);
    }
}
