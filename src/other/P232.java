package other;

import java.util.Stack;

/**
 * @author iwant
 * @date 19-6-25 20:28
 * @desc
 */
public class P232 {


}


/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

class MyQueue {

    private Stack<Integer> stack = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {

    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        int[] num = new int[stack.size()];
        for (int i = 0; i < num.length; i++)
            num[i] = stack.pop();
        for (int i = num.length - 2; i >= 0; i--)
            stack.push(num[i]);
        return num[num.length - 1];
    }

    /**
     * Get the front element.
     */
    public int peek() {
        int[] num = new int[stack.size()];
        for (int i = 0; i < num.length; i++)
            num[i] = stack.pop();
        for (int i = num.length - 1; i >= 0; i--)
            stack.push(num[i]);
        return num[num.length - 1];
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack.size() == 0;
    }
}

