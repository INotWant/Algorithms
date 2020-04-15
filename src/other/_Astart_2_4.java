package other;

import java.util.Scanner;

public class _Astart_2_4 {

    // TODO 未过
    // 估计原因 :: 求组合数出现问题
    // 求组合数的方法
    // 1) 利用递归式
    // 2) 利用乘法逆元
    // 3) 利用 分解成质因式
    // 4) 卢卡斯（用于求超大规模的素数求余数）

    private static final int VALUE = (int) (1e9 + 7);

    public static void main(String[] args) {
        make_p();
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int c = 0;
        int[] results = new int[count];
        while (c < count) {
            int len = scanner.nextInt();
            int[] as = new int[len * 2];
            for (int i = 0; i < len * 2; i++)
                as[i] = scanner.nextInt();
            long last = 1;
            int sum = 0;
            boolean lastStatus = true;
            for (int i = 0; i < as.length; i++) {
                int start = i;
                int currNum = as[i];
                int currCount;
                while (i < as.length && as[i] == currNum)
                    ++i;
                currCount = i - start;
                sum += currCount;
                boolean currStatus = (sum % 2) == 0;
                if (lastStatus && currStatus) {
                    last *= help(currCount, currCount / 2);
                } else if (!lastStatus && !currStatus) {
                    last *= (help(currCount, currCount / 2) + help(currCount, currCount / 2 - 1));
                } else {
                    last *= help(currCount, currCount / 2);
                    if (lastStatus)
                        last *= 2;
                }
                last %= VALUE;
                lastStatus = currStatus;
                --i;
            }
            results[c] = (int) last % VALUE;
            ++c;
        }
        for (int result : results)
            System.out.println(result);
    }

    private static final int MAX = 50000;
    private static int[] vis = new int[MAX + 1];
    private static int[] p = new int[MAX + 1];
    private static int tot = 0;

    // 挖素数
    private static void make_p() {
        vis[0] = vis[1] = 1;
        for (int i = 2; i <= MAX; i++)
            if (vis[i] == 0) {
                p[++tot] = i;
                for (int j = i * i; j > 0 && j <= MAX; j += i)
                    vis[j] = 1;
            }
    }

    // 快速幂
    private static long ksm(long a, long b) {
        long ans = 1, w = a;
        for (; b != 0; b >>= 1, w = (w * w) % VALUE)
            if ((b & 1) == 1)
                ans = (ans * w) % VALUE;
        return ans;
    }

    // 阶乘的标准分解式中素因数的指数
    private static long get(int x, int p) {
        long ans = 0;
        long rec = p;
        while (x >= rec) {
            ans += x / rec;
            rec *= p;
        }
        return ans;
    }

    private static int help(int x, int y) {
        if (x == 0 || y == 0)
            return 1;
        long ans = 1;
        for (int i = 1; i <= tot && p[i] <= x; i++) {
            long t = get(x, p[i]) - get(x - y, p[i]) - get(y, p[i]);
            ans = (ans * ksm(p[i], t)) % VALUE;
        }
        return (int) ans;
    }
}


// 方法一
// ERROR
    /*
    private static Map<Integer, Long> saveMap = new HashMap<>();

    private static long help(int big, int low) {
        if (low == 0)
            return 1;
        if (saveMap.get(low) == null) {
            long r = 1;
            for (int i = 2; i <= low; i++)
                r *= i;
            saveMap.put(low, r);
        }
        long d = saveMap.get(low);
        long m = 1;
        for (int i = 0; i < low; i++)
            m *= (big - i);
        return (m / d) % VALUE;
    }
    */

// 递归
// MLE
// 方法二
    /*
    private static Map<String, Integer> saveMap = new HashMap<>();

    private static int help(int big, int low) {
        if (big == 0 || low == 0)
            return 1;
        if (low > big)
            return 0;
        if (low == big)
            return 1;
        String key = big + "," + low;
        if (saveMap.get(key) != null)
            return saveMap.get(key);
        int result = (help(big - 1, low) + help(big - 1, low - 1)) % VALUE;
        saveMap.put(key, result);
        return result;
    }
    */

// 乘法逆元
// TLE
    /*
    private static long ksm(long a, long b) {
        long s = 1;
        while (b != 0) {
            if ((b & 1) != 0) {
                a %= VALUE;
                s %= VALUE;
                s *= a;
            }
            a %= VALUE;
            a *= a;
            b >>= 1;
        }
        return s % VALUE;
    }

    private static long help(long n, long m) {
        if (n == m || m == 0) return 1;
        long up = 1, down = 1;
        if (m > n - m) m = n - m;
        for (int i = 0; i < m; i++) {
            up = up * (n - i) % VALUE;
            down = down * (i + 1) % VALUE;
        }
        // (a/b)%mod=a*(b^(mod-2)) mod为素数
        return up * ksm(down, VALUE - 2) % VALUE;
    }
    */
