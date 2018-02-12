package other;

import java.util.LinkedList;

/**
 * url :: https://leetcode.com/problems/valid-parentheses/description/
 *
 * @author kissx on 2018/2/12.
 */
public class P20 {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        char[] chars = {'(', '{', '['};
        LinkedList<Character> stack = new LinkedList<>();
        int count = 0;
        while (count < s.length())
            if (find(chars, s.charAt(count))) {
                stack.addLast(s.charAt(count++));
                while (stack.size() > 0 && count < s.length()) {
                    char c = s.charAt(count++);
                    if (!find(chars, c)) {
                        char tChar = stack.removeLast();
                        if (c == ')' && tChar != '(')
                            return false;
                        if (c == '}' && tChar != '{')
                            return false;
                        if (c == ']' && tChar != '[')
                            return false;
                    } else
                        stack.addLast(c);
                }
                if (stack.size() > 0)
                    return false;
            } else
                return false;
        return true;
    }

    private boolean find(char[] chars, char c) {
        for (char aChar : chars) {
            if (aChar == c)
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        P20 p20 = new P20();
        System.out.println(p20.isValid("()"));
        System.out.println(p20.isValid(")"));
        System.out.println(p20.isValid("("));
        System.out.println(p20.isValid("([()]{})"));
        System.out.println(p20.isValid("[])"));
        System.out.println(p20.isValid("[](){}"));
    }
}
