/*
Given a binary tree, return the zigzag level order
traversal of its nodes' values. (ie, from left to
right, then right to left for the next level and
alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

class Solution
{
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        vector<vector<int>> res;
        if (!root) return res;
        stack<TreeNode*>* curLevel = new stack<TreeNode*>();
        curLevel->push(root);
        stack<TreeNode*>* nextLevel = new stack<TreeNode*>();
        bool left2right = true;

        while (!curLevel->empty()) {
            res.push_back({});
            while (!curLevel->empty()) {
                TreeNode* node = curLevel->top();
                curLevel->pop();
                res.back().push_back(node->val);
                if (left2right) {
                    if (node->left) {
                        nextLevel->push(node->left);
                    }
                    if (node->right) {
                        nextLevel->push(node->right);
                    }
                } else {
                    if (node->right) {
                        nextLevel->push(node->right);
                    }
                    if (node->left) {
                        nextLevel->push(node->left);
                    }
                }
            }
            left2right = !left2right;
            stack<TreeNode*>* tmp = curLevel;
            curLevel = nextLevel;
            nextLevel = tmp;
        }
        return res;
    }
};