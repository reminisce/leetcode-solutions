/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false,
the order of elements returned by next should be: [1, 2, 3, 4, 5, 6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/

#include <vector>
#include <iostream>

using namespace std;

class Vector2D {
public:
    typedef vector<int> vector_type;
    typedef vector_type::iterator iterator_type;
    typedef vector<vector_type> vector_2d_type;
    typedef vector_2d_type::iterator iterator_2d_type;

    Vector2D(vector<vector<int>>& vec2d) : m_vec2d(vec2d), m_row(0), m_col(0) {
    }

    int next() {
        return m_vec2d[m_row][m_col++];
    }

    bool hasNext() {
        while (m_row < (int)m_vec2d.size() && m_col == (int)m_vec2d[m_row].size()) {
            ++m_row;
            m_col = 0;
        }

        return m_row < (int)m_vec2d.size();
    }

private:
    vector_2d_type& m_vec2d;
    int m_row;
    int m_col;
};

class Vector2DIter {
public:
    typedef vector<int> vector_type;
    typedef vector_type::iterator iterator_type;
    typedef vector<vector_type> vector_2d_type;
    typedef vector_2d_type::iterator iterator_2d_type;

    Vector2DIter(vector<vector<int>>& vec2d) : m_vec2d(vec2d), m_row_iter(m_vec2d.begin()) {
        if (!m_vec2d.empty()) {
            m_col_iter = m_row_iter->begin();
        }
    }

    int next() {
        return *(m_col_iter++);
    }

    bool hasNext() {
        if (m_vec2d.empty()) return false;

        while (m_row_iter != m_vec2d.end() && m_col_iter == m_row_iter->end()) {
            ++m_row_iter;
            if (m_row_iter == m_vec2d.end()) return false;
            m_col_iter = m_row_iter->begin();
        }

        return true;
    }

private:
    vector_2d_type& m_vec2d;
    iterator_2d_type m_row_iter;
    iterator_type m_col_iter;
};

int main()
{
    vector<int> v1 = {1, 2, 3};
    vector<int> v2 = {4};
    vector<int> v3 = {5, 6, 7};
    vector<int> v4 = {};
    vector<vector<int>> vs;
    vs.push_back(v4);
    vs.push_back(v4);
    vs.push_back(v4);

    vs.push_back(v1);
    vs.push_back(v2);
    vs.push_back(v4);
    vs.push_back(v4);
    vs.push_back(v4);
    vs.push_back(v3);


    vs.push_back(v4);
    vs.push_back(v4);
    vs.push_back(v4);
    vs.push_back(v4);

    Vector2DIter v2d(vs);
    while (v2d.hasNext()) {
        cout << v2d.next() << ' ';
    }
    cout << endl;
    return 0;
}
