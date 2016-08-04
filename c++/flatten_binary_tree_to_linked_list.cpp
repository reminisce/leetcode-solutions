/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
 

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree,
each node's right child points to the next node of a pre-order trave
*/

struct TreeNode {
    TreeNode* left;
    TreeNode* right;
    int val;
};

class Solution {
public:
    void flattenRecursive(TreeNode *root) {
        if (!root) return;
        if (root->left) flattenRecursive(root->left);
        if (root->right) flattenRecursive(root->right);
        TreeNode *tmp = root->right;
        root->right = root->left;
        root->left = nullptr;
        while (root->right) root = root->right;
        root->right = tmp;
    }

    void flattenItervative(TreeNode *root) {
        TreeNode *cur = root;
        while (cur) {
            if (cur->left) {
                TreeNode *p = cur->left;
                while (p->right) p = p->right;
                p->right = cur->right;
                cur->right = cur->left;
                cur->left = nullptr;
            }
            cur = cur->right;
        }
    }
};
