/**
 * Created on 6/5/16.
 * There is a fence with n posts, each post can be painted with one of the k colors.
 *
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 * n and k are non-negative integers.
 */

public class PaintFence {
    public static void main(String[] args) {
        PaintFence app = new PaintFence();
        int n = 3, k = 2;
        System.out.println("There are " + app.numWays(n, k) + " ways to paint"
        + n + " posts with " + k + " colors.");
    }

    /**
     * For n = 0, there are 0 ways to paint.
     * For n = 1, there are k ways to paint.
     * For n = 2, we consider two situations:
     * 1. To paint posts 1 and 2 differently, there are k ways to paint the post 1,
     *    and k-1 ways to paint the post 2 with different colors, so in total k*(k-1).
     * 2. To paint the posts 1 and 2 with the same color, there are k ways to paint post 1,
     *    and only 1 way to paint post 2.
     * So in total, it's k*(k-1) + k.
     * For n = 3, there are in total k*(k-1) + k ways to paint the first two posts,
     * 1. To paint post 3 differently, there are k-1 to choose, so there are [k*(k-1)+k]*(k-1)] ways.
     * 2. To paint posts 2 and 3 with the same color, post 2 cannot have the same color as post 1.
     *    We have known that there are k*(k-1) ways to paint post 2 differently from post 1, so
     *    there are k*(k-1) ways to paint post 3 with the same color as post 2.
     * So in total, there are [k*(k-1)+k]*(k-1)] + k*(k-1) ways to paint three posts.
     * Keep going...
     * Denote s[i] as the number of painting up to post i with the same color as post i-1
     * Denote d[i] as the number of painting up to post i with different color from post i-1
     * d[i] = (k-1)*(s[i-1]+d[i-1])
     * s[i] = d[i-1]
     * @param n
     * @param k
     * @return
     */
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        int sameWays = 0; // paint post i with the same color as i-1
        int diffWays = k; // paint post i with the different color as i-1
        for (int i = 2; i <= n; ++i) {
            int tmp = diffWays;
            diffWays = (sameWays + diffWays) * (k-1);
            sameWays = tmp;
        }

        return sameWays + diffWays;
    }
}
