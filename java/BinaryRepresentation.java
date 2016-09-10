import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/12/16.
 * Given two numbers a and b, find all the binary
 * representations with length a and b number of 1's,
 * and output in decimal in sorted order. (G)
 * Example:
 * input a = 3, b = 1.
 * [001, 010, 100]
 * output: [1, 2, 4].
 */
public class BinaryRepresentation {

    public static void main(String[] args) {
        int a = 6;
        int b = 3;
        BinaryRepresentation app = new BinaryRepresentation();
        List<Integer> res = app.getBinaryRepresentations(a, b);
        System.out.println(res.toString());
    }

    public List<Integer> getBinaryRepresentations(int a, int b) {
        List<Integer> res = new ArrayList<>();
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
