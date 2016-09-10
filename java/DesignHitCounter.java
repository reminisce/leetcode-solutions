/**
 * Created on 7/2/16.
 * Design a hit counter which hits the number
 * of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity)
 * and you may assume that calls are being made to the system in
 * chronological order (ie, the timestamp is monotonically increasing).
 * You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 */

public class DesignHitCounter {

    public DesignHitCounter(int granularity) {
        this.granularity = granularity;
        hits = new int[granularity];
        timestamps = new int[granularity];
    }

    public void hit(int timestamp) {
        int index = timestamp % granularity;
        if (timestamps[index] != timestamp) {
            timestamps[index] = timestamp;
            hits[index] = 1;
        } else {
            ++hits[index];
        }
    }

    public int getHits(int timestamp) {
        int count = 0;
        for (int i = 0; i < granularity; ++i) {
            if (timestamp - timestamps[i] < granularity) {
                count += hits[i];
            }
        }
        return count;
    }

    private int granularity; // granularity in seconds
    private int[] hits;
    private int[] timestamps;
}
