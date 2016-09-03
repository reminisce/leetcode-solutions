/*
(F)
Given a binary tree, print all the
root-to-leave paths using bfs.
*/

#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    vector<string> printAllPaths(TreeNode* root) {
        vector<string> paths;
        if (!root) return paths;
        queue<vector<TreeNode*>> q;
        q.push(vector<TreeNode*>(1, root));
        while (!q.empty()) {
            vector<TreeNode*> curPath = q.front();
            q.pop();
            if (!curPath.back()->left && !curPath.back()->right) {
                savePath(curPath, paths);
                continue;
            }
            if (curPath.back()->left) {
                vector<TreeNode*> leftPath = curPath;
                leftPath.push_back(curPath.back()->left);
                q.push(leftPath);
            }
            if (curPath.back()->right) {
                vector<TreeNode*> rightPath = curPath;
                rightPath.push_back(curPath.back()->right);
                q.push(rightPath);
            }
        }
        return paths;
    }

    void savePath(const vector<TreeNode*>& path, vector<string>& paths) {
        paths.push_back("");
        for (size_t i = 0; i < path.size(); ++i) {
            paths.back() += to_string(path[i]->val);
            if (i < (int)path.size()-1) {
                paths.back() += "->";
            }
        }
    }
};

int main()
{
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->right = new TreeNode(3);
    root->left->left = new TreeNode(4);
    root->left->right = new TreeNode(5);
    root->right->left = new TreeNode(6);
    root->left->left->right = new TreeNode(7);
    root->left->right->left = new TreeNode(8);
    root->left->right->left->left = new TreeNode(10);
    root->right->left->right = new TreeNode(9);
    root->right->left->right->right = new TreeNode(11);
    Solution sol;
    vector<string> paths = sol.printAllPaths(root);
    for (auto& path : paths) {
        cout << path << endl;
    }
    return 0;
}
