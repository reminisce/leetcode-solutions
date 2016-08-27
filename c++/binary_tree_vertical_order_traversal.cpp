/*
Given a binary tree, print it vertically. The following example illustrates vertical order traversal.

           1
        /    \
       2      3
      / \    / \
     4   5  6   7
             \   \
              8   9 
               
              
The output of print this tree vertically will be:
4
2
1 5 6
3 8
7
9 
*/

#include <iostream>
#include <vector>
#include <map>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    vector<vector<int>> verticalOrderDFS(TreeNode* root) {
        map<int, vector<int>> offset2ValuesMap;
        verticalOrderDFSHelper(root, 0, offset2ValuesMap);
        vector<vector<int>> res;
        for (auto& entry : offset2ValuesMap) {
            res.push_back(entry.second);
        }
        return res;
    }

    void verticalOrderDFSHelper(TreeNode* node, int curOffset, map<int, vector<int>>& offset2ValuesMap) {
        if (!node) return;
        offset2ValuesMap[curOffset].push_back(node->val);
        verticalOrderDFSHelper(node->left, curOffset-1, offset2ValuesMap);
        verticalOrderDFSHelper(node->right, curOffset+1, offset2ValuesMap);
    }
#if 0
    vector<vector<int>> verticalOrder(TreeNode* root) {
        int minDist = 0;
        int maxDist = 0;
        findMinMax(root, minDist, maxDist);

        vector<vector<int>> rs;
        for (int offset = minDist; offset <= maxDist; ++offset) {
            vector<int> col;
            verticalOrderHelper(root, col, offset, 0);
            rs.push_back(col);
        }
        return rs;
    }

    void verticalOrderHelper(TreeNode* root, vector<int>& col, int offset, int d) {
        if (!root) {
            return;
        }

        if (d == offset) {
            col.push_back(root->val);
        }
        verticalOrderHelper(root->left, col, offset, d-1);
        verticalOrderHelper(root->right, col, offset, d+1);
    }
    /**
     * Find the horizontal distance from the leftmost and
     * rightmost nodes to the root, respectively. The
     * minDist is a negative number.
     */
    void findMinMax(TreeNode* root, int& minDist, int& maxDist) {
        if (!root) {
            minDist = 0;
            maxDist = 0;
            return;
        }

        int d = 0;
        // check left subtree
        TreeNode* node = root;
        while (node->left) {
            node = node->left;
            --d;
        }
        minDist = d;

        //check right subtree
        node = root;
        d = 0;
        while (node->right) {
            node = node->right;
            ++d;
        }
        maxDist = d;
    }
#endif
};


int main()
{
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->left->left = new TreeNode(4);
    root->left->right = new TreeNode(5);
    root->right = new TreeNode(3);
    root->right->left = new TreeNode(6);
    root->right->left->right = new TreeNode(8);
    root->right->right = new TreeNode(7);
    root->right->right->right = new TreeNode(9);
    Solution sol;
    vector<vector<int>> rs = sol.verticalOrderDFS(root);

    for (const vector<int>& vec : rs) {
        for (int val : vec) {
            cout << val << ' ';
        }
        cout << endl;
    }
    return 0;
}