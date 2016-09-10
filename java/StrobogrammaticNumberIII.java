/**
 * Created on 6/5/16.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 *
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */

public class StrobogrammaticNumberIII {

    public static void main(String[] args) {
        StrobogrammaticNumberIII app = new StrobogrammaticNumberIII();
        String low = "10";
        String high = "16";
        int num = app.strobogrammaticInRange("50", "102");
        System.out.println("There are " + num + " strobogrammatic numbers between " + low + " and " + high);
    }

    public int strobogrammaticInRange(String low, String high) {
        int count = 0;
        count = find(low, high, "", count);
        count = find(low, high, "0", count);
        count = find(low, high, "1", count);
        count = find(low, high, "8", count);
        return count;
    }

    private int find(String low, String high, String num, int count) {
        if (num.length() >= low.length() && num.length() <= high.length()) {
            if ((num.length() == low.length() && num.compareTo(low) < 0)
                    || (num.length() == high.length() && num.compareTo(high) > 0)) {
                return count;
            }
            if (!(num.length() > 1 && num.charAt(0) == '0')) ++count;
        }
        if (num.length() + 2 > high.length()) return count;

        count = find(low, high, '0' + num + '0', count);
        count = find(low, high, '1' + num + '1', count);
        count = find(low, high, '6' + num + '9', count);
        count = find(low, high, '8' + num + '8', count);
        count = find(low, high, '9' + num + '6', count);
        return count;
    }
}
