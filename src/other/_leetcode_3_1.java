package other;

public class _leetcode_3_1 {

    //T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
    public int tribonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;

        int t1 = 0;
        int t2 = 1;
        int t3 = 1;
        int curr = -1;
        for (int i = 3; i <= n; i++) {
            curr = t1 + t2 + t3;
            t1 = t2;
            t2 = t3;
            t3 = curr;
        }
        return curr;
    }

    public static void main(String[] args) {
        _leetcode_3_1 obj = new _leetcode_3_1();
        System.out.println(obj.tribonacci(25));

    }

}
