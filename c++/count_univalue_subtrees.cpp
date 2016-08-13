/**
* Created on 8/3/16.
* Given a binary tree, count the number of uni-value subtrees.
*
* A Uni-value subtree means all nodes of the subtree have the same value.
*
* For example:
* Given binary tree,
*
*     5
*    / \
*   1  5
*  / \  \
* 5  5  5
*
* return 4.
*/

#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution
{
public:
    int countUnivalueSubtrees(TreeNode* root) {
        if (!root) return 0;
        int cnt = 0;
        countHelper(root, cnt);
        return cnt;
    }

    bool countHelper(TreeNode* node, int& cnt) {
        if (!node) return true;
        bool isUniValTreeLeft = countHelper(node->left, cnt);
        bool isUniValTreeRight = countHelper(node->right, cnt);
        if (isSameVal(node, node->left, isUniValTreeLeft) &&
            isSameVal(node, node->right, isUniValTreeRight)) {
            ++cnt;
            return true;
        }
        return false;
    }

    bool isSameVal(TreeNode* parent, TreeNode* child, bool isChildUniValTree) {
        return (child == nullptr || (parent->val == child->val && isChildUniValTree));
    }
};

int main()
{
    TreeNode* root = new TreeNode(5);
    root->left = new TreeNode(1);
    root->left->left = new TreeNode(5);
    root->left->right = new TreeNode(5);
    root->right = new TreeNode(5);
    root->right->right = new TreeNode(5);
    Solution sol;
    cout << sol.countUnivalueSubtrees(root) << endl;
    return 0;
}
