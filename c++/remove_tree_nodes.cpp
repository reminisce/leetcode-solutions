/**
* Created on 7/23/16.
* Given a binary tree and a value list,
* return the tree root values if the nodes
* of the values in the value list are deleted.
*
* For example, tree
*      1
*     / \
*    2  5
*   / \  \
*  3  4  6
*
*  list = [2, 5],
*  return
*  [1, 3, 4, 6]
*/

#include <iostream>
#include <vector>
#include <unordered_set>

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
    vector<TreeNode*> removeTreeNodes(TreeNode* root, const vector<int>& nodeVals) {
        unordered_set<int> nodeValSet;
        for (int v : nodeVals) {
            nodeValSet.insert(v);
        }
        vector<TreeNode*> res;
        root = removeTreeNodesHelper(root, nodeValSet, res);
        if (root != nullptr) {
            res.push_back(root);
        }
        return res;
    }

private:
    // Post-order traversal of removing tree nodes
    TreeNode* removeTreeNodesHelper(TreeNode* node, const unordered_set<int>& nodeValSet, vector<TreeNode*>& res) {
        if (nullptr == node) return nullptr;
        node->left = removeTreeNodesHelper(node->left, nodeValSet, res);
        node->right = removeTreeNodesHelper(node->right, nodeValSet, res);
        if (nodeValSet.count(node->val)) {
            if (node->left != nullptr) res.push_back(node->left);
            if (node->right != nullptr) res.push_back(node->right);
            delete node;
            node = nullptr;
        }
        return node;
    }
};

int main()
{
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->right = new TreeNode(5);
    root->left->left = new TreeNode(3);
    root->left->right = new TreeNode(4);
    root->right->right = new TreeNode(6);
    vector<int> values = {2, 5};
    Solution sol;
    vector<TreeNode*> res = sol.removeTreeNodes(root, values);
    for (TreeNode* node : res) {
        cout << node->val << ' ';
    }
    cout << endl;
}
