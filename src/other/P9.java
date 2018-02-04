package other;

/**
 * url :: https://leetcode.com/problems/palindrome-number/description/
 *
 * @author kissx on 2018/2/4.
 */
public class P9 {

    // space: O(1)
    /*
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int size = 1;
        int t = x;
        while (t / 10 > 0) {
            t = t / 10;
            ++size;
        }
        if (size == 1)
            return true;
        t = x;
        int c = size % 2 == 0 ? size / 2 : size / 2 + 1;
        for (int i = size; i > c; i--) {
            int hight = x / (int) Math.pow(10, i - 1);
            int low = t % 10;
            if (hight != low)
                return false;
            x = x - (int) Math.pow(10, i - 1) * hight;
            t = t / 10;
        }
        return true;
    }
    */

    // this can be accepted
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        String str1 = String.valueOf(x);
        String str2 = new StringBuilder(str1).reverse().toString();
        return str1.equals(str2);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(9999));
        System.out.println(isPalindrome(101));
    }

}
