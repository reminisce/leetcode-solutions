/**
* Created on 7/24/16.
* Given a binary search tree and a value,
* split the binary search tree into two
* binary search trees whose node values
* are less/equal and greater than the given
* value. (G)
*/

#include <iostream>

using namespace std;

struct TreeNode {
    TreeNode(int val) {
        this->val = val;
        left = nullptr;
        right = nullptr;
    }
    TreeNode* left;
    TreeNode* right;
    int val;
};

class Solution {
public:
    pair<TreeNode*, TreeNode*> splitBinarySearchTree(TreeNode* root, int val) {
        if (nullptr == root) return {nullptr, nullptr};
        pair<TreeNode*, TreeNode*> roots = {nullptr, nullptr};
        if (root->val <= val) {
            pair<TreeNode*, TreeNode*> p = splitBinarySearchTree(root->right, val);
            roots.first = root;
            root->right = p.first;
            roots.second = p.second;
        } else {
            pair<TreeNode*, TreeNode*> p = splitBinarySearchTree(root->left, val);
            roots.second = root;
            root->left = p.second;
            roots.first = p.first;
        }
        return roots;
    }
};

int main()
{
    TreeNode* root = new TreeNode(3);
    root->left = new TreeNode(2);
    root->left->right = new TreeNode(1);
    root->right = new TreeNode(4);
    root->right->left = new TreeNode(5);
    Solution sol;
    pair<TreeNode*, TreeNode*> roots = sol.splitBinarySearchTree(root, 3);
    cout << roots.first->val << endl;
    cout << roots.second->val << endl;
    return 0;
}
