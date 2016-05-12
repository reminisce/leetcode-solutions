/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
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

class Solution {
public:
    int depthSum(vector<NestedInteger>& nestedList) {
        int sum = 0;
        for (const NestedInteger& ni : nestedList) {
            sum += getSum(ni, 1);
        }
        return sum;
    }

    int getSum(NestedInteger ni, int level) {
        int sum = 0;
        if (ni.isInteger()) {
            sum += ni.getInteger() * level;
        } else {
            const vector<NestedInteger>& niList = ni.getList();
            for (const NestedInteger& nestedInteger : niList) {
                sum += getSum(nestedInteger, level + 1);
            }
        }

        return sum;
    }
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

    Solution sol;
    int sum = sol.depthSum(nestedIntegerList);

    cout << "Weighted nested integers sum is " << sum << endl;

    return 0;
}
