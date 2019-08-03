package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iwant
 * @date 19-6-25 19:06
 * @desc
 */
public class P225 {

}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

class MyStack {

    private List<Integer> list = new ArrayList<>();

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        list.add(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return list.remove(list.size() - 1);
    }

    /**
     * Get the top element.
     */
    public int top() {
        return list.get(list.size() - 1);
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return list.size() == 0;
    }
}


