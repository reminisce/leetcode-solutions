import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 6/5/16.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 * Hint:
 *
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */

public class StrobogrammaticNumberII {

    public static void main(String[] args) {
        StrobogrammaticNumberII app = new StrobogrammaticNumberII();
        //List<String> res = app.findStrobogrammatic(3);
        List<String> res = app.findStrobogrammaticIterative(5);
        for (String str : res) {
            System.out.println(str);
        }
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if (n % 2 == 0) {
            findStrobogrammaticHelper(n, "", res);
        } else {
            findStrobogrammaticHelper(n, "0", res);
            findStrobogrammaticHelper(n, "1", res);
            findStrobogrammaticHelper(n, "8", res);
        }
        return res;
    }

    private void findStrobogrammaticHelper(int n, String str, List<String> res) {
        if (str.length() == n) {
            if (n == 1 || str.charAt(0) != '0') {
                res.add(str);
            }
            return;
        }

        findStrobogrammaticHelper(n, "0" + str + "0", res);
        findStrobogrammaticHelper(n, "1" + str + "1", res);
        findStrobogrammaticHelper(n, "6" + str + "9", res);
        findStrobogrammaticHelper(n, "8" + str + "8", res);
        findStrobogrammaticHelper(n, "9" + str + "6", res);
    }

    public List<String> findStrobogrammaticIterative(int n) {
        Queue<String> queue = new LinkedList<>();
        if (n % 2 == 0) {
            queue.offer("");
        } else {
            queue.offer("0");
            queue.offer("1");
            queue.offer("8");
        }

        while (queue.peek().length() < n) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String str = queue.poll();
                queue.offer("0" + str + "0");
                queue.offer("1" + str + "1");
                queue.offer("6" + str + "9");
                queue.offer("8" + str + "8");
                queue.offer("9" + str + "6");
            }
        }

        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (n > 1 && str.charAt(0) == '0') continue;
            res.add(str);
        }

        return res;
    }
}
