package other;

/**
 * @author iwant
 * @date 2019-04-25 19:30
 */
public class P188 {

    // 借鉴 P123 的解题思路
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1 || k <= 0)
            return 0;
        int[] array = new int[prices.length - 1];
        int count = 0;
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            array[i] = prices[i + 1] - prices[i];
            if (array[i] > 0) {
                ++count;
                sum += array[i];
            }
        }
        if (k >= count)
            return sum;
        array = new int[k * 2];
        for (int i = 0; i < 2 * k; i++)
            if (i % 2 == 0)
                array[i] = Integer.MIN_VALUE;
        for (int price : prices) {
            for (int i = 0; i < 2 * k; i += 2) {
                if (i == 0) {
                    array[0] = Math.max(array[0], -price);
                    array[1] = Math.max(array[1], array[0] + price);
                } else {
                    array[i] = Math.max(array[i], array[i - 1] - price);
                    array[i + 1] = Math.max(array[i + 1], array[i] + price);
                }
            }
        }
        return array[k * 2 - 1];
    }

    public static void main(String[] args) {
        P188 p188 = new P188();
        int k = 2;
        int[] prices = {2, 4, 1};
        p188.maxProfit(k, prices);
    }

}
