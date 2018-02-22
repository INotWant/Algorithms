package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * url :: https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * @author kissx on 2018/2/20.
 */
public class P32 {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        List<Integer> list = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c) {
                stack.addFirst(i);
            } else {
                if (stack.size() > 0) {
                    Integer pos = stack.removeFirst();
                    list.add(pos);
                    list.add(i);
                }
            }
        }
        Integer[] array = new Integer[list.size()];
        list.toArray(array);
        Arrays.sort(array);
        int result = 0;
        int start = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] - array[i - 1] > 1) {
                if (i - start > result)
                    result = i - start;
                start = i;
            }
        }
        result = result > array.length - start ? result : array.length - start;
        return result;
    }

    public static void main(String[] args) {
        P32 p32 = new P32();
        System.out.println(p32.longestValidParentheses("()((())()((("));
    }

}
