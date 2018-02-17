package other;

/**
 * url :: https://leetcode.com/submissions/detail/141136251/
 *
 * @author kissx on 2018/2/17.
 */
public class P28 {

    public int strStr(String haystack, String needle) {
        int result = -1;
        if (needle == null || needle.length() == 0)
            return 0;
        if (haystack == null || haystack.length() == 0)
            return result;
        char b = needle.charAt(0);
        for (int i = 0; i < haystack.length(); i++) {
            char a = haystack.charAt(i);
            if (a == b && haystack.length() - i >= needle.length()) {
                String str = haystack.substring(i, needle.length() + i);
                if (needle.equals(str)) {
                    result = i;
                    return result;
                }
            }
        }
        return result;
    }

}
