/**
 * Created on 6/5/16.
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * For example:
 *
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 *
 * Hint:
 *
 * A naive implementation of the above process is trivial. Could you come up with other methods?
 * What are all the possible results?
 * How do they occur, periodically or randomly?
 *
 * You may find this Wikipedia article useful.
 */

public class AddDigits {
    public int addDigits(int num) {
        int sum = addDigitsHelper(num);
        while (sum >= 10) {
            sum = addDigitsHelper(sum);
        }
        return sum;
    }

    private int addDigitsHelper(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    /**
     * https://en.wikipedia.org/wiki/Digital_root
     * @param num
     * @return
     */
    public int addDigitsFast(int num) {
        if (num == 0) return 0;
        return 1 + ((num-1) % 9);
    }
}
