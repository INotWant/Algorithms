package other;

/**
 * url :: https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * @author kissx on 2018/2/1.
 */
public class P5 {

    private static Date[][] array;

    private static class Date {
        int start1;
        int end1;
        int start2;
        int end2;

        Date(int start1, int end1, int start2, int end2) {
            this.start1 = start1;
            this.end1 = end1;
            this.start2 = start2;
            this.end2 = end2;
        }
    }

    // Longest common substring (1) :: O(n^3)
    /*
    private static Date helper(String strA, String strB, int pos1, int pos2) {
        if (array[pos1 + 1][pos2 + 1] != null)
            return array[pos1 + 1][pos2 + 1];
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        if (pos1 >= 0 && pos2 >= 0 && strA.charAt(pos1) == strB.charAt(pos2)) {

//            Date date = helper(strA, strB, pos1 - 1, pos2 - 1);
//            if (date == null)
//                d3 = new Date(pos1, pos1, pos2, pos2);
//            else if (date.end1 + 1 == pos1 && date.end2 + 1 == pos2)
//                d3 = new Date(date.start1, ++date.end1, date.start2, ++date.end2);
//            else {
//                int length = 1;
//                int p = pos1 > pos2 ? pos2 : pos1;
//                for (int i = 1; i <= p; i++) {
//                    if (strA.charAt(pos1 - i) != strB.charAt(pos2 - i))
//                        break;
//                    ++length;
//                }
//                if (length < date.end1 - date.start1 + 1)
//                    d3 = date;
//                else
//                    d3 = new Date(pos1 - length + 1, pos1, pos2 - length + 1, pos2);
//            }

            int length = 1;
            int p = pos1 > pos2 ? pos2 : pos1;
            for (int i = 1; i <= p; i++) {
                if (strA.charAt(pos1 - i) != strB.charAt(pos2 - i))
                    break;
                ++length;
            }
            d3 = new Date(pos1 - length + 1, pos1, pos2 - length + 1, pos2);
        }

        if (pos1 >= 0)
            d1 = helper(strA, strB, pos1 - 1, pos2);
        if (pos2 >= 0)
            d2 = helper(strA, strB, pos1, pos2 - 1);

        int size1 = d1 == null ? -1 : d1.end1 - d1.start1 + 1;
        int size2 = d2 == null ? -1 : d2.end1 - d2.start1 + 1;
        int size3 = d3 == null ? -1 : d3.end1 - d3.start1 + 1;
        if (size3 > size1 && size3 > size2) {
            array[pos1 + 1][pos2 + 1] = d3;
            return d3;
        }
        if (size1 > size2 && size1 > size3) {
            array[pos1 + 1][pos2 + 1] = d1;
            return d1;
        }
        array[pos1 + 1][pos2 + 1] = d2;
        return d2;
    }
    // */

    // Longest common substring (2) :: O(n^2)
    /*
    private static void newHelper(String strA, String strB) {
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    if (array[i][j] == null)
                        array[i + 1][j + 1] = new Date(i, i, j, j);
                    else {
                        Date date = array[i][j];
                        array[i + 1][j + 1] = new Date(date.start1, i, date.start2, j);
                    }
                }
            }
        }
    }
    // */

    private static Date newHelper(String strA, String strB) {
        int size = 0;
        Date[][] array = new Date[strA.length() + 1][strA.length() + 1];
        Date result = new Date(0, 0, 0, 0);
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    if (array[i][j] == null) {
                        array[i + 1][j + 1] = new Date(i, i, j, j);
                    } else {
                        Date date = array[i][j];
                        date = new Date(date.start1, i, date.start2, j);
                        array[i + 1][j + 1] = date;
                        if (strA.length() - date.start1 - 1 == date.end2) {
                            if (date.end1 - date.start1 + 1 > size) {
                                result = date;
                                size = date.end1 - date.start1 + 1;
                            }
                        }

                        /*
                        if (date.end1 - date.start1 + 1 > size) {
                            String temp = strA.substring(date.start1, date.end1 + 1);
                            if (temp.equals(new StringBuilder(temp).reverse().toString())) {
                                result = date;
                                size = date.end1 - date.start1 + 1;
                            }
                        }
                        */

                    }
                }
            }
        }
        return result;
    }

    public static String longestPalindrome(String s) {
        String sReverse = new StringBuilder(s).reverse().toString();
        if (s.equals(sReverse))
            return s;

        // Error 1
        /*
        array = new Date[s.length() + 1][s.length() + 1];
        Date date = helper(s, sReverse, s.length() - 1, s.length() - 1);
        if (date == null)
            return String.valueOf(s.charAt(0));
        else {
            if (date.start1 <= date.start2 || date.start2 <= date.start1)
                return s.substring(date.start1, date.end1 + 1);
            return String.valueOf(s.charAt(0));
        }
        // */

        Date date = newHelper(s, sReverse);
        return s.substring(date.start1, date.end1 + 1);
    }

    public static void main(String[] args) {
//        String s = "babad";
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String s = "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
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
