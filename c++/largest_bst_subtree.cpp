/**
* Created on 7/31/16.
* Given a binary tree, find the largest subtree which
* is a Binary Search Tree (BST), where largest means
* subtree with largest number of nodes in it.
* Note:
* A subtree must include all of its descendants.
* Here's an example:
*     10
*    / \
*   5  15
*  / \  \
* 1  8  7
* The Largest BST Subtree in this case is the highlighted one.
* The return value is the subtree's size, which is 3.
* Hint:
* You can recursively use algorithm similar to 98.
* Validate Binary Search Tree at each node of the tree,
* which will result in O(nlogn) time complexity.
* Follow up:
* Can you figure out ways to solve it with O(n) time complexity?
*/

#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

// Per node result
struct Result {
    Result() : size(0), lower(INT_MAX), upper(INT_MIN), isBST(false) {
    }

    int size; // the number of nodes if the current node is a bst
    int lower; // the lower bound of the current subtree if it's a bst
    int upper; // the upper bound of the current subtree if it's a bst
    bool isBST; // indicates whether the current subtree is a bst
};

class Solution {
public:
    int largestBSTSubtree(TreeNode* root) {
        maxSize = 0;
        largestBSTSubtreeHelper(root);
        return maxSize;
    }

    Result largestBSTSubtreeHelper(TreeNode* node) {
        Result result;
        if (nullptr == node) {
            result.isBST = true;
            return result;
        }

        Result leftResult = largestBSTSubtreeHelper(node->left);
        Result rightResult = largestBSTSubtreeHelper(node->right);

        result.lower = min(min(leftResult.lower, rightResult.lower), node->val);
        result.upper= max(max(leftResult.upper, rightResult.upper), node->val);

        if (leftResult.isBST && node->val > leftResult.upper
            && rightResult.isBST && node->val < rightResult.lower) {
            result.size = leftResult.size + rightResult.size + 1;
            result.isBST = true;
            maxSize = max(maxSize, result.size);
        }

        return result;
    }

private:
    int maxSize;
};

int main()
{
    TreeNode* root = new TreeNode(10);
    root->left = new TreeNode(5);
    root->left->left = new TreeNode(1);
    root->left->right = new TreeNode(8);
    root->right = new TreeNode(15);
    root->right->right = new TreeNode(7);
    Solution sol;
    cout << sol.largestBSTSubtree(root) << endl;
    return 0;
}
