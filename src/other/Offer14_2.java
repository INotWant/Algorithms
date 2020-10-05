package other;

public class Offer14_2 {

    public int cuttingRope(int n) {
        if (n <= 3)
            return n - 1;
        if (n % 3 == 0)
            return (int) quickPower(3, n / 3);
        if (n % 3 == 1)
            return (int) (quickPower(3, n / 3 - 1) * 4 % 1000000007);
        else
            return (int) (quickPower(3, n / 3) * 2 % 1000000007);
    }

    public long quickPower(int base, int index) {
        if (index == 0)
            return 1;
        if (index == 1)
            return base;
        if (index % 2 == 0) {
            long tmp = quickPower(base, index / 2) % 1000000007;
            return (tmp * tmp) % 1000000007;
        } else {
            long tmp = quickPower(base, (index - 1) / 2) % 1000000007;
            return (base * tmp * tmp) % 1000000007;
        }
    }

    public static void main(String[] args) {
        Offer14_2 offer14_2 = new Offer14_2();
        int n = 127;
        System.out.println(offer14_2.cuttingRope(n));
    }

}
