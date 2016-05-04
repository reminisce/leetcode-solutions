/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/

#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    int minDepth(TreeNode* root) {
        if (!root) return 0;
        int minDepth = 0;
        int curDepth = 0;
        minDepthHelper(root, minDepth, curDepth);
        return minDepth;
    }

    void minDepthHelper(TreeNode* root, int& minDepth, int curDepth) {
        if (!root) {
            return;
        }

        ++curDepth;

        if (root->left == nullptr && root->right == nullptr) {
            if (minDepth == 0) {
                minDepth = curDepth;
            } else {
                minDepth = min(minDepth, curDepth);
            }
        }

        if (minDepth > 0 && minDepth <= curDepth) {
            return;
        }

        minDepthHelper(root->left, minDepth, curDepth);
        minDepthHelper(root->right, minDepth, curDepth);
    }
};

int main()
{
    TreeNode* root = new TreeNode(1);
    TreeNode* node = new TreeNode(3);
    root->right = node;
    node = new TreeNode(2);
    root->right->left = node;
    node = new TreeNode(4);
    root->right->right = node;
    node = new TreeNode(5);
    root->right->right->right = node; 

    Solution sol;
    int minDepth = sol.minDepth(root);
    cout << "min depth = " << minDepth << endl;
    return 0;
}
