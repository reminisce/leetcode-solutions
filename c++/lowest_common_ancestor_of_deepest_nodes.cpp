/*
Given a binary tree, find the lowest
common ancestor of the deepest node(s).
For example,
       1
      / \
     2   3
    / \   
   4   5
this tree's deepest ndoes are 4 and 5,
and the lowest common ancestor is 2.
If there is only one deepest node,
return itself.   
*/

#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class LowestCommonAncestor {
public:
    TreeNode* findLCA(TreeNode* root) {
        int depth = 0;
        return findLCAHelper(root, depth);
    }

    TreeNode* findLCAHelper(TreeNode* node, int& depth) {
        if (!node) {
            depth = 0;
            return nullptr;
        }

        int leftDepth = 0;
        TreeNode* leftNode = findLCAHelper(node->left, leftDepth);
        int rightDepth = 0;
        TreeNode* rightNode = findLCAHelper(node->right, rightDepth);
        depth = 1 + max(leftDepth, rightDepth);

        if (leftDepth == rightDepth) return node;
        else if (leftDepth < rightDepth) return rightNode;
        else return leftNode;
    }
};

int main()
{
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->right = new TreeNode(3);
    root->left->left = new TreeNode(4);
    root->left->right = new TreeNode(5);
    root->right->right = new TreeNode(6);
    LowestCommonAncestor finder;
    cout << finder.findLCA(root)->val << endl;
    return 0;
}
