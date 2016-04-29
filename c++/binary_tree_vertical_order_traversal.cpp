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

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
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

    Solution sol;
    vector<vector<int>> rs = sol.verticalOrder(nodes[4]);

    for (const vector<int>& vec : rs) {
        for (int val : vec) {
            cout << val << ' ';
        }
        cout << endl;
    }
    return 0;
}