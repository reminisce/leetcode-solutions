/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

#include <queue>
#include <iostream>

using namespace std;

class MovingAverage {
public:
    MovingAverage(int size) : m_sum(0), m_size(size) {}
    
    double next(int val) {
        if (m_slidingWindow.size() == m_size) {
            m_sum -= m_slidingWindow.front();
            m_slidingWindow.pop();
        }
        m_sum += val;
        m_slidingWindow.push(val);
        return m_sum/(double)m_slidingWindow.size();
    }
    
private:
    queue<int> m_slidingWindow;
    double m_sum;
    int m_size;
};

int main()
{
    MovingAverage m(3);
    cout << m.next(1) << endl;
    cout << m.next(10) << endl;
    cout << m.next(3) << endl;
    cout << m.next(5) << endl;
    return 0;
}
