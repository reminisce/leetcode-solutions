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

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
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