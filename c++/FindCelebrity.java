import java.util.Stack;

/**
 * Created on 6/1/16.
 * Given n people at a party with id 0, 1,..., n-1, find the celebrity if there is one.
 * The celebrity's definition is that s/he is known by everyone else, but s/he doesn't know
 * anyone.
 *
 * You can only call boolean knows(i, j) to determine whether i knows j.
 *
 * Use as few as call to the function to find the celebrity.
 */

public class FindCelebrity {

    public static void main(String[] args) {
        FindCelebrity app = new FindCelebrity();
        int n = 6;
        boolean[][] knows = new boolean[n][n];
        app.generateMatrix(knows);
        int celebrity = app.findCelebrity(n, knows);
        if (celebrity >= 0) {
            System.out.println("Celebrity is " + celebrity);
        } else {
            System.out.println("There is no celebrity at the party");
        }
    }

    private void generateMatrix(boolean[][] knows) {
        knows[0][0] = true;
        knows[0][1] = true;
        knows[0][2] = false;
        knows[0][3] = true;
        knows[0][4] = false;
        knows[0][5] = true;

        knows[1][0] = false;
        knows[1][1] = true;
        knows[1][2] = true;
        knows[1][3] = true;
        knows[1][4] = false;
        knows[1][5] = true;

        knows[2][0] = false;
        knows[2][1] = true;
        knows[2][2] = true;
        knows[2][3] = true;
        knows[2][4] = true;
        knows[2][5] = false;

        knows[3][0] = false;
        knows[3][1] = false;
        knows[3][2] = false;
        knows[3][3] = true;
        knows[3][4] = false;
        knows[3][5] = false;

        knows[4][0] = false;
        knows[4][1] = false;
        knows[4][2] = false;
        knows[4][3] = true;
        knows[4][4] = true;
        knows[4][5] = false;

        knows[5][0] = false;
        knows[5][1] = false;
        knows[5][2] = false;
        knows[5][3] = true;
        knows[5][4] = true;
        knows[5][5] = true;
    }

    /**
     * If we build a digraph, we need to find the node with n-1 inward degree, 0 outward degree.
     * The complexity is O(n^2). Alternatively, we can pick two people and ask whether i knows j.
     * If i knows j, i is not the celebrity, and j is a candidate; else, i is a candidate and discard
     * j. Keep repeating the process until there is only one person left, which is the candidate.
     * Then, ask whether the other n-1 people know him/her, and whether s/he doesn't know the other n-1 people.
     * If either of the condition is not true, s/he is not a celebrity.
     * @param n
     * @param knows
     * @return
     */
    public int findCelebrity(int n, boolean[][] knows) {
        this.knows = knows;
        int candidate = 0;
        for (int i = 0; i < n; ++i) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        // Verify the candidate
        for (int i = 0; i < n; ++i) {
            if (i == candidate) continue;
            if (!knows(i, candidate)) return -1;
            if (knows(candidate, i)) return -1;
        }

        return candidate;
    }

    private boolean knows(int i, int j) {
        return knows[i][j];
    }

    private boolean[][] knows;
}
