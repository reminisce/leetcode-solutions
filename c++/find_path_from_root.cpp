/* Given a root of the tree and an internal node, print
 * the path from the root to the node.
 */

#include <cstdlib>
#include <list>
#include <vector>
#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {
    }
    ~TreeNode() {
        if (this) {
            this->left = nullptr;
            this->right = nullptr;
        }
    }
};

bool getPath(TreeNode* root, TreeNode* dest, list<TreeNode*>& path) {
    if (!root) {
        return false;
    }

    path.push_back(root);

    if (root == dest) {
        return true;
    }
    
    if (getPath(root->left, dest, path)) {
        return true;
    }
    
    if (getPath(root->right, dest, path)) {
        return true;
    }
    
    path.pop_back();

    return false;
}

void preOrder(TreeNode* root) {
    if (!root) {
        return;
    }
    cout << root->val << ' ';
    preOrder(root->left);
    preOrder(root->right);
}

void printPath(const list<TreeNode*>& path)
{
    for(auto it = path.begin(); it != path.end(); ++it) {
        cout << (*it)->val << ' ';
    }
    cout << endl;
}

int main(int argc, char** argv) {
    vector<TreeNode*> nodes;
    nodes.resize(10);
    for (size_t i = 0; i < nodes.size(); ++i) {
        nodes[i] = new TreeNode(i);
    }
    nodes[0]->left = nodes[1];
    nodes[0]->right = nodes[5];
    nodes[1]->left = nodes[2];
    nodes[1]->right = nodes[3];
    nodes[2]->left = nodes[4];
    nodes[5]->right = nodes[6];
    nodes[6]->left = nodes[7];
    nodes[6]->right = nodes[8];
    nodes[7]->left = nodes[9];
    list<TreeNode*> path;
    
    cout << "Pre-order traversal: ";
    preOrder(nodes[0]);
    cout << endl;
    
    cout << "The path from " << nodes[0]->val << " to " << nodes[9]->val << ": ";
    getPath(nodes[0], nodes[9], path);
    printPath(path);
       
    for (size_t i = 0; i < nodes.size(); ++i) {
        if (nodes[i]) {
            delete nodes[i];
            nodes[i] = nullptr;
        }
    }
    
    return 0;
}

