package other;

public class P204 {

    // 超时
    /*
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public int countPrimes(int n) {
        if (n <= 2)
            return 0;
        int count = 1;
        for (int i = 3; i < n; i++) {
            if (isPrime(i))
                ++count;
        }
        return count;
    }
    // */

    // 素数筛 or 埃氏筛
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++)
            isPrime[i] = true;
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i)
                isPrime[j] = false;
        }
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime[i]) count++;
        return count;
    }

    public static void main(String[] args) {
        P204 p204 = new P204();
        System.out.println(p204.countPrimes(1500000));
        System.out.println(p204.countPrimes(2));
        System.out.println(p204.countPrimes(4));
    }

}
