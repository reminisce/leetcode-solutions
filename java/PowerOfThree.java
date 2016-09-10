/**
 * Created on 6/5/16.
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n > 1) {
            if (n % 3 != 0) return false;
            n /= 3;
        }
        return true;
    }

    /**
     * 3^19=1162261467, in [0, 2^31]
     * @param n
     * @return
     */
    boolean isPowerOfThree2(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }
}
