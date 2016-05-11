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

    Vector2D(vector<vector<int>>& vec2d) : m_vec2d(vec2d), m_row(-1), m_col(-1) {
        if (!m_vec2d.empty()) {
            m_row = 0;
        }
        skipEmptyVector();
        if (m_row != (int)m_vec2d.size()) {
            m_col = 0;
        }
    }

    int next() {
        int val = m_vec2d[m_row][m_col];
        if (++m_col == (int)m_vec2d[m_row].size()) {
            ++m_row;
            m_col = 0;
        }
        return val;
    }

    bool hasNext() {
        if (m_row == -1 || m_col == -1 || m_row == (int)m_vec2d.size()) return false;
        skipEmptyVector();
        if (m_row == (int)m_vec2d.size()) return false;
        return true;
    }

private:
    void skipEmptyVector() {
        while (m_row < (int)m_vec2d.size() && m_vec2d[m_row].empty()) ++m_row;
    }

private:
    vector_2d_type& m_vec2d;
    int m_row;
    int m_col;
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

    Vector2D v2d(vs);
    while (v2d.hasNext()) {
        cout << v2d.next() << ' ';
    }
    cout << endl;
    return 0;
}
