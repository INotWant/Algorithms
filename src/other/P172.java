package other;

public class P172 {

    // 将问题转化为求 1~n 中 质因数5 的个数
    public int trailingZeroes(int n) {
        if (n == 0)
            return 0;
        int count = 0;
        // 求为 5^1、5^2、5^3... 倍数的有多少
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

}
