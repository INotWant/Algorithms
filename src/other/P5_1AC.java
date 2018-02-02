package other;

import java.util.Arrays;

/**
 * 基于最长公共子串（特殊处理为：注释1）
 * <p>
 * 此类对比 {@link P5} ，只是优化了中间结果的存储。然而却 AC 。唉，要被这题虐哭了！MMP
 */
public class P5_1AC {

    private static class Date {
        int start;
        int end;

        public Date(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Date newHelper(String strA, String strB) {
        int size = 0;
        int[][] array = new int[strA.length() + 1][strA.length() + 1];
        for (int[] anArray : array) {
            Arrays.fill(anArray, -1);
        }
        int end = 0;
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    if (array[i][j] == -1) {
                        array[i + 1][j + 1] = i;
                    } else {
                        int start = array[i][j];
                        array[i + 1][j + 1] = start;
                        // 注释1 --> 保证得到的最长串在 strA 中的坐标和 strB 中的坐标满足一定关系。
                        if (strA.length() - start - 1 == j) {
                            if (i - start + 1 > size) {
                                end = i;
                                size = i - start + 1;
                            }
                        }
                    }
                }
            }
        }
        return new Date(end - size + 1, end);
    }

    public static String longestPalindrome(String s) {
        String sReverse = new StringBuilder(s).reverse().toString();
        if (s.equals(sReverse))
            return s;
        Date date = newHelper(s, sReverse);
        if (date.start > date.end)
            return String.valueOf(s.charAt(0));
        return s.substring(date.start, date.end + 1);
    }

    public static void main(String[] args) {
//        String s = "babad";
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String s = "abcda";
//        String s = "cbbd";
        long start = System.currentTimeMillis();
//        String s = "tattarrattat";
//        String s = "abcdasdfghjkldcba";
//        String s = "cbbd";
//        String s = "ccccccccccc";
//        String s = "babadada";
//        String s = "abcdasdfghjkldcba";
        String result = longestPalindrome(s);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("Time :: " + (end - start));
    }
}