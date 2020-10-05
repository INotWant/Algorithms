package other;

public class _leetcode_7_1 {

    public int minCount(int[] coins) {
        int sum = 0;
        for (int i = 0; i < coins.length; i++) {
            sum += coins[i] / 2 + coins[i] % 2;
        }
        return sum;
    }

}
