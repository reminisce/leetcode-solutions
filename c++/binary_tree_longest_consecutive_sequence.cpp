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
#include <unordered_map>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:

    /**
     * Post order traverse the tree. During the process
     * update the max length. No cache is needed.
     * If doing DFS, a cache is needed.
     */
    int longestConsecutivePostOrder(TreeNode* root) {
        int maxLength = 0;
        longestConsecutivePostOrder(root, maxLength);
        return maxLength;
    }

    // return the consecutive length of the current node
    int longestConsecutivePostOrder(TreeNode* node, int& maxLength) {
        if (!node) return 0;
        int leftLength = longestConsecutivePostOrder(node->left, maxLength);
        int rightLength = longestConsecutivePostOrder(node->right, maxLength);
        int curLength = 1;
        if (node->left && node->val + 1 == node->left->val) {
            curLength = max(curLength, leftLength+1);
        }
        if (node->right && node->val + 1 == node->right->val) {
            curLength = max(curLength, rightLength+1);
        }
        maxLength = max(curLength, max(maxLength, max(leftLength, rightLength)));
        return curLength;
    }

    int longestConsecutive(TreeNode* root) { 
        // return longestConsecutive(root, nullptr, 0);
        unordered_map<TreeNode*, int> cache;
        int maxLength = 0;
        dfs(root, cache, maxLength);
        return maxLength;
    }

    // DFS
    void dfs(TreeNode* node, unordered_map<TreeNode*, int>& cache, int& maxLength) {
        if (!node) return;
        maxLength = max(maxLength, longestConsecutive2(node, cache));
        dfs(node->left, cache, maxLength);
        dfs(node->right, cache, maxLength);
    }

    int longestConsecutive2(TreeNode* node, unordered_map<TreeNode*, int>& cache) {
        if (!node) return 0;
        auto it = cache.find(node);
        if (it != cache.end()) return it->second;
        int len = 0;
        if (node->left && node->val + 1 == node->left->val) {
            len = max(longestConsecutive2(node->left, cache), len);
        }
        if (node->right && node->val + 1 == node->right->val) {
            len = max(longestConsecutive2(node->right, cache), len);
        }
        cache[node] = len + 1;
        return len + 1;
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
    // int len = sol.longestConsecutive(root);
    int len = sol.longestConsecutivePostOrder(root);
    cout << "Longest consecutive path is " << len << endl;
    return 0;
}
