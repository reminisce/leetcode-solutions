/**
* Created on 6/26/16.
* Serialization is the process of converting a
* data structure or object into a sequence of
* bits so that it can be stored in a file or
* memory buffer, or transmitted across a network
* connection link to be reconstructed later
* in the same or another computer environment.
*
* Design an algorithm to serialize and deserialize
* a binary tree. There is no restriction on how
* your serialization/deserialization algorithm
* should work. You just need to ensure that a
* binary tree can be serialized to a string
* and this string can be deserialized to the
* original tree structure.
*
* For example, you may serialize the following tree
*
*     1
*    / \
*   2  3
*     / \
*    4  5
* as "[1,2,3,null,null,4,5]", just the same as how LeetCode
* OJ serializes a binary tree. You do not necessarily
* need to follow this format, so please be creative
* and come up with different approaches yourself.
* Note: Do not use class member/global/static
* variables to store states. Your serialize
* and deserialize algorithms should be stateless.
*/

#include <string>
#include <iostream>
#include <queue>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        string str;
        // serializeDFS(root, str);
        serializeBFS(root, str);
        str.pop_back();
        return str;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        int index = 0;
        // TreeNode* root = deserializeDFS(data, index);
        TreeNode* root = deserializeBFS(data);
        return root;
    }

private:
    void serializeDFS(TreeNode* node, string& str) {
        if (!node) {
            str += "# ";
            return;
        }

        str += to_string(node->val) + ' ';
        serializeDFS(node->left, str);
        serializeDFS(node->right, str);
    }

    void serializeBFS(TreeNode* root, string& str) {
        if (!root) {
            str += "#";
            return;
        }

        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();
            if (node) {
                str += to_string(node->val) + ' ';
                q.push(node->left);
                q.push(node->right);
            } else {
                str += "# ";
            }
        }
    }

    TreeNode* deserializeDFS(string& data, int& index) {
        if (index >= data.size()) return nullptr;
        if (data[index] == '#') {
            index += 2;
            return nullptr;
        }
        int right = index + 1;
        while (right < data.size() && data[right] >= '0' && data[right] <= '9') {
            ++right;
        }
        TreeNode* node = new TreeNode(stoi(data.substr(index, right-index)));
        index = right + 1;
        node->left = deserializeDFS(data, index);
        node->right = deserializeDFS(data, index);
        return node;
    }

    TreeNode* deserializeBFS(string& data) {
        if (data.empty()) return nullptr;
        int i = 0;
        if (data[i] == '#') return nullptr;
        int j = i + 1;
        while (j < data.size() && data[j] >= '0' && data[j] <= '9') {
            ++j;
        }
        TreeNode* root = new TreeNode(stoi(data.substr(i, j-i)));
        i = j + 1;
        queue<TreeNode*> q;
        q.push(root);
        while (i < data.size()) {
            TreeNode* curNode = q.front();
            q.pop();
            if (data[i] != '#') {
                j = i + 1;
                while (j < data.size() && data[j] >= '0' && data[j] <= '9') {
                    ++j;
                }
                curNode->left = new TreeNode(stoi(data.substr(i, j)));
                q.push(curNode->left);
                i = j + 1;
            } else {
                i += 2;
            }
            if (i >= data.size()) break;
            if (data[i] != '#') {
                j = i + 1;
                while (j < data.size() && data[j] >= '0' && data[j] <= '9') {
                    ++j;
                }
                curNode->right = new TreeNode(stoi(data.substr(i, j)));
                q.push(curNode->right);
                i = j + 1;
            } else {
                i += 2;
            }
        }
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));

int main()
{
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->right = new TreeNode(3);
    root->left->left = new TreeNode(4);

    Codec codec;
    string str = codec.serialize(root);
    cout << str << endl;

    TreeNode* newRoot = codec.deserialize(str);
    return 0;
}
