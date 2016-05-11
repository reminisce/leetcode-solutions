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

#include <vector>
#include <iostream>
#include <stack>

using namespace std;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
public:
    NestedInteger() : m_isInteger(true), m_integer(0) {}

    NestedInteger(bool isInteger, int integer = 0) : m_isInteger(isInteger), m_integer(integer) {}

    NestedInteger(const NestedInteger& ni) : m_nestedIntegerList(ni.getList()), m_isInteger(ni.isInteger()),
                                             m_integer(ni.getInteger()) {}

    // Return true if this NestedInteger holds a single integer, rather than a nested list.
    bool isInteger() const {
        return m_isInteger;
    }

    // Return the single integer that this NestedInteger holds, if it holds a single integer
    // The result is undefined if this NestedInteger holds a nested list
    int getInteger() const {
        return m_integer;
    }

    // Return the nested list that this NestedInteger holds, if it holds a nested list
    // The result is undefined if this NestedInteger holds a single integer
    const vector<NestedInteger>& getList() const {
        return m_nestedIntegerList;
    }

    vector<NestedInteger>& getList() {
        return m_nestedIntegerList;
    }

    void clear() {
        m_isInteger = true;
        m_nestedIntegerList.clear();
    }

    void push_back(const NestedInteger& ni) {
        m_isInteger = false;
        m_nestedIntegerList.push_back(ni);
    }

private:
    vector<NestedInteger> m_nestedIntegerList;
    bool m_isInteger;
    int m_integer;
};

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
            m_nestedIntegerStack.push(&nestedList[i]);
        }
    }

    int next() {
        const NestedInteger* curNestedInteger = m_nestedIntegerStack.top();
        m_nestedIntegerStack.pop();
        return curNestedInteger->getInteger();
    }

    bool hasNext() {
        while (!m_nestedIntegerStack.empty()) {
            const NestedInteger* curNestedInteger = m_nestedIntegerStack.top();
            if (curNestedInteger->isInteger()) {
                return true;
            }
            m_nestedIntegerStack.pop();
            const vector<NestedInteger>& nestedList = curNestedInteger->getList();
            for (int i = (int)nestedList.size()-1; i >= 0; --i) {
                m_nestedIntegerStack.push(&nestedList[i]);
            }
        }
        return false;
    }

private:
    stack<const NestedInteger*> m_nestedIntegerStack;
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */

int main()
{
    // {0, {1, 2}, 3, {4, {5, 6}}}
    vector<NestedInteger> nestedIntegerList;
    nestedIntegerList.push_back(NestedInteger(true, 0));

    nestedIntegerList.push_back(NestedInteger());
    nestedIntegerList.back().push_back(NestedInteger(true, 1));
    nestedIntegerList.back().push_back(NestedInteger(true, 2));

    nestedIntegerList.push_back(NestedInteger(true, 3));

    nestedIntegerList.push_back(NestedInteger());
    nestedIntegerList.back().push_back(NestedInteger(true, 4));

    NestedInteger ni;
    ni.push_back(NestedInteger(true, 5));
    ni.push_back(NestedInteger(true, 6));
    nestedIntegerList.back().push_back(ni);

    NestedIterator nit(nestedIntegerList);
    while (nit.hasNext()) {
        cout << nit.next() << ' ';
    }
    cout << endl;

    return 0;
}
