/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
*/

/**
 * Answer to the follow-up:
 * We can add another data member in the tree node to record
 * the nodes in its left subtree. Then, we can decide whether
 * we want to recurse to the left subtree or the right.
 * Such a bst is called order statistic tree.
 * https://en.wikipedia.org/wiki/Order_statistic_tree
 * All operations that modify the tree must adjust this information
 * to preserve the invariant that
 * size[x] = size[left[x]] + size[right[x]] + 1
 * Such type of tree supports two additional operations beyond
 * insertion, lookup, and deletion.
 * Select(i) -- find the i-th smallest element stored in the tree
 * Rank(x) -- find the rank of element x in the tree, i.e. its
 * index in the sorted list of elements of the tree.
 */

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
        if (!root) {
            return 0;
        }

        TreeNode* node = nullptr;
        inOrder(root, node, k);
        if (!node) {
            return 0;
        }

        return node->val;
    }

    void inOrder(TreeNode* root, TreeNode*& node, int& count) {
        if (!root) {
            return;
        }
        inOrder(root->left, node, count);
        if (--count == 0) {
            node = root;
            return;
        }
        inOrder(root->right, node, count);
    }
};
