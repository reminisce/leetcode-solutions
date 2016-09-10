/**
 * Created on 8/14/16.
 * Given a nested list of integers represented
 * as a string, implement a parser to deserialize it.
 *
 * Each element is either an integer, or a list -- whose
 * elements may also be integers or other lists.
 *
 * Note: You may assume that the string is well-formed:
 *
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 * Example 1:
 *
 * Given s = "324",
 *
 * You should return a NestedInteger object
 * which contains a single integer 324.
 * Example 2:
 *
 * Given s = "[123,[456,[789]]]",
 *
 * Return a NestedInteger object containing a
 * nested list with 2 elements:
 *
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * i.  An integer containing value 456.
 * ii. A nested list with one element:
 * a. An integer containing value 789.
 */

import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

public class MiniParser {

    public static void main(String[] args) {
        String s = "[1,[2,3],4,[5,[6]]]";
        MiniParser app = new MiniParser();
        NestedInteger ni = app.deserialize(s);
        System.out.println(app.serialize(ni));
    }

    public NestedInteger deserialize(String s) {
        int i = 0;
        Stack<NestedInteger> nestedIntegerStack = new Stack<>();
        while (i < s.length()) {
            if (s.charAt(i) == '[') {
                nestedIntegerStack.push(new NestedInteger());
                ++i;
            } else if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-') {
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) ++j;
                int val = Integer.parseInt(s.substring(i, j));
                NestedInteger ni = new NestedInteger(val);
                if (!nestedIntegerStack.isEmpty()) nestedIntegerStack.peek().add(ni);
                else nestedIntegerStack.push(ni);
                i = j;
            } else if (s.charAt(i) == ']') {
                if (nestedIntegerStack.size() > 1) {
                    NestedInteger ni = nestedIntegerStack.pop();
                    nestedIntegerStack.peek().add(ni);
                }
                ++i;
            } else {
                ++i;
            }
        }
        return nestedIntegerStack.peek();
    }

    public String serialize(NestedInteger ni) {
        if (ni.isInteger()) return ni.getInteger().toString();
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < ni.getList().size(); ++i) {
            serializeHelper(ni.getList().get(i), i==ni.getList().size()-1, str);
        }
        return str.toString();
    }

    private void serializeHelper(NestedInteger ni, boolean isLast, StringBuilder str) {
        if (null == ni) return;
        if (ni.isInteger()) {
            str.append(ni.getInteger().toString());
        } else {
            str.append('[');
            for (int i = 0; i < ni.getList().size(); ++i) {
                serializeHelper(ni.getList().get(i), i==ni.getList().size()-1, str);
            }
        }
        str.append(isLast? ']' : ',');
    }
}
