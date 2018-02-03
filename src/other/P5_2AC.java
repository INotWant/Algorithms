package other;

/**
 * @author kissx on 2018/2/3.
 */
public class P5_2AC {

    public static String longestPalindrome(String s) {
        int start = 0, end = 0;
        int size = 1;
        // odd
        for (int i = 1; i < s.length() - 1; i++) {
            int space = s.length() - i - 1 >= i ? i : s.length() - i - 1;
            int sizeTemp = 1;
            for (int j = 1; j <= space; j++) {
                if (s.charAt(i - j) != s.charAt(i + j))
                    break;
                sizeTemp += 2;
            }
            if (sizeTemp > size) {
                start = i - sizeTemp / 2;
                end = i + sizeTemp / 2;
                size = sizeTemp;
            }
        }
        // even
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1))
                continue;
            int space = s.length() - i - 2 >= i ? i : s.length() - i - 2;
            int sizeTemp = 2;
            for (int j = 1; j <= space; j++) {
                if (s.charAt(i - j) != s.charAt(i + 1 + j))
                    break;
                sizeTemp += 2;
            }
            if (sizeTemp > size) {
                start = i - (sizeTemp - 2) / 2;
                end = i + sizeTemp / 2;
                size = sizeTemp;
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        //        String s = "babad";
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String s = "abcda";
//        String s = "cbbd";
        long start = System.currentTimeMillis();
//        String s = "tattarrattat";
//        String s = "abcdasdfghjkldcba";
//        String s = "cbbd";
//        String s = "ccccccccccc";
//        String s = "babadada";
        String s = "abcdasdfghjkldcba";
        String result = longestPalindrome(s);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("Time :: " + (end - start));
    }

}
