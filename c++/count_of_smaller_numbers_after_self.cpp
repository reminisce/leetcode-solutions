/*
You are given an integer array nums and you have to return a new counts array. The counts array has the
property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
​Return the array [2, 1, 1, 0].
*/

/*
Solution 1: Starting from the last elment in nums, insert it into a newly
allocated array. For each insertion, we use binary search to find the lowest
index bound whose element is not less than the element to be inserted. That
index is the number of smaller elements after itself in original array. For
example, [5, 2, 6, 2, 1], we have the following steps:

Index 0 1 2 3 4
step1 1          // 1's index is 0, there are no smaller numbers after itself
step2 1 2        // 2's index is 1, there are one smaller number after itself
step3 1 2 6      // 6's index is 2, there are two smaller numbers after itself
step4 1 2 2 6    // 2's index is 1, there is one smaller number after itself
step5 1 2 2 5 6  // 5's index is 3, there are three smaller numbers after itself

This is not very efficient in insertion. We can use a binary search tree to reduce
the insertion complexity. The binary search tree's node has a attribute called
smaller, which records the number of smaller nodes on its left.
When inserting a new value, if the value is < root->val, root->smaller++, and call
return insert(root->left, val). Else, insert value into the right subtree of the root,
the number of smaller value is
root->smaller + insert(root->right, value) + (root->val!=value? 1 : 0).
*/

#include <vector>
#include <iostream>

using namespace std;

struct BinarySearchTreeNode {
    int val;
    int smaller; // #smaller nodes on its left
    int duplicate;
    BinarySearchTreeNode *left;
    BinarySearchTreeNode *right;
    BinarySearchTreeNode(int x) : val(x), smaller(0), duplicate(1), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    vector<int> countSmaller(vector<int>& nums) {
        vector<int> rs(nums.size());
        BinarySearchTreeNode* root = nullptr;
        for (int i = (int)nums.size() - 1; i >= 0; --i) {
            root = insertRecursive(root, nums[i], 0, rs, i);
        }
        return rs;
    }

    BinarySearchTreeNode* insertRecursive(BinarySearchTreeNode* node, int val, int smaller, vector<int>& res, int idx) {
        if (!node) {
            node = new BinarySearchTreeNode(val);
            res[idx] = smaller;
        } else if (node->val == val) {
            ++node->duplicate;
            res[idx] = smaller + node->smaller;
        } else if (node->val > val) {
            ++node->smaller;
            node->left = insertRecursive(node->left, val, smaller, res, idx);
        } else {
            node->right = insertRecursive(node->right, val, smaller+node->duplicate+node->smaller, res, idx);
        }
        return node;
    }
};

int main()
{
    vector<int> nums = {5, 2, 6, 1};
    Solution sol;
    vector<int> rs = sol.countSmaller(nums);
    for (int v : rs) {
        cout << v << ' ';
    }
    cout << endl;
    return 0;
}
