import java.util.Stack;

/**
 * Created on 6/25/16.
 * Design a stack that supports push, pop, top, and
 * retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */

public class MinStack {

    /** initialize your data structure here. */
    public MinStack() {
        numStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        numStack.push(x);
        if (minStack.isEmpty() || x <= getMin()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (numStack.isEmpty()) return;
        int x = numStack.pop();
        if (x == getMin()) {
            minStack.pop();
        }
    }

    public int top() {
        return numStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    private Stack<Integer> numStack;
    private Stack<Integer> minStack;
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
