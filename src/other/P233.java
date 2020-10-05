package other;

public class P233 {

    public static int countDigitOne(int n) {
        int sum = 0;

        long base = 1;
        while (n / base >= 1) {
            long last = base;
            base *= 10;
            sum += (n / base) * last;
            long tmp = n % base;
            if (tmp >= 2 * last)
                sum += last;
            else if (tmp > last - 1)
                sum += (tmp - last + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(1410065408));
        System.out.println(Integer.MAX_VALUE);
    }

}
