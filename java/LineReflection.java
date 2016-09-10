import java.util.HashMap;
import java.util.HashSet;

/**
 * Created on 6/26/16.
 * Given n points on a 2D plane, find if there is such a
 * line parallel to y-axis that reflect the given set of points.
 *
 * Example 1:
 * Given points = [[1,1],[-1,1]], return true.
 *
 * Example 2:
 * Given points = [[1,1],[-1,-1]], return false.
 *
 * Follow up:
 * Could you do better than O(n2)?
 *
 * Hint:
 *
 * Find the smallest and largest x-value for all points.
 * If there is a line then it should be at x = (minX + maxX) / 2.
 * For each point, make sure that it has a reflected point in the opposite side.
 */

public class LineReflection {

    public static void main(String[] args) {
        int[][] points = {{1, 1,}, {-1, 1}, {0, 7}, {0, 8}, {2, 3}, {-2, 3}};
        LineReflection app = new LineReflection();
        System.out.println(app.isRelfected(points));
    }

    public boolean isRelfected(int[][] points) {
        if (null == points || points.length == 0) return true;

        // map x to a list of y for all the points
        HashMap<Integer, HashSet<Integer>> pointMap = new HashMap<>();
        int minX = points[0][0];
        int maxX = points[0][0];
        for (int i = 0; i < points.length; ++i) {
            if (!pointMap.containsKey(points[i][0])) {
                pointMap.put(points[i][0], new HashSet<>());
            }
            pointMap.get(points[i][0]).add(points[i][1]);
            minX = Math.min(minX, points[i][0]);
            maxX = Math.max(maxX, points[i][0]);
        }

        int x2 = minX + maxX; // x = x2 / 2 is the reflection line if there is one
        for (int[] point : points) {
            if (point[0] * 2 == x2) continue;
            int x = x2 - point[0];
            if (!pointMap.containsKey(x) || !pointMap.get(x).contains(point[1])) return false;
        }
        return true;
    }
}
