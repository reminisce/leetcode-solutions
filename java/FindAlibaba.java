import java.util.Arrays;

/**
 * (F)
 * Created on 9/6/16.
 * Alibaba is a monster hiding in one of
 * the n caves. It can move +/- 1 to
 * the neighboring cave once a day. Given
 * a sequence of guesses of the hiding places
 * of Alibaba, where each element represents
 * the guessing cave id per day.
 * Check whether the sequence is a winning
 * strategy to catch Alibaba.
 */

public class FindAlibaba {

    public static void main(String[] args) {
        FindAlibaba app = new FindAlibaba();
        int n = 4;
        int[] guess = {0, 1, 2, 3, 3, 2, 1, 0};
        System.out.println(app.canWin(n, guess));
    }

    /**
     * @param n number of caves
     * @param guess guess sequence
     * @return
     */
    public boolean canWin(int n, int[] guess) {
        int m = guess.length;
        // survived[i][j] represents whether the monster
        // survived on the i-th day if guess is the j-th cave
        boolean[][] survived = new boolean[m][n];

        // fill in the first day
        for (int j = 0; j < n; ++j) {
            survived[0][j] = true;
        }
        survived[0][guess[0]] = false;

        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // check whether it survived in the left neighbor of j
                // in the previous round
                boolean left = (j > 0 && survived[i-1][j-1]);
                // check whether it survived in the right neighbor of j
                // in the previous round
                boolean right = (j < n-1 && survived[i-1][j+1]);

                // determine whether it could survive this round in j
                survived[i][j] = (left || right) && guess[i] != j;
            }
        }

        print(survived);

        for (int j = 0; j < n; ++j) {
            if (survived[m-1][j]) {
                return false; // cannot win
            }
        }

        return true;
    }

    void print(boolean[][] survived) {
        for (boolean[] r : survived) {
            System.out.println(Arrays.toString(r));
        }
    }
}
