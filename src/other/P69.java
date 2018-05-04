package other;

public class P69 {

    public int mySqrt(int x) {
        return (int) helper(1, x, x);
    }

    private static long helper(long start, long end, int x) {
        long mid = (start + end) / 2;
        long value = mid * mid;
        if (value == x)
            return mid;
        else if (value < x) {
            if ((mid + 1) * (mid + 1) > x)
                return mid;
            return helper(mid + 1, end, x);
        } else {
            if ((mid - 1) * (mid - 1) < x)
                return mid - 1;
            return helper(start, mid - 1, x);
        }
    }

    public static void main(String[] args) {
        P69 p69 = new P69();
        System.out.println(p69.mySqrt(2147395599));
    }

}
