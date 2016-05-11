/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by
next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18): The "Zigzag" order is not clearly
defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag"
with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/

#include <vector>
#include <queue>
#include <iostream>

using namespace std;

class ZigzagIterator {
public:
    typedef vector<int> vector_type;
    typedef vector<int>::iterator iterator_type;
    typedef pair<iterator_type, vector_type*> pair_type;

    ZigzagIterator(vector<int>& v1, vector<int>& v2) {
        if (!v1.empty()) {
            m_iteratorQueue.push(make_pair(v1.begin(), &v1));
        }
        if (!v2.empty()) {
            m_iteratorQueue.push(make_pair(v2.begin(), &v2));
        }
    }

    ZigzagIterator(vector<vector<int>>& vs) {
        for (vector<int>& v : vs) {
            if (!v.empty()) {
                m_iteratorQueue.push(make_pair(v.begin(), &v));
            }
        }
    }

    /**
     * Do not push iterator to queue if it's already the end of the vector
     */
    int next() {
        pair_type p = m_iteratorQueue.front();
        m_iteratorQueue.pop();
        int val = *(p.first);
        if (++(p.first) != p.second->end()) {
            m_iteratorQueue.push(p);
        }
        return val;
    }

    bool hasNext() {
        return !m_iteratorQueue.empty();
    }

private:
    queue<pair_type> m_iteratorQueue;
};

int main()
{
    vector<int> v1 = {1, 2, 3};
    vector<int> v2 = {4, 5, 6, 7};
    vector<int> v3 = {8, 9};

    vector<vector<int>> vs;
    vs.push_back(v1);
    vs.push_back(v2);
    vs.push_back(v3);

    ZigzagIterator zit(vs);

    while (zit.hasNext()) {
        cout << zit.next() << ' ';
    }
    cout << endl;

    return 0;
}
