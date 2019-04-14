package other;

import java.util.LinkedList;

public class P150 {

    public int evalRPN(String[] tokens) {
        if (null == tokens || 0 == tokens.length) {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int n1, n2;
        for (String token : tokens)
            switch (token) {
                case "+":
                    n1 = stack.removeFirst();
                    n2 = stack.removeFirst();
                    stack.addFirst(n1 + n2);
                    break;
                case "-":
                    n2 = stack.removeFirst();
                    n1 = stack.removeFirst();
                    stack.addFirst(n1 - n2);
                    break;
                case "*":
                    n1 = stack.removeFirst();
                    n2 = stack.removeFirst();
                    stack.addFirst(n1 * n2);
                    break;
                case "/":
                    n2 = stack.removeFirst();
                    n1 = stack.removeFirst();
                    stack.addFirst(n1 / n2);
                    break;
                default:
                    stack.addFirst(Integer.parseInt(token));
                    break;
            }
        return stack.removeFirst();
    }

}
