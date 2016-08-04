/*
Design a Phone Directory which supports
the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
*/

#include <vector>

using namespace std;

/**
 * Use two arrays:
 * 1. bool used[maxNum], indicates whether number i is used
 * 2. int recycled[maxNum] and an index, where recycled[0],...,recycled[idx]
 * are all the number recycled. If assign numbers, first assign from
 * here.
 * In addition, use another index to record the position of used array
 */
class PhoneDirectory {
public:
    PhoneDirectory(int maxNum) : m_index1(-1), m_index2(-1), m_maxNum(maxNum) {
        m_used.resize(m_maxNum, 0);
        m_recycled.resize(m_maxNum);
    }

    int get() {
        if (m_index1 == m_maxNum-1 && m_index2 < 0) return -1;
        if (m_index2 >= 0) {
            int n = m_recycled[m_index2--];
            m_used[n] = 1;
            return n;
        }

        m_used[++m_index1] = 1;
        return m_index1;
    }

    bool check(int number) {
        return number >= 0 && number < m_maxNum && !m_used[number]; 
    }

    void release(int number) {
        if (number >= 0 && number < m_maxNum && m_used[number]) {
            m_used[number] = 0;
            m_recycled[++m_index2] = number;
        }
    }

private:
    vector<int> m_used; // 1: used, 0: unused
    vector<int> m_recycled; // store the numbers recycled so far
    int m_index1; // for used array
    int m_index2; // for recycled
    int m_maxNum;
};
