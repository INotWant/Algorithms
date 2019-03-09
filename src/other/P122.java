package other;

public class P122 {

    public int maxProfit(int[] prices) {
        if (null == prices) {
            return 0;
        }
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                result += diff;
            }
        }
        return result;
    }
}
