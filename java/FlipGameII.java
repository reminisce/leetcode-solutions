/**
 * Created on 6/5/16.
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only these two characters: + and -, you and your
 * friend take turns to flip two consecutive "++" into "--". The game ends when a
 * person can no longer make a move and therefore the other person will be the winner.
 *
 * Write a function to determine if the starting player can guarantee a win.
 *
 * For example, given s = "++++", return true. The starting player can guarantee a
 * win by flipping the middle "++" to become "+--+".
 *
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */

public class FlipGameII {

    /**
     * Complexity analysis:
     * For the worst case, the string is "++++++...+++++".
     * T(N) = T(N-2) + T(N-3) + [T(2) + T(N-4)] + [T(3) + T(N-5)] +...+ [T(N-4] + T(2)] + T(N-3) + T(N-2)
     *      = 2 * sum[T(i)], where i = 2,...,N-2
     * So T(N) = 2^(N-1).
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        for (int i = 0; i < s.length()-1; ++i) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+' && !canWin(s.substring(0, i) + "--" + s.substring(i+2))) {
                return true;
            }
        }
        return false;
    }

}
