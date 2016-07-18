/*
Given a list of sorted arrays,
implement a MergeSortIterator
that iterates all the elements
in order.
*/

#include <queue>
#include <vector>
#include <iostream>

using namespace std;

struct PairIteratorComparator {
    typedef vector<int>::iterator iterator;
    typedef pair<iterator, iterator> iterator_pair;
    bool operator()(const iterator_pair& it1, const iterator_pair& it2) const {
        return *(it1.first) > *(it2.first);
    }
};

class MergeSortIterator
{
public:
    typedef PairIteratorComparator::iterator iterator;
    typedef PairIteratorComparator::iterator_pair iterator_pair;
    MergeSortIterator(vector<vector<int>>& arrays) {
        for (vector<int>& array : arrays) {
            if (!array.empty()) {
                m_heap.push(make_pair(array.begin(), array.end()));
            }
        }
    }

    bool hasNext() const {
        return !m_heap.empty();
    }

    int next() {
        iterator_pair p = m_heap.top();
        m_heap.pop();
        int num = *(p.first);
        if (++p.first != p.second) {
            m_heap.push(p);
        }
        return num;
    }

private:
    priority_queue<iterator_pair, vector<iterator_pair>, PairIteratorComparator> m_heap;
};

int main()
{
    vector<vector<int>> nums = {{1, 4, 6}, {2, 3}, {}, {7, 10}};
    MergeSortIterator it(nums);
    while (it.hasNext()) {
        cout << it.next() << ' ';
    }
    cout << endl;
    return 0;
}
