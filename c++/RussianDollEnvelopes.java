import java.util.Arrays;
import java.util.Comparator;

/**
 * Created on 6/25/16.
 * You have a number of envelopes with widths and heights
 * given as a pair of integers (w, h). One envelope can fit
 * into another if and only if both the width and height of
 * one envelope is greater than the width and height of the
 * other envelope.
 *
 * What is the maximum number of envelopes can you Russian
 * doll? (put one inside other)
 *
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number
 * of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

public class RussianDollEnvelopes {

    public static void main(String[] args) {
        int[][] envelopes = {{5,4}, {6, 4}, {6, 7}, {2, 3}};
        RussianDollEnvelopes app = new RussianDollEnvelopes();
        System.out.println(app.maxEnvelopes(envelopes));
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (null == envelopes || envelopes.length == 0) return 0;
        int n = envelopes.length;
        Arrays.sort(envelopes, new EnvelopeComparator());
        // maxNumEnvelopes[i] means the max number of envelopes that
        // can be held by envelopes[i].
        int[] maxNumEnvelopes = new int[n];
        maxNumEnvelopes[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < n; ++i) {
            maxNumEnvelopes[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][1] > envelopes[j][1] && envelopes[i][0] > envelopes[j][0]) {
                    maxNumEnvelopes[i] = Math.max(maxNumEnvelopes[i], maxNumEnvelopes[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, maxNumEnvelopes[i]);
        }

        return maxLen;
    }

    private class EnvelopeComparator implements Comparator<int[]> {
        public int compare(int[] env1, int[] env2) {
            if (env1[0] == env2[0]) return env1[1] - env2[1];
            else return env1[0] - env2[0];
        }
    }
}
