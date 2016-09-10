import java.util.List;

/**
 * Created on 8/15/16.
 * (L)
 * Given a list of intervals and assume
 * they are sorted based on the left end points.
 * Calculate the total length cover by
 * this interval list.
 */
public class IntervalCoveredLength {

    public int getCoveredLength(List<Interval> intervalList) {
        if (null == intervalList || intervalList.isEmpty()) return 0;
        Interval curInterval = new Interval(intervalList.get(0).start, intervalList.get(0).end);
        int totalLength = 0;
        for (int i = 1; i < intervalList.size(); ++i) {
            Interval interval = intervalList.get(i);
            if (interval.start <= curInterval.end) {
                curInterval.end = interval.end;
            } else {
                totalLength += curInterval.end - curInterval.start;
                curInterval.start = interval.start;
                curInterval.end = interval.end;
            }
        }
        totalLength += curInterval.end - curInterval.start;
        return totalLength;
    }
}
