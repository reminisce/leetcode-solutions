/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the
tree along the parent-child connections. The longest consecutive path need to be from
parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
*/

#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    int longestConsecutive(TreeNode* root) { 
        return longestConsecutive(root, nullptr, 0);
    }

    int longestConsecutive(TreeNode* curNode, TreeNode* parentNode, int len) {
        if (!curNode) return len;
        len = ((parentNode && parentNode->val + 1 == curNode->val)? len + 1 : 1);
        return max(len,
                   max(longestConsecutive(curNode->left, curNode, len),
                       longestConsecutive(curNode->right, curNode, len)));
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
    int len = sol.longestConsecutive(root);
    cout << "Longest consecutive path is " << len << endl;
    return 0;
}
