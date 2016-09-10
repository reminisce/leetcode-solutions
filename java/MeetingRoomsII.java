import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created on 7/15/16.
 * Given an array of meeting time intervals
 * consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find
 * the minimum number of conference rooms required.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */

public class MeetingRoomsII {

    public static void main(String[] args) {
        int[] startTimes = {0, 5, 15};
        int[] endTimes = {30, 10, 20};
        Interval[] intervals = new Interval[startTimes.length];
        for (int i = 0; i < startTimes.length; ++i) {
            intervals[i] = new Interval(startTimes[i], endTimes[i]);
        }
        MeetingRoomsII app = new MeetingRoomsII();
        System.out.println(app.minMeetingRooms2(intervals));
    }

    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        PriorityQueue<Integer> endTimePriorityQueue = new PriorityQueue<>();
        for (Interval interval : intervals) {
            if (endTimePriorityQueue.isEmpty()) endTimePriorityQueue.offer(interval.end);
            else {
                if (interval.start >= endTimePriorityQueue.peek()) {
                    endTimePriorityQueue.poll();
                }
                endTimePriorityQueue.offer(interval.end);
            }
        }
        return endTimePriorityQueue.size();
    }

    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval interval1, Interval interval2) {
            return interval1.start - interval2.start;
        }
    }

    public int minMeetingRooms2(Interval[] intervals) {
        int n = intervals.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];
        for (int i = 0; i < n; ++i) {
            startTimes[i] = intervals[i].start;
            endTimes[i] = intervals[i].end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int count = 0;
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (startTimes[i] >= endTimes[j]) ++j;
            else ++count;
        }
        return count;
    }
}
