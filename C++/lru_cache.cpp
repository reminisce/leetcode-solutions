#include <cstdlib>
#include <list>
#include <unordered_map>

using namespace std;

class LRUCache{
public:
    LRUCache(int capacity): m_capacity(capacity) {
        m_hashmap.clear();
        m_hashmap.reserve(capacity);
        m_list.clear();
    }
    
    int get(int key) {
        auto hit = m_hashmap.find(key);
        if (hit == m_hashmap.end()) {
            return -1;
        }
        move2front(hit->second.first);
        return hit->second.second;
    }
    
    void set(int key, int value) {
        auto hit = m_hashmap.find(key);
        if (hit == m_hashmap.end()) {
            if (m_list.size() == m_capacity) {
                m_hashmap.erase(m_list.back());
                m_list.erase(--(m_list.end()));
            }
            m_list.push_front(key);
            m_hashmap[key] = {m_list.begin(), value};
            return;
        }
        hit->second.second = value;
        move2front(hit->second.first);
    }
    
    void move2front(list<int>::iterator it) {
        if (it != m_list.begin()) {
            m_list.splice(m_list.begin(), m_list, it);
        }
    }
private:
    unordered_map<int, pair<list<int>::iterator, int>> m_hashmap; // the int in the pair is the value of the key
    list<int> m_list; // doubly linked list, store the key
    int m_capacity;
};

int main(int argc, char** argv) {

    return 0;
}

