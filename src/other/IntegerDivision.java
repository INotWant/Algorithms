package other;

/**
 * @author kissx on 2017/9/21.
 *         <p>
 *         整数划分（二维递归）
 */
public class IntegerDivision {

    private static int integerDivision(int n, int m) {
        if (n == 1 || m == 1)
            return 1;
        else if (m > n)         // 健壮性
            return integerDivision(n, n);
        else if (m == n)
            return 1 + integerDivision(n,n-1);
        else
            return integerDivision(n, m - 1) + integerDivision(n - m, m);
    }

    private static int integerDivision(int n){
        if (n > 0)
            return integerDivision(n,n);
        throw new RuntimeException("Error!!!");
    }

    public static void main(String[] args) {
        System.out.println("6 划分的结果为：");
        System.out.println(integerDivision(6));
    }

}
