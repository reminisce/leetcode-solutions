/*
(G)
Implement a interval tree class
which supports:
1. Insert an interval
2. Remove an interval
3. Given an interval x, find if x overlaps
with any of the existing intervals.

Solution: http://www.geeksforgeeks.org/interval-tree/
*/

struct Interval {
    Interval() : low(0), high(0) {}
    Interval(int l, int h) : low(l), high(h) {}
    bool overlaps(const Interval& interval) {
        return !(low > interval.high || high < interval.low);
    }
    int low;
    int high;
};

struct IntervalTreeNode {
    IntervalTreeNode(int l, int h)
    : left(nullptr), right(nullptr), maxHigh(h) {
        interval.low = l;
        interval.high = h;
    }

    IntervalTreeNode(const Interval& i) : left(nullptr), right(nullptr),
    interval(i), maxHigh(i.high) {}
    IntervalTreeNode* left;
    IntervalTreeNode* right;
    Interval interval;
    // max high value of all the
    // nodes rooted with the current node
    int maxHigh;
};

/**
 * Interval tree's nodes are ordered
 * by their intervals' low value in
 * terms of binary search tree.
 * Each node should keep a maxHigh
 * as the greatest high value among
 * all the interval nodes rooted with
 * this node including itself.
 * To check whether an given interval
 * overlaps with any of the intervals,
 * 1. If the root has the left child and
 * the left child's maxHigh is greater than
 * the given interval's low, recurse to
 * the left subtree.
 * 2. Else, recurse to the right subtree.
 * We need to prove it works by proving that
 * after recursing to the left subtree and
 * find no overlapping interval nodes, it's
 * guaranteed that there are no overlapping
 * intervals in the right subtree either.
 * Proof: Assume after recursing into the
 * left subtree find no overalpping intervals,
 * let's say the interval with the maxHigh is
 * [curLow, maxHigh], the given interval.high <
 * curLow <= maxHigh. Since all the nodes' low
 * are greater than curLow, the given interval
 * cannot overlap with any of the intervals
 * in the right subtree.
 */

#include <iostream>
#include <vector>

using namespace std;

class IntervalTree {
public:
    IntervalTree() : m_root(nullptr) {}

    void insert(const Interval& interval) {
        m_root = insert(m_root, interval);
    }

    IntervalTreeNode* overlaps(const Interval& interval) const {
        return overlaps(m_root, interval);
    }

    void inOrderPrint() const {
        inOrderPrint(m_root);
    }

private:
    IntervalTreeNode* insert(IntervalTreeNode* node, const Interval& interval) {
        if (node == nullptr) {
            node = new IntervalTreeNode(interval);
            node->maxHigh = interval.high;
            return node;
        }
        if (interval.low <= node->interval.low) {
            node->left = insert(node->left, interval);
        } else {
            node->right = insert(node->right, interval);
        }
        node->maxHigh = max(node->maxHigh, interval.high);
        return node;
    }

    IntervalTreeNode* overlaps(IntervalTreeNode* node, const Interval& interval) const {
        if (node == nullptr) return nullptr;
        if (node->interval.overlaps(interval)) return node;
        if (node->left && node->left->maxHigh >= interval.low) {
            return overlaps(node->left, interval);
        }
        return overlaps(node->right, interval);
    }

    void inOrderPrint(IntervalTreeNode* node) const {
        if (!node) return;
        inOrderPrint(node->left);
        cout << "([" << node->interval.low << ", " <<
        node->interval.high << "], maxHigh=" << node->maxHigh << ") " << endl;
        inOrderPrint(node->right);
    }

    IntervalTreeNode* m_root;
};

int main()
{
    vector<Interval> intervals;
    intervals.push_back(Interval(15, 20));
    intervals.push_back(Interval(10, 30));
    intervals.push_back(Interval(17, 19));
    intervals.push_back(Interval(5, 20));
    intervals.push_back(Interval(12, 15));
    intervals.push_back(Interval(30, 40));

    IntervalTree itree;
    for (auto& i : intervals) {
        itree.insert(i);
    }

    itree.inOrderPrint();

    Interval interval(1, 4);
    IntervalTreeNode* node = itree.overlaps(interval);
    if (node) {
        cout << "[" << node->interval.low << ", " <<
        node->interval.high << "] maxHigh = " << node->maxHigh << endl;
    } else {
        cout << "No overlapping intervals are found" << endl;
    }

    return 0;
}
