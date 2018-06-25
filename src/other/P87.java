package other;

import java.util.HashMap;
import java.util.Map;

public class P87 {

//    public boolean isScramble(String s1, String s2) {
//        return helper(s1, s2);
//    }

    /* // brute force (且只考虑了中间劈法)
    private static boolean helper(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        if (s1.length() == 1)
            return s1.charAt(0) == s2.charAt(0);
        if (s1.length() == 2)
            return s1.equals(s2) || s1.equals(new StringBuilder(s2).reverse().toString());
        int length = s1.length();
        // 左子树少,右子树多
        String left1 = s1.substring(0, length / 2);
        String right1 = s1.substring(length / 2, length);
        String left2 = s2.substring(0, length / 2);
        String right2 = s2.substring(length / 2, length);
        if (helper(left1, left2) && helper(right1, right2))
            return true;
        String str = left1;
        left1 = right1;
        right1 = str;
        left2 = s2.substring(0, left1.length());
        right2 = s2.substring(left1.length(), s2.length());
        if (helper(left1, left2) && helper(right1, right2))
            return true;
        // 左子树多,右子树少
        if (length % 2 != 0) {
            left1 = s1.substring(0, length / 2 + 1);
            right1 = s1.substring(length / 2 + 1, length);
            left2 = s2.substring(0, length / 2 + 1);
            right2 = s2.substring(length / 2 + 1, length);
            if (helper(left1, left2) && helper(right1, right2))
                return true;
            str = left1;
            left1 = right1;
            right1 = str;
            left2 = s2.substring(0, left1.length());
            right2 = s2.substring(left1.length(), s2.length());
            return helper(left1, left2) && helper(right1, right2);
        }
        return false;
    }
    // */


    public boolean isScramble(String s1, String s2) {
        Map<String, Boolean> recordMap = new HashMap<>();
        return helper(s1, s2, recordMap);
    }

    /*
    // TLE
    private boolean helper(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        } else {
            if (s1.length() == 1) {
                return s1.charAt(0) == s2.charAt(0);
            } else if (s1.length() == 2) {
                return (s1.charAt(0) == s2.charAt(0) && s1.charAt(1) == s2.charAt(1)) ||
                        (s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0));
            } else {
                for (int i = 1; i < s1.length(); i++) {
                    // 未扰乱
                    String s1b = s1.substring(0, i);
                    String s1e = s1.substring(i);
                    String s2b = s2.substring(0, i);
                    String s2e = s2.substring(i);
                    if (helper(s1b, s2b) && helper(s1e, s2e))
                        return true;
                    // 扰乱
                    s2b = s2.substring(s2.length() - i);
                    s2e = s2.substring(0, s2.length() - i);
                    if (helper(s1b, s2b) && helper(s1e, s2e))
                        return true;
                }
                return false;
            }
        }
    }
    // */

    private boolean helper(String s1, String s2, Map<String, Boolean> recordMap) {
        if (s1.length() != s2.length()) {
            return false;
        } else {
            if (s1.length() == 1) {
                return s1.charAt(0) == s2.charAt(0);
            } else if (s1.length() == 2) {
                return (s1.charAt(0) == s2.charAt(0) && s1.charAt(1) == s2.charAt(1)) ||
                        (s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0));
            } else {
                if (recordMap.get(s1 + "&" + s2) != null)
                    return recordMap.get(s1 + "&" + s2);
                for (int i = 1; i < s1.length(); i++) {
                    // 未扰乱
                    String s1b = s1.substring(0, i);
                    String s1e = s1.substring(i);
                    String s2b = s2.substring(0, i);
                    String s2e = s2.substring(i);
                    if (helper(s1b, s2b, recordMap) && helper(s1e, s2e, recordMap)) {
                        recordMap.put(s1 + "&" + s2, true);
                        return true;
                    } else
                        recordMap.put(s1 + "&" + s2, false);
                    // 扰乱
                    s2b = s2.substring(s2.length() - i);
                    s2e = s2.substring(0, s2.length() - i);
                    if (helper(s1b, s2b, recordMap) && helper(s1e, s2e, recordMap)) {
                        recordMap.put(s1 + "&" + s2, true);
                        return true;
                    } else
                        recordMap.put(s1 + "&" + s2, false);
                }
                return false;
            }
        }
    }


    public static void main(String[] args) {
        P87 p87 = new P87();
//        System.out.println(p87.isScramble("abcd", "acdb"));
//        System.out.println(p87.isScramble("great", "rgeat"));
//        System.out.println(p87.isScramble("great", "rgtae"));
//        System.out.println(p87.isScramble("abcde", "caebd"));
//        System.out.println(p87.isScramble("abb", "bab"));
        System.out.println(p87.isScramble("abcdefghijklmnopq", "efghijklmnopqcadb"));
    }
}
