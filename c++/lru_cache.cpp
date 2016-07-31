/*
Design and implement a data structure
for Least Recently Used (LRU) cache.
It should support the following operations:
get and set.

get(key) - Get the value (will always be positive)
of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if
the key is not already present. When the cache
reached its capacity, it should invalidate the
least recently used item before inserting a new item.
*/

#include <list>
#include <unordered_map>
#include <iostream>

using namespace std;

class LRUCache{
public:
    typedef pair<int, int> pair_type;
    typedef list<pair_type> list_type;
    typedef list_type::iterator list_iterator_type;
    typedef unordered_map<int, list_iterator_type> hashmap_type;
    typedef hashmap_type::iterator hashmap_iterator_type;

    LRUCache(int capacity) : m_capacity(capacity) {
    }
    
    int get(int key) {
        hashmap_iterator_type hit = m_hashmap.find(key);
        if (hit == m_hashmap.end()) return -1;
        moveToFront(hit->second);
        return hit->second->second;
    }
    
    void set(int key, int value) {
        hashmap_iterator_type hit = m_hashmap.find(key);
        if (hit != m_hashmap.end()) {
            hit->second->second = value;
            moveToFront(hit->second);
        } else {
            if (m_hashmap.size() == m_capacity) {
                int key2 = m_list.back().first;
                m_hashmap.erase(key2);
                m_list.pop_back();
            }
            m_list.push_front(make_pair(key, value));
            m_hashmap[key] = m_list.begin();
        }
    }

    void printList() {
        for (list_iterator_type lit = m_list.begin(); lit != m_list.end(); ++lit) {
            cout << "(" << lit->first << ", " << lit->second << ") ";
        }
        cout << endl;
    }

private:
    void moveToFront(list_iterator_type& lit) {
        if (lit == m_list.begin()) return;
        m_list.splice(m_list.begin(), m_list, lit);
    }

    int m_capacity;
    list_type m_list;
    hashmap_type m_hashmap;
};

int main()
{
    LRUCache lruCache(2);
    lruCache.set(1, 1);
    lruCache.printList();
    lruCache.set(2, 2);
    lruCache.printList();
    lruCache.get(1);
    lruCache.printList();
    lruCache.set(3, 3);
    lruCache.printList();
    return 0;
}
