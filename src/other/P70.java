package other;

public class P70 {

    public int climbStairs(int n) {
        int cNum = 1;
        int aNum = 2;
        if (n == 1)
            return cNum;
        else {
            int temp;
            for (int i = 2; i <= n; i++) {
                temp = aNum;
                aNum += cNum;
                cNum = temp;
            }
            return cNum;
        }
    }

    public static void main(String[] args) {
        P70 p70 = new P70();
        System.out.println(p70.climbStairs(3));
    }

}
