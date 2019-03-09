package other;

public class P121 {

    public int maxProfit(int[] prices) {
        if (null == prices || 0 == prices.length) {
            return 0;
        }
        int curr = 0;
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int p = prices[i] - prices[i - 1];
            if (p >= 0) {
                curr += p;
                if (curr > result) {
                    result = curr;
                }
            } else if (curr + p > 0) {
                curr += p;
            } else {
                curr = 0;
            }

        }
        return result;
    }

}
