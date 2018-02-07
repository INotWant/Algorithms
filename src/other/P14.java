package other;

/**
 * url :: https://leetcode.com/problems/longest-common-prefix/description/
 *
 * @author kissx on 2018/2/7.
 */
public class P14 {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0)
            return "";
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int num = 0;
            int size = result.length() > str.length() ? str.length() : result.length();
            for (int j = 0; j < size; j++) {
                if (result.charAt(j) != str.charAt(j))
                    break;
                ++num;
            }
            if (num == 0)
                return "";
            result = result.substring(0, num);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"aaa", "aa", "ba"};
        System.out.println(longestCommonPrefix(strs));
    }

}
