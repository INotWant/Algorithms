package other;

/**
 * url :: https://leetcode.com/problems/reverse-integer/description/
 *
 * @author kissx on 2018/2/3.
 */
public class P7 {

    public static int reverse(int x) {
        String str = String.valueOf(x);
        short falg = 1;
        if ('-' == str.charAt(0)) {
            str = str.substring(1, str.length());
            falg = -1;
        }
        long l = Long.parseLong(new StringBuilder(str).reverse().toString());
        l *= falg;
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE)
            return 0;
        else
            return (int) l;
    }

    public static void main(String[] args) {
        System.out.println(reverse(720));
        System.out.println(reverse(-17));
    }

}
