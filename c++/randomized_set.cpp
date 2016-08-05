/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements.
Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 1 is the only number in the set, getRandom always return 1.
randomSet.getRandom();Design a data structure that supports
all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements.
Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 1 is the only number in the set, getRandom always return 1.
randomSet.getRandom();
*/

#include <vector>
#include <unordered_map>
#include <random>
#include <iostream>

using namespace std;

class RandomizedSet {
public:
    /** Initialize your data structure here. */
    RandomizedSet() : m_capacity(10000), m_lastIdx(-1) {
        m_num2IndexMap.reserve(m_capacity);
        m_nums.resize(m_capacity);
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    bool insert(int val) {
        if (m_num2IndexMap.count(val)) return false;
        if (m_lastIdx < m_capacity-1) {
            m_nums[++m_lastIdx] = val;
        } else {
            m_nums.push_back(val);
            ++m_lastIdx;
            ++m_capacity;
        }
        m_num2IndexMap[val] = m_lastIdx;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    bool remove(int val) {
        if (!m_num2IndexMap.count(val)) return false;
        int index = m_num2IndexMap[val];
        swap(m_nums[index], m_nums[m_lastIdx--]);
        m_num2IndexMap.erase(val);
        return true;
    }
    
    /** Get a random element from the set. */
    int getRandom() {
        if (m_lastIdx < 0) return 0;
        int index = rand() % (m_lastIdx + 1);
        return m_nums[index];
    }

private:
    unordered_map<int, int> m_num2IndexMap;
    vector<int> m_nums;
    int m_capacity;
    int m_lastIdx;
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * bool param_1 = obj.insert(val);
 * bool param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

int main()
{
    RandomizedSet randomSet;
    cout << "randomSet.insert(1) = ";
    cout << randomSet.insert(1) << endl;

    cout << "randomSet.remove(2) = ";
    cout << randomSet.remove(2) << endl;

    cout << "randomSet.insert(2) = ";
    cout << randomSet.insert(2) << endl;

    cout << "randomSet.getRandom() = ";
    cout << randomSet.getRandom() << endl;

    cout << "randomSet.remove(1) = ";
    cout << randomSet.remove(1) << endl;

    cout << "randomSet.insert(2) = ";
    cout << randomSet.insert(2) << endl;

    cout << "randomSet.getRandom() = ";
    cout << randomSet.getRandom() << endl;
    return 0;
}

