/**
 * Find the duplicate subtrees in a binary tree.
 */

#include <vector>
#include <unordered_map>
#include <string>

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
    vector<vector<TreeNode*>> findSameSubtrees(TreeNode* root) {
        unordered_map<string, vector<TreeNode*>> cache;
        findSameSubtreesHelper(root, cache);
        vector<vector<TreeNode*>> res;
        for (auto& entry : cache) {
            res.push_back(entry.second);
        }
        return res;
    }

    string findSameSubtreesHelper(TreeNode* node, unordered_map<string, vector<TreeNode*>>& cache) {
        if (!node) return "#";
        string key = findSameSubtreesHelper(node->left, cache);
        key += (key.empty()? " " : "") + findSameSubtreesHelper(node->right, cache);
        key += (key.empty()? " " : "") + to_string(node->val);
        cache[key].push_back(node);
        return key;
    }
};
