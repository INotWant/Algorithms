package other;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * @author kissx on 2017/11/2.
 */
public class P3 {

    class Solution {
        int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int cMax = 0;
            int max = cMax;
            int clearP = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                Integer n = map.get(c);
                if (n == null || clearP > map.get(c)) {
                    map.put(c, i);
                    ++cMax;
                } else {
                    max = cMax > max ? cMax : max;
                    clearP = map.get(c);
                    cMax = i - map.get(c);
                    map.put(c, i);
                }
            }
            return cMax > max ? cMax : max;
        }
    }

    // ---------------------- TEST ----------------------

    @Test
    public void test() {
        Solution solution = new Solution();
        int n1 = solution.lengthOfLongestSubstring("abcabcbb");
        int n2 = solution.lengthOfLongestSubstring("dvdf");
        int n3 = solution.lengthOfLongestSubstring("abba");
        int n4 = solution.lengthOfLongestSubstring("c");
        System.out.println("--------------- END ---------------");
    }

}
