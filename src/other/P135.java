package other;

public class P135 {
    // 方法一
    /*
    public int candy(int[] ratings) {
        if (null == ratings || 0 == ratings.length) {
            return 0;
        }
        int[] array = new int[ratings.length];
        Arrays.fill(array, 1);
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < ratings.length; i++) {
                if (i != 0 && ratings[i] > ratings[i - 1] && array[i] <= array[i - 1]) {
                    array[i] = array[i - 1] + 1;
                    flag = true;
                }
                if (i != ratings.length - 1 && ratings[i] > ratings[i + 1] && array[i] <= array[i + 1]) {
                    array[i] = array[i + 1] + 1;
                    flag = true;
                }
            }
        }
        int sum = 0;
        for (int n : array) {
            sum += n;
        }
        return sum;
    }
    // */

    // 方法二
    public int candy(int[] ratings) {
        int length = ratings.length;
        int[] ans = new int[length];
        ans[0] = 1;
        // 从前往后，保前
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                ans[i] = ans[i - 1] + 1;
            } else {
                ans[i] = 1;
            }
        }
        // 从后往前，保后
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                ans[i] = Math.max(ans[i], ans[i + 1] + 1);
            }
        }
        int res = 0;
        for (int a : ans
        ) {
            res += a;
        }
        return res;
    }

    public static void main(String[] args) {
        P135 p135 = new P135();
        int[] ratings = {1, 2, 2};
        System.out.println(p135.candy(ratings));
    }

}
