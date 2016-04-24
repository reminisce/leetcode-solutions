/**
  * Given a nested list of integers, implement an iterator to flatten it.
  * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
  * Example 1:
  * Given the list [[1,1],2,[1,1]],
  * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
  * Example 2:
  * Given the list [1,[4,[6]]],
  * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
  */


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */

/**
 * Solution:
 * View the list as a tree, with each list type as internal node, and
 * integer type as leaf nodes.
 * Push each element in nestedList from end to begin into a stack.
 * When calling hasNext, check the top of the stack:
 * 1. If it's an integer, return true;
 * 2. If it's not an integer, pop it out, and push its getList elements
 *    into the stack from end to begin. Do this while stack is not empty.
 *    If any top of the stack is an integer, return true.
 * When calling next(), pop the stack, and return its top's integer.
 */
class NestedIterator {
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        if (nestedList.empty()) {
            return;
        }
        for (int i = (int)nestedList.size()-1; i >= 0; --i) {
            m_nestedIntergerStack.push(&nestedList[i]);
        }
    }

    int next() {
        const NestedInteger* curNestedInteger = m_nestedIntergerStack.top();
        m_nestedIntergerStack.pop();
        return curNestedInteger->getInteger();
    }

    bool hasNext() {
        while (!m_nestedIntergerStack.empty()) {
            const NestedInteger* curNestedInteger = m_nestedIntergerStack.top();
            if (curNestedInteger->isInteger()) {
                return true;
            }
            m_nestedIntergerStack.pop();
            const vector<NestedInteger>& nestedList = curNestedInteger->getList();
            for (int i = (int)nestedList.size()-1; i >= 0; --i) {
                m_nestedIntergerStack.push(&nestedList[i]);
            }
        }
        return false;
    }

private:
    stack<const NestedInteger*> m_nestedIntergerStack;
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
