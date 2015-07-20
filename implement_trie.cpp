/* Implement a trie with insert, search, and startsWith methods.
 */

#include <cstdlib>
#include <unordered_map>
#include <string>
#include <iostream>

using namespace std;

class TrieNode {
public:
    // Initialize your data structure here.
    TrieNode(): m_is_word_last_char(false) {}
    
    void markAsWordLastChar() {
        m_is_word_last_char = true;
    }
    
    bool isWordLastChar() const {
        return m_is_word_last_char;
    }
    
    TrieNode* getChildNode(char c) {
        auto hit = m_children.find(c);
        return (hit == m_children.end()? nullptr : hit->second);
    }
    
    TrieNode* createChildNode(char c) {
        auto hit = m_children.find(c);
        if (hit == m_children.end()) {
            TrieNode* node = new TrieNode();
            m_children[c] = node;
            return node;
        }
        return hit->second;
    }
    
    bool isNodeLeaf() const {
        return m_children.empty();
    }
    
    ~TrieNode() {
        for (auto it = m_children.begin(); it != m_children.end(); ++it) {
            if (it->second) {
                delete it->second;
                it->second = nullptr;
            }
        }
        m_children.clear();
    }
private:
    bool m_is_word_last_char;
    unordered_map<char, TrieNode*> m_children;
};

class Trie {
public:
    Trie() {
        m_root = new TrieNode();
    }

    // Inserts a word into the trie.
    void insert(string word) {
        TrieNode* node = m_root;
        for (size_t i = 0; i < word.size(); ++i) {
            node = node->createChildNode(word[i]);
        }
        node->markAsWordLastChar();
    }

    // Returns if the word is in the trie.
    bool search(string word) {
        TrieNode* node = m_root;
        for (size_t i = 0; i < word.size(); ++i) {
            node = node->getChildNode(word[i]);
            if (node == nullptr) {
                return false;
            }
        }
        return node->isWordLastChar();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    bool startsWith(string prefix) {
        TrieNode* node = m_root;
        for (size_t i = 0; i < prefix.size(); ++i) {
            node = node->getChildNode(prefix[i]);
            if (node == nullptr) {
                return false;
            }
        }
        return true;
    }
    
    ~Trie() {
        if (m_root) {
            delete m_root;
            m_root = nullptr;
        }
    }

private:
    TrieNode* m_root;
};

// Your Trie object will be instantiated and called as such:
// Trie trie;
// trie.insert("somestring");
// trie.search("key");

int main(int argc, char** argv) {

    Trie trie;
    string str = "abc";
    trie.insert(str);
    trie.insert("ab");
    string word = "ab";
    bool ret = trie.search(word);
    cout << ret << endl;
    return 0;
}

