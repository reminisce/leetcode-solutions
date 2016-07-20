import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/12/16.
 * Consider a binary watch with 4 LEDs to display
 * hours and 6 LEDs to display minutes. Print out
 * all times in human-readable format when exactly
 * n LEDs are on.
 * For example,
 * 01:05 -> 0001 000101
 * Input n = 1 --> Output: [00:01, 00:02, 00:04,
 * 00:08, 00:16, 00:32, 01:00, 02:00, 04:00, 08:00]
 */

public class LEDBinaryDigitalTimeRepresentation {

    public static void main(String[] args) {
        LEDBinaryDigitalTimeRepresentation app = new LEDBinaryDigitalTimeRepresentation();
        int n = 1;
        // List<String> res = app.getDigitalTimeRecursive(n);
        List<String> res = app.getDigitalTimeIterative(n);
        System.out.println(res.toString());
    }

    public List<String> getDigitalTimeRecursive(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            if (i <= 4 && n-i <= 6) {
                List<Integer> hours = getBinaryRepresentations(4, i);
                List<Integer> minutes = getBinaryRepresentations(6, n - i);
                for (int hour : hours) {
                    for (int minute : minutes) {
                        res.add(formatTime(hour, minute));
                    }
                }
            }
        }
        return res;
    }

    public List<String> getDigitalTimeIterative(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= 12; ++i) {
            for (int j = 0; j <= 59; ++j) {
                int n1 = countOneBits(i);
                int n2 = countOneBits(j);
                if (n1 > 4 || n2 > 6) continue;
                if (n1 + n2 == n) {
                    res.add(formatTime(i, j));
                }
            }
        }
        return res;
    }

    private int countOneBits(int n) {
        int count = 0;
        while (n > 0) {
            ++count;
            n = n & (n-1);
        }
        return count;
    }

    private String formatTime(int hour, int minute) {
        return int2time(hour) + ":" + int2time(minute);
    }

    private String int2time(int d) {
        if (d < 10) {
            return "0" + Integer.toString(d);
        }
        return Integer.toString(d);
    }

    /**
     * @param a how many bits in total
     * @param b how many 1's
     * @return
     */
    public List<Integer> getBinaryRepresentations(int a, int b) {
        List<Integer> res = new ArrayList<>();
        if (b > a) return res;
        dfs(a, 0, b, 0, res);
        return res;
    }

    private void dfs(int len, int curPos, int remainingOnes, int num, List<Integer> res) {
        if (curPos == len) {
            if (remainingOnes == 0) {
                res.add(num);
            }
            return;
        }

        if (len < curPos + remainingOnes) return;

        dfs(len, curPos+1, remainingOnes, 2 * num, res);
        dfs(len, curPos+1, remainingOnes-1, 2 * num + 1, res);
    }
}
