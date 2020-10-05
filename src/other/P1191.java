package other;

public class P1191 {

    public static int kConcatenationMaxSum(int[] arr, int k) {
        long tmp = 0;
        for (int num : arr)
            tmp += num;
        long max;
        max = Math.max(tmp * k, 0);
        if (k >= 3 && tmp > 0) {
            long rI;
            long tmpI = 0, maxI = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                tmpI += arr[i];
                maxI = Math.max(tmpI, maxI);
            }
            rI = maxI;
            tmpI = 0;
            maxI = 0;
            for (int value : arr) {
                tmpI += value;
                maxI = Math.max(tmpI, maxI);
            }
            rI += maxI;
            rI += ((k - 2) * tmp);
            max = Math.max(rI, max);
        }
        tmp = Math.max(arr[0], 0);
        max = Math.max(tmp, max);
        for (int i = 1; i < arr.length; i++) {
            tmp += arr[i];
            tmp = Math.max(tmp, 0);
            max = Math.max(max, tmp);
        }
        if (k >= 2) {
            for (int value : arr) {
                tmp += value;
                tmp = Math.max(tmp, 0);
                max = Math.max(max, tmp);
            }
        }
        return (int) (max % 1000000007);
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 4, 1, 4};
        int k = 4;
        System.out.println(kConcatenationMaxSum(arr, k));
    }

}
