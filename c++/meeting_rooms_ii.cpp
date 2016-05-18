/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.

For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
*/

#include <vector>
#include <functional> // std::less
#include <algorithm> // std::sort
#include <iostream>
#include <queue> // priority_queue

using namespace std;

struct Interval {
    int start;
    int end;
};

struct IntervalComparator {
    bool operator() (const Interval& interval1, const Interval& interval2) const {
        return interval1.start < interval2.start;
    }
};

class Solution {
public:
    /**
     * Sort the intervals by its start time in ascending order.
     * Loop through the sorted intervals, find for the current interval
     * next closest interval that does not overlap with it, which means
     * they can share one meeting room. In implementation, we can maintain
     * a min
     */
    int minMeetingRooms(vector<Interval>& intervals) {
        if (intervals.empty()) {
            return 0;
        }

        sort(intervals.begin(), intervals.end(), IntervalComparator()); // used specialized std::less<Interval>
        priority_queue<int, vector<int>, greater<int>> pq; // min heap
        pq.push(intervals[0].end);

        for (int i = 1; i < intervals.size(); ++i) {
            // compare with the meeting with the earliest
            // ending time in order to use the meeting room.
            if (intervals[i].start > pq.top()) {
                // In this case, the two meetings have no overlap
                // They can use the same room and update the ending
                // time of this room with the later one.
                pq.pop();
            }
            pq.push(intervals[i].end);
        }

        return pq.size();
    }

    int minMeetingRooms2(vector<Interval>& intervals) {
        if (intervals.empty()) {
            return 0;
        }

        vector<int> startTimes(intervals.size());
        vector<int> endTimes(intervals.size());

        for (int i = 0; i < intervals.size(); ++i) {
            startTimes[i] = intervals[i].start;
            endTimes[i] = intervals[i].end;
        }

        sort(startTimes.begin(), startTimes.end());
        sort(endTimes.begin(), endTimes.end());

        int endIdx = 0;
        int count = 0;
        for (int i = 0; i < intervals.size(); ++i) {
            if (startTimes[i] < endTimes[endIdx]) ++count;
            else ++endIdx;
        }

        return count;
    }
};

int main()
{
    vector<Interval> intervals;
    Interval interval;
    interval.start = 0;
    interval.end = 30;
    intervals.push_back(interval);

    interval.start = 5;
    interval.end = 10;
    intervals.push_back(interval);

    interval.start = 15;
    interval.end = 20;
    intervals.push_back(interval);

    Solution sol;
    int num = sol.minMeetingRooms(intervals);
    cout << "Min number of meetings rooms = " << num << endl;

    return 0;
}