/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/
#include <climits>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    bool isValidBST(TreeNode* root) {        
        if (!root) {
            return true;
        }
        int prevNodeValue = INT_MIN;
        bool isLeftMostNode = true;
        return isValidBSTTraverse(root, prevNodeValue, isLeftMostNode);
    }

    bool isValidBSTTraverse(TreeNode* root, int& prevNodeValue, bool& isLeftMostNode) {
        if (!root) return true;

        bool ret = isValidBSTTraverse(root->left, prevNodeValue, isLeftMostNode);
        if (!ret) return false;
        if (isLeftMostNode) {
            isLeftMostNode = false;
        } else if (prevNodeValue >= root->val) {
            return false;
        }
        prevNodeValue = root->val;
        return isValidBSTTraverse(root->right, prevNodeValue, isLeftMostNode);
    }

    bool isValidBST2(TreeNode* root) {
        prevNode = nullptr;
        return isValidBSTTraverse2(root);
    }

    bool isValidBSTTraverse2(TreeNode* root) {
        if (!root) {
            return true;
        }
        if (!isValidBSTTraverse2(root->left)) return false;
        if (prevNode && prevNode->val >= root->val) return false;
        prevNode = root;
        return isValidBSTTraverse2(root->right);
    }

private:
    TreeNode* prevNode;
};