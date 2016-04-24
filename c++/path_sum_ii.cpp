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
    vector<vector<int>> pathSum(TreeNode* root, int sum) {
    	vector<vector<int>> rs;
    	if (root == nullptr) {
    		return rs;
    	}

    	vector<int> path;
    	path.resize(1000); // FIXME: get the height of the tree
    	int index = 0; // indicates the last index of the path
    	findPathSum(root, rs, path, index, sum);
    	return rs;
    }

    void findPathSum(TreeNode* root, vector<vector<int>>& rs, vector<int>& path, int index, int sum) {
    	if (root == nullptr) {
    		return;
    	}

    	if (root->left == nullptr && root->right == nullptr) {
    		if (sum == root->val) {
    			path[index] = root->val;
    			savePath(rs, path, index);
    		}
    		return;
    	}

    	if (root->left) {
    		path[index] = root->val;
    		findPathSum(root->left, rs, path, index+1, sum-root->val);
    	}

    	if (root->right) {
    		path[index] = root->val;
    		findPathSum(root->right, rs, path, index+1, sum-root->val);
    	}
    }

    void savePath(vector<vector<int>>& rs, vector<int>& path, int index) {
    	if (index < 0) {
    		return;
    	}

    	rs.push_back(vector<int>());
    	for (int i = 0; i <= index; ++i) {
	    	rs.back().push_back(path[i]);
	    }
    }
};

int main()
{
	return 0;
}