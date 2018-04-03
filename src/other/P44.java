package other;

import java.util.HashMap;
import java.util.Map;

public class P44 {
    // TLE (1805/1805个通过测试用例。并且最后一个案例可以很快的通过，为什么还是TLE？WHY？) --> 突然通过
    public boolean isMatch(String s, String p) {
        int pCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c != '*') {
                ++pCount;
                sb.append(c);
            } else {
                if (i == 0 || p.charAt(i - 1) != '*')
                    sb.append(c);
            }
        }
        Map<String, Integer> saveMap = new HashMap<>();
        return helper(s, sb.toString(), pCount, saveMap) == 0;
    }

    private static int helper(String s, String p, int pCount, Map<String, Integer> saveMap) {
        if (pCount > s.length())
            return -1;
        if (s.length() == 0) {
            if (pCount == 0)
                return 0;
            else
                return -1;
        }
        if (p.length() == 0)
            return -1;
        if (saveMap.get(s + ":" + p) != null)
            return saveMap.get(s + ":" + p);
        char cS = s.charAt(0);
        char cP = p.charAt(0);
        if (cS == cP || cP == '?')
            return helper(s.substring(1), p.substring(1), pCount - 1, saveMap);
        else if (cP == '*') {
            if (p.length() == 1)
                return 0;
            int index = p.indexOf("?");
            String newP = p.substring(1);
            if (newP.contains("*") && newP.indexOf("*") < index)
                index = newP.indexOf("*") + 1;
            if (index < 0) {
                index = newP.indexOf("*");
                if (index != -1)
                    ++index;
                if (index < 0)
                    index = p.length();
            }
            String subStr = p.substring(1, index);
            int pos = s.indexOf(subStr);
            String tempStr = s;
            while (!"".equals(subStr) && pos >= 0) {
                s = s.substring(pos);
                int result = helper(s, p.substring(1), pCount, saveMap);
                if (result == 0) {
                    saveMap.put(tempStr + ":" + p, 0);
                    return 0;
                }
                s = s.substring(1);
                pos = s.indexOf(subStr);
            }
            int result = -1;
            if ("".equals(subStr)) {
                for (int i = 0; i < s.length(); i++) {
                    result = helper(s.substring(i), p.substring(1), pCount, saveMap);
                    if (result == 0)
                        break;
                }
            }
            saveMap.put(tempStr + ":" + p, result);
            return result;
        } else {
            saveMap.put(s + ":" + p, -1);
            return -1;
        }
    }

    public static void main(String[] args) {
        P44 p44 = new P44();
//        System.out.println(p44.isMatch("aa", "a"));
//        System.out.println(p44.isMatch("aa", "aa"));
//        System.out.println(p44.isMatch("aaa", "aa"));
//        System.out.println(p44.isMatch("aa", "*"));
//        System.out.println(p44.isMatch("aa", "a*"));
//        System.out.println(p44.isMatch("ab", "?*"));
//        System.out.println(p44.isMatch("aab", "c*a*b"));


//        System.out.println(p44.isMatch("aaabaaabaabababbabababaababbabbbbaaaaababbaabbbaab",
//                "*babbbb*aab**b*bb*aa*"));

//        System.out.println(p44.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
//                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));

//                System.out.println(p44.isMatch("bbbbba","*b*??*"));

        long start = System.currentTimeMillis();
        System.out.println(p44.isMatch("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*"));
        long end = System.currentTimeMillis();
        System.out.println("Total Time :: " + (end - start));

    }
}
