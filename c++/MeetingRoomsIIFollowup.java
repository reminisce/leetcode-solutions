import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created on 7/19/16.
 * Following up on Meeting Rooms II,
 * find out the max number of meeting
 * being held at the same time and return
 * the busiest time.
 */

public class MeetingRoomsIIFollowup {

    public static void main(String[] args) {
        int[] startTimes = {0, 5, 15, 10, 11, 16, 31, 41, 51};
        int[] endTimes = {30, 10, 20, 15, 30, 20, 32, 42, 52};
        Interval[] intervals = new Interval[startTimes.length];
        for (int i = 0; i < startTimes.length; ++i) {
            intervals[i] = new Interval(startTimes[i], endTimes[i]);
        }
        MeetingRoomsIIFollowup app = new MeetingRoomsIIFollowup();
        System.out.println(app.minMeetingRooms(intervals) + " is the busiest time");
    }

    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        PriorityQueue<Integer> endTimePriorityQueue = new PriorityQueue<>();
        int activeMeetings = 0;
        int maxSameTimeMeetings = 0;
        int busiestTime = 0;
        for (Interval interval : intervals) {
            if (endTimePriorityQueue.isEmpty()) {
                endTimePriorityQueue.offer(interval.end);
                ++activeMeetings;
            }
            else {
                while (!endTimePriorityQueue.isEmpty() && interval.start >= endTimePriorityQueue.peek()) {
                    endTimePriorityQueue.poll();
                    --activeMeetings;
                }
                endTimePriorityQueue.offer(interval.end);
                ++activeMeetings;
            }
            if (maxSameTimeMeetings < activeMeetings) {
                maxSameTimeMeetings = activeMeetings;
                busiestTime = interval.start;
            }
            System.out.println("At time " + interval.start + ", " + activeMeetings + " meetings in progress");
        }
        return busiestTime;
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

        int activeMeetings = 0;
        int busiestTime = 0;
        int maxActiveMeetings = 0;
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j < n && startTimes[i] >= endTimes[j]) {
                ++j;
                --activeMeetings;
            }
            ++activeMeetings;

            if (maxActiveMeetings < activeMeetings) {
                maxActiveMeetings = activeMeetings;
                busiestTime = startTimes[i];
            }
            System.out.println("At time " + startTimes[i] + ", " + activeMeetings + " meetings in progress");
        }
        return busiestTime;
    }
}
