package other;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class P224 {

//    /**
//     * 自己的 AC
//     */
//    public static int calculate(String s) {
//        LinkedList<String> stack = new LinkedList<>();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != ' ')
//                sb.append(s.charAt(i));
//        }
//        s = sb.toString();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '(' || c == '+' || c == '-') {
//                stack.addFirst(String.valueOf(c));
//            } else {
//                if (c == ')') {
//                    sb = new StringBuilder(stack.pollFirst());
//                    stack.pollFirst();
//                } else {
//                    sb = new StringBuilder();
//                    while (i < s.length() && Character.isDigit(s.charAt(i))) {
//                        sb.append(s.charAt(i));
//                        ++i;
//                    }
//                    --i;
//                }
//                int num = Integer.parseInt(sb.toString());
//                while (stack.size() > 0) {
//                    if (stack.getFirst().equals("+")) {
//                        stack.pollFirst();
//                        num = Integer.parseInt(stack.pollFirst()) + num;
//                    } else if (stack.getFirst().equals("-")) {
//                        stack.pollFirst();
//                        num = Integer.parseInt(stack.pollFirst()) - num;
//                    } else
//                        break;
//                }
//                stack.addFirst(String.valueOf(num));
//            }
//        }
//        return Integer.parseInt(stack.get(0));
//    }

//    /**
//     * 这个思路清晰
//     */
//    public static int calculate(String s) {
//        Stack<Integer> stack = new Stack<>();
//        int sign = 1, res = 0;
//        int length = s.length();
//        for (int i = 0; i < length; i++) {
//            char ch = s.charAt(i);
//            if (Character.isDigit(ch)) {
//                int cur = ch - '0';
//                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
//                    cur = cur * 10 + s.charAt(++i) - '0';
//                res = res + sign * cur;
//            } else if (ch == '+') {
//                sign = 1;
//            } else if (ch == '-') {
//                sign = -1;
//            } else if (ch == '(') { // 关键
//                stack.push(res);
//                res = 0;
//                stack.push(sign);
//                sign = 1;
//            } else if (ch == ')') { // 关键
//                res = stack.pop() * res + stack.pop();
//            }
//        }
//        return res;
//    }

    public static int calculate(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) != ' ')
                sb.append(s.charAt(i));
        s = sb.toString();
        return evalRPN(toRPN(s));
    }

    private static String[] toRPN(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        List<String> rpn = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder tsb = new StringBuilder();
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    tsb.append(s.charAt(i));
                    ++i;
                }
                --i;
                rpn.add(tsb.toString());
            } else if (c == '(')
                stack.addLast(c);
            else if (c == ')') {
                while (stack.getLast() != '(')
                    rpn.add(String.valueOf(stack.removeLast()));
                stack.removeLast();
            } else {
                while (stack.size() > 0 && stack.getLast() != '(')
                    rpn.add(String.valueOf(stack.removeLast()));
                stack.addLast(c);
            }
        }
        while (stack.size() > 0)
            rpn.add(String.valueOf(stack.poll()));
        String[] result = new String[rpn.size()];
        rpn.toArray(result);
        return result;
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String curr = tokens[i];
            if ("+".equals(curr)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if ("-".equals(curr)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if ("*".equals(curr)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if ("/".equals(curr)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.add(Integer.parseInt(curr));
            }
        }
        return stack.get(0);
    }


    public static void main(String[] args) {
//        System.out.println(calculate("1"));
        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(calculate("2147483647"));
    }


}
