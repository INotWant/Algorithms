package other;

import java.util.LinkedList;

/**
 * @author iwant
 * @date 19-6-26 20:23
 * @desc
 */
public class P227 {

    public int calculate(String s) {
        if (s == null)
            return 0;
        LinkedList<String> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                if (c == '*' || c == '/') {
                    while (s.charAt(++i) == ' ') ;
                    int start = i;
                    while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9')
                        ++i;
                    if (c == '*')
                        stack.push(String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(s.substring(start, i))));
                    else
                        stack.push(String.valueOf(Integer.parseInt(stack.pop()) / Integer.parseInt(s.substring(start, i))));
                    --i;
                } else {
                    if (c == '+' || c == '-')
                        stack.push(String.valueOf(c));
                    else {
                        int start = i;
                        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9')
                            ++i;
                        stack.push(s.substring(start, i));
                        --i;
                    }
                }
            }
        }
        int sum = Integer.parseInt(stack.removeLast());
        while (stack.size() > 0) {
            char f = stack.removeLast().charAt(0);
            int num = Integer.parseInt(stack.removeLast());
            if (f == '+')
                sum += num;
            else
                sum -= num;
        }
        return sum;
    }

    public static void main(String[] args) {
        P227 p227 = new P227();
        System.out.println(p227.calculate("0-2147483647"));
    }

}
