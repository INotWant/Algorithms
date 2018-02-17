package other;

/**
 * url :: https://leetcode.com/problems/divide-two-integers/description/
 *
 * @author kissx on 2018/2/17.
 */
public class P29 {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == 1)
            return Integer.MIN_VALUE;
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int result = 0;
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        long t = Math.abs(divisor);
        if (absDividend > 10000 && absDivisor < 10000) {
            int m = -1;
            for (int i = 10000; i > 0; i--) {
                m = isDivisible(i, absDivisor);
                if (m > 0) {
                    t = i;
                    break;
                }
            }
            int count = 0;
            while (absDividend > 0) {
                absDividend -= t;
                ++count;
            }
            if (absDividend < 0) {
                --count;
                absDividend += t;
            }
            if (m > 0)
                t = m;
            for (int i = 1; i <= count; ++i) {
                result += t;
            }
        }
        while (absDividend > 0) {
            absDividend -= absDivisor;
            if (result == Integer.MAX_VALUE)
                return result;
            ++result;
        }
        if (absDividend < 0)
            --result;
        if ((dividend >= 0 && divisor > 0) || (dividend < 0 && divisor < 0))
            return result;
        else
            return -result;
    }

    private static int isDivisible(int dividend, long divisor) {
        int count = 0;
        while (dividend > 0) {
            dividend -= divisor;
            ++count;
        }
        return dividend == 0 ? count : -1;
    }

    // new think --> 加速叠加（sum += sum，然后递归）

    public static void main(String[] args) {
        P29 p29 = new P29();
//        System.out.println(p29.divide(-5, 2));
//        System.out.println(p29.divide(-5, -2));
//        System.out.println(p29.divide(5, 2));
//        System.out.println(p29.divide(-1, -2));
//        p29.divide(2147483647, 2);
        long start = System.currentTimeMillis();
//        int result = p29.divide(-2147483648, -1);
        int result = p29.divide(-1010369383, -2147483648);
        long end = System.currentTimeMillis();
        System.out.println("result :: " + result);
        System.out.println("total time :: " + (end - start));
    }

}
