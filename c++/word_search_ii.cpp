/**
* Created on 5/26/16.
* Given a 2D board and a list of words from the dictionary, find all words in the board.
*
* Each word must be constructed from letters of sequentially adjacent cell,
* where "adjacent" cells are those horizontally or vertically neighboring.
* The same letter cell may not be used more than once in a word.
*
* For example,
* Given words = ["oath","pea","eat","rain"] and board =
*
* [
* ['o','a','a','n'],
* ['e','t','a','e'],
* ['i','h','k','r'],
* ['i','f','l','v']
* ]
* Return ["eat","oath"].
* Note:
* You may assume that all inputs are consist of lowercase letters a-z.
*
* click to show hint.
*
* You would need to optimize your backtracking to pass the larger test.
* Could you stop backtracking earlier?
*
* If the current candidate does not exist in all words' prefix,
* you could stop backtracking immediately. What kind of data structure could answer
* such query efficiently? Does a hash table work? Why or why not? How about a Trie?
* If you would like to learn how to implement a basic trie, please work on this problem:
* Implement Trie (Prefix Tree) first.
*/

#include <vector>
#include <string>
#include <iostream>

using namespace std;

class TrieNode {
public:
    TrieNode() {
        for (int i = 0; i < 26; ++i) children[i] = nullptr;
    }
    
    TrieNode* children[26];
    string word;
};

class Trie {
public:
    Trie() : root(nullptr) {}

    void insert(const string& word) {
        if (word.empty()) return;
        if (root == nullptr) root = new TrieNode();
        TrieNode* p = root;
        for (size_t i = 0; i < word.size(); ++i) {
            int index = (int)(word[i] - 'a');
            if (p->children[index] == nullptr) {
                p->children[index] = new TrieNode();
            }
            p = p->children[index];
        }
        p->word = word;
    }

    TrieNode* getRoot() { return root; }

private:
    TrieNode* root;
};

class Solution {
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        vector<string> res;
        if (board.empty() || board[0].empty()) return res;
        int m = board.size();
        int n = board[0].size();

        Trie trie;
        for (auto& word : words) {
            trie.insert(word);
        }

        vector<vector<int>> visited(m, vector<int>(n));
        vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                findWordsHelper(board, i, j, visited, dirs, trie.getRoot(), res);
            }
        }
        return res;
    }

private:
    void findWordsHelper(const vector<vector<char>>& board, int i, int j, vector<vector<int>>& visited,
                         const vector<vector<int>>& dirs, TrieNode* node, vector<string>& res) {
        if (!node->word.empty()) {
            res.push_back(node->word);
            node->word.clear();
        }
        int m = board.size();
        int n = board[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || node == nullptr) return;
        int index = (int)(board[i][j]-'a');
        if (node->children[index] == nullptr) return;

        visited[i][j] = 1;
        for (auto& dir : dirs) {
            findWordsHelper(board, i+dir[0], j+dir[1], visited, dirs, node->children[index], res);
        }
        visited[i][j] = 0;
    }
};

int main()
{
    vector<vector<char>> board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'},
                                  {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
    vector<string> words = {"oath","pea","eat","rain"};
    Solution sol;
    vector<string> res = sol.findWords(board, words);
    for (auto& str : res) {
        cout << str << ' ';
    }
    cout << endl;
    return 0;
}


