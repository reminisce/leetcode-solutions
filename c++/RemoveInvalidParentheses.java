import java.util.*;

/**
 * Created on 7/19/16.
 * Remove the minimum number of invalid
 * parentheses in order to make the input
 * string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 */

public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);

        // We remove one char each time and put
        // the new string into a queue for validation.
        // This variable controls how deep we want
        // to bfs the remaining strings. We should
        // stop bfs further if a valid string
        // is found at the current level.
        boolean found = false;

        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (visited.contains(str)) continue;
            visited.add(str);
            if (isValid(str)) {
                res.add(str);
                found = true;
            }
            if (found) continue;
            for (int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) != '(' && str.charAt(i) != ')') continue;
                String t = str.substring(0, i) + str.substring(i+1);
                if (!visited.contains(t)) {
                    queue.offer(t);
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') ++cnt;
            else if (s.charAt(i) == ')') {
                if (--cnt < 0) return false;
            }
        }
        return cnt == 0;
    }
}
