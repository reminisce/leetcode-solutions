/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 };

class Solution {
public:
    int closestValue(TreeNode* root, double target) {
        if (!root) return -1;

        double minDist = fabs(root->val - target);
        TreeNode* node = root;
        while (root) {
            double dist = fabs(root->val - target);
            if (dist == 0) {
                return root->val;
            }
            if (dist < minDist) {
                node = root;
                minDist = dist;
            }

            if (target < root->val) {
                root = root->left;
            } else {
                root = root->right;
            }
        }

        return node->val;
    }
};

int main()
{
    vector<TreeNode*> nodes;
    for (int i = 0; i < 7; ++i) {
        nodes.push_back(new TreeNode(i));
    }
    nodes[1]->left = nodes[0];
    nodes[1]->right = nodes[3];
    nodes[3]->left = nodes[2];
    nodes[4]->left = nodes[1];
    nodes[4]->right = nodes[5];
    nodes[5]->right = nodes[6]; 

    double target = 0;
    Solution sol;
    int val = sol.closestValue(nodes[4], target);
    cout << val << endl;
    return 0;
}
