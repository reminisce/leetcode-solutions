/**
 * Created on 5/25/16.
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root." Besides the root,
 * each house has one and only one parent house. After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree". It will automatically contact the police if
 * two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

#include <utility>
#include <unordered_map>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    int rob(TreeNode* root) {
        // unordered_map<TreeNode*, int> cache;
        //return robHelper(root, cache);
        auto p = robHelper(root);
        return max(p.first, p.second);
    }

    /**
     * post order traversal, the return value
     * first is robbing this node,
     * second is not robbing this node.
     */
    pair<int, int> robHelper(TreeNode* node) {
        if (!node) return {0, 0};
        auto leftSum = robHelper(node->left);
        auto rightSum = robHelper(node->right);
        return {node->val + leftSum.second + rightSum.second,
            max(leftSum.first, leftSum.second) + max(rightSum.first, rightSum.second)};
    }

    int robHelper(TreeNode* node, unordered_map<TreeNode*, int>& cache) {
        if (!node) return 0;
        auto it = cache.find(node);
        if (it != cache.end()) return it->second;

        int leftSum = robHelper(node->left, cache);
        int rightSum = robHelper(node->right, cache);

        int leftSum2 = 0;
        if (node->left) leftSum2 = robHelper(node->left->left, cache) + robHelper(node->left->right, cache);
        int rightSum2 = 0;
        if (node->right) rightSum2 = robHelper(node->right->left, cache) + robHelper(node->right->right, cache);

        int maxSum = max(leftSum + rightSum, node->val+leftSum2+rightSum2);
        cache[node] = maxSum;
        return maxSum;
    }
};
