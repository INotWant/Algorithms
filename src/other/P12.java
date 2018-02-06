package other;

/**
 * url :: https://leetcode.com/problems/integer-to-roman/description/
 *
 * @author kissx on 2018/2/5.
 */
public class P12 {

    private static final char THOUSAND = 'M';
    private static final char HUNDRED_5 = 'D';
    private static final char HUNDRED = 'C';
    private static final char TEN_5 = 'L';
    private static final char TEN = 'X';
    private static final char FIVE = 'V';
    private static final char ONE = 'I';

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int t = num / 1000;
        if (t > 0) {
            for (int i = 1; i <= t; i++)
                sb.append(THOUSAND);
            num -= t * 1000;
        }
        t = num / 100;
        if (t > 0) {
            if (t == 9)
                sb.append(HUNDRED).append(THOUSAND);
            else if (t >= 5) {
                sb.append(HUNDRED_5);
                for (int i = 5; i < t; i++)
                    sb.append(HUNDRED);
            } else if (t == 4)
                sb.append(HUNDRED).append(HUNDRED_5);
            else {
                for (int i = 0; i < t; i++)
                    sb.append(HUNDRED);
            }
            num -= t * 100;
        }
        t = num / 10;
        if (t > 0) {
            if (t == 9)
                sb.append(TEN).append(HUNDRED);
            else if (t >= 5) {
                sb.append(TEN_5);
                for (int i = 5; i < t; i++)
                    sb.append(TEN);
            } else if (t == 4)
                sb.append(TEN).append(TEN_5);
            else {
                for (int i = 0; i < t; i++)
                    sb.append(TEN);
            }
            num -= t * 10;
        }
        t = num;
        if (t > 0) {
            if (t == 9)
                sb.append(ONE).append(TEN);
            else if (t >= 5) {
                sb.append(FIVE);
                for (int i = 5; i < t; i++)
                    sb.append(ONE);
            } else if (t == 4)
                sb.append(ONE).append(FIVE);
            else {
                for (int i = 0; i < t; i++)
                    sb.append(ONE);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(intToRoman(i));
        }
        System.out.println(intToRoman(11));
        System.out.println(intToRoman(14));
        System.out.println(intToRoman(15));
        System.out.println(intToRoman(19));
        System.out.println(intToRoman(20));
        System.out.println(intToRoman(89));
        System.out.println(intToRoman(3999));
    }

}
