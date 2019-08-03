package other;

/**
 * @author iwant
 * @date 19-5-19 10:24
 */
public class P214 {

    // O(n^2) --> 超时
    /*
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1)
            return s;
        if (isPalindrome(s))
            return s;
        for (int i = s.length() - 1; i >= 0; i--)
            if (isPalindrome(new StringBuilder(s.substring(i)).reverse().toString() + s))
                return new StringBuilder(s.substring(i)).reverse().toString() + s;
        return null;
    }

    private boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i))
                return false;
        }
        return true;
    }

    // */

    // 1) 简单明了 --> 好思路
    /*
    public String shortestPalindrome(String s) {
        // c1 的使用减少了很多运算
        char[] c1 = new char[s.length()];
        char[] c2 = s.toCharArray();
        for (int i = 0; i < c1.length; i++)
            c1[i] = c2[c2.length - 1 - i];
        P:
        for (int i = 0; i < c1.length; i++) {
            for (int j = i; j < c1.length; j++)
                if (c1[j] != c2[j - i])
                    continue P;
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < i; j++)
                b.append(c1[j]);
            b.append(s);
            return b.toString();
        }
        return "";
    }
    // */

    // 2)
    /*
    public String shortestPalindrome(String s) {
        int j = 0;
        // 此循环可确定 [j, s.length-1] 一定不在最长前缀的范围
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) == s.charAt(j))
                j += 1;
        if (j == s.length())
            return s;
        String suffix = s.substring(j);
        // 增加，并且最终会收敛
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }
    // */

    /*
     * 计算失配表
     */
    private void getUnmatchTable(int[] unmatchTable, String templateStr) {
        for (int i = 2; i < unmatchTable.length; i++) {
            int pos = unmatchTable[i - 1];
            if (templateStr.charAt(pos) == templateStr.charAt(i - 1))
                unmatchTable[i] = pos + 1;
            else {
                do {
                    pos = unmatchTable[pos];
                    if (templateStr.charAt(pos) == templateStr.charAt(i - 1)) {
                        unmatchTable[i] = pos + 1;
                        break;
                    }
                } while (pos != 0);
            }
        }
    }

    // 借用 KMP 的失配表
    public String shortestPalindrome(String s) {
        String str = s + "#" + new StringBuilder(s).reverse().toString() + "#";
        int[] unmatchTable = new int[str.length()];
        getUnmatchTable(unmatchTable, str);
        int pos = unmatchTable[str.length() - 1];
        if (pos == s.length())
            return s;
        else
            return new StringBuilder(s.substring(pos)).reverse().toString() + s;
    }

    public static void main(String[] args) {
        P214 p214 = new P214();
        p214.shortestPalindrome("babbbabbaba");
    }
}
