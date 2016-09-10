/**
 * Created on 8/21/16.
 * Given a 1m sidewalk, simulate rain drop with 1cm
 * and count how many random rain drops can make
 * the sidewalk wet.
 */

public class RainDropSimulator {

    public static void main(String[] args) {
        RainDropSimulator app = new RainDropSimulator();
        System.out.println(app.simulate());
    }

    public static class Interval {
        // left and right denote the
        // interval that has not been wet yet
        double left = 0, right = 0.01;
        boolean isWet() { return left >= right; }
    }

    /**
     * We divide the sidewalk into 100 intervals, each of which
     * has two end points indicating the segment that has not been
     * wet. Generate a rain drop with center as a random number,
     * and left and right end points by minus/plus 0.005 to the center.
     * We calculate the interval index that cover the rain drop's left
     * end point. If it's not totally wet, set its right end point
     * to the left end point of the rain drop. For example, see the following
     * picture.
     * |______|______|  sidewalk intervals
     *   |_______|      raindrop interval
     * |_|       |___|  side walk intervals that have not been wet after this rain drop
     * @return
     */
    public int simulate() {
        Interval[] sidewalk = new Interval[100];
        for (int i = 0; i < 100; ++i) {
            sidewalk[i] = new Interval();
        }
        int cnt = 0, wetCnt = 0;
        while (wetCnt < 100) {
            ++cnt;
            double p = Math.random();
            double left = p - 0.005;
            double right = p + 0.005;

            if (left >= 0) {
                int idx = (int)(left/0.01);
                if (!sidewalk[idx].isWet()) {
                    double iright = left - idx * 0.01;
                    if (iright < sidewalk[idx].right) {
                        sidewalk[idx].right = iright;
                        if (sidewalk[idx].isWet()) {
                            ++wetCnt;
                        }
                    }
                }
            }

            if (right <= 1) {
                int idx = (int)(right/0.01);
                if (!sidewalk[idx].isWet()) {
                    double ileft = right - idx * 0.01;
                    if (ileft > sidewalk[idx].left) {
                        sidewalk[idx].left = ileft;
                        if (sidewalk[idx].isWet()) {
                            ++wetCnt;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
