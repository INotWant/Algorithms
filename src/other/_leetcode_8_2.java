package other;

public class _leetcode_8_2 {

    public static int countTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int t1 = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int t2 = arr[j];
                for (int k = j + 1; k < arr.length; k++) {
                    t2 ^= arr[k];
                    if (t1 == t2) {
                        ++count;
                    }
                }
                t1 ^= arr[j];
                if (t1 == 0) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 7};
        System.out.println(countTriplets(arr));
        arr = new int[]{1, 1, 1, 1, 1};
        System.out.println(countTriplets(arr));
        arr = new int[]{7, 11, 12, 9, 5, 2, 7, 17, 22};
        System.out.println(countTriplets(arr));
        arr = new int[]{1, 3, 5, 7, 9};
        System.out.println(countTriplets(arr));
    }


}
