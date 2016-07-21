import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/21/16.
 * Given an absolute path for a file (Unix-style),
 * simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * click to show corner cases.
 *
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */

public class SimplifyPath {
    public String simplifyPath(String path) {
        List<String> dirs = new ArrayList<>();
        int i = 0;
        while (i < path.length()) {
            if (path.charAt(i) == '/') {
                ++i;
                continue;
            }
            int j = i;
            while (j < path.length() && path.charAt(j) != '/') ++j;
            if (path.substring(i, j).equals("..")) {
                if (!dirs.isEmpty()) {
                    dirs.remove(dirs.size()-1);
                }
            } else if (!path.substring(i, j).equals(".")) {
                dirs.add("/" + path.substring(i, j));
            }
            i = j;
        }

        if (dirs.isEmpty()) return "/";

        StringBuilder str = new StringBuilder();
        for (String dir : dirs) {
            str.append(dir);
        }
        return str.toString();
    }
}
