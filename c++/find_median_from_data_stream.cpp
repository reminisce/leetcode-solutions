/*
Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
*/

#include <queue>
#include <iostream>

using namespace std;

class MedianFinder {
public:

    // Adds a number into the data structure.
    void addNum(int num) {
        minHeap.push(num);
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.push(minHeap.top());
            minHeap.pop();
        }
        if (!maxHeap.empty() && minHeap.top() > maxHeap.top()) {
            int temp = maxHeap.top();
            maxHeap.pop();
            maxHeap.push(minHeap.top());
            minHeap.pop();
            minHeap.push(temp);
        }
    }

    // Returns the median of current data stream
    double findMedian() {
        if (minHeap.size() != maxHeap.size()) {
            return double(minHeap.top());
        } else {
            return (double(minHeap.top()) + maxHeap.top()) * 0.5;
        }
    }

private:
    priority_queue<int, vector<int>, less<int>> minHeap;
    priority_queue<int, vector<int>, greater<int>> maxHeap;
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf;
// mf.addNum(1);
// mf.findMedian();

int main()
{
    MedianFinder finder;
    finder.addNum(155);
    finder.addNum(66);
    finder.addNum(114);
    finder.addNum(0);
    finder.addNum(60);
    finder.addNum(73);
    finder.addNum(109);
    finder.addNum(26);
    finder.addNum(154);
    finder.addNum(0);
    finder.addNum(107);
    finder.addNum(75);
    finder.addNum(9);
    cout << "The median is " << finder.findMedian() << endl;

    return 0;
}
