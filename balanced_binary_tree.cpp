/* Given a binary tree, determine if it is height-balanced. For this problem, a
 * height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 */

#include <cstdlib>
#include <algorithm>

using namespace std;

// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode(int x) : val(x), left(NULL), right(NULL) {
    }
};

class Solution {
public:
    bool isBalanced(TreeNode* root) {
        if (!root) {
            return true;
        }
        
        if (checkTreeHeight(root) == -1) {
            return false;
        }
        
        return true;
    }
    
    int checkTreeHeight(TreeNode* root) {
        if (!root) {
            return 0;
        }
        
        int lh = checkTreeHeight(root->left);
        if (lh == -1) {
            return -1;
        }
        
        int rh = checkTreeHeight(root->right);
        if (rh == -1) {
            return -1;
        }
        
        int dh = abs(lh - rh);
        if (dh > 1) {
            return -1;
        }
        
        return 1 + max(lh, rh);
    }
};

int main(int argc, char** argv) {

    return 0;
}

