/*
Given a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
*/

#include <vector>
#include <iostream>
#include <queue>

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
     * Use level order traversal to store the last node
     * value of each level.
     */
    vector<int> rightSideView(TreeNode* root) {
        vector<int> rs;
        if (!root) return rs;

        queue<TreeNode*> levelQueue;
        levelQueue.push(root);

        while (!levelQueue.empty()) {
            int sz = levelQueue.size();
            for (int i = 0; i < sz; ++i) {
                TreeNode* node = levelQueue.front();
                levelQueue.pop();
                if (i + 1 == sz) {
                    rs.push_back(node->val);
                }

                if (node->left) {
                    levelQueue.push(node->left);
                }
                if (node->right) {
                    levelQueue.push(node->right);
                }
            }
        }
        return rs;
    }
};

int main()
{
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->right = new TreeNode(3);
    root->left->left = new TreeNode(4);
    Solution solution;
    vector<int> rs = solution.rightSideView(root);
    for (size_t i = 0; i < rs.size(); ++i) {
        cout << rs[i] << ' ';
    }
    cout << endl;

    return 0;
}