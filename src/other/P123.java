package other;

public class P123 {

    public int maxProfit(int[] prices) {
        if (null == prices || 0 == prices.length || 1 == prices.length) {
            return 0;
        }
        int max1 = 0;
        int max2 = 0;
        int last = 0;
        // 1）
        int[] array = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            array[i - 1] = diff;
            if (diff > 0) {
                last += diff;
                if (i == prices.length - 1) {
                    if (last > max1) {
                        max2 = max1;
                        max1 = last;
                    } else if (last > max2) {
                        max2 = last;
                    }
                }
            } else {
                if (last != 0) {
                    if (last > max1) {
                        max2 = max1;
                        max1 = last;
                    } else if (last > max2) {
                        max2 = last;
                    }
                    last = 0;
                }
            }
        }
        int result = max1 + max2;
        if (0 == result) {
            return 0;
        }
        // 2）
        int startIndex, endIndex, count = 0;
        max1 = 0;
        last = 0;
        for (int i = 0; i < array.length; i++) {
            last += array[i];
            if (last < 0) {
                last = 0;
                count = 0;
            } else {
                ++count;
                if (last > max1) {
                    max1 = last;
                    startIndex = i - count + 1;
                    endIndex = i;
                    result = Math.max(result, max1 + helper(array, startIndex, endIndex));
                }
            }
        }
        return result;
    }

    private int helper(int[] array, int startIndex, int endIndex) {
        int max = 0;
        int last = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == startIndex) {
                i = endIndex;
                last = 0;
                continue;
            }
            last += array[i];
            if (last < 0) {
                last = 0;
            } else {
                if (last > max) {
                    max = last;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        P123 p123 = new P123();
        System.out.println(p123.maxProfit(array));
    }

}
