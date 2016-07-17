import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 5/24/16.
 * Given a point in k dimension, find all its neighboring points.
 * In two dimension, point (x, y)'s neighbors are
 * (x-1, y-1), (x-1, y), (x-1, y+1),
 * (x, y-1), (x, y+1), (x+1, y-1), (x+1, y), (x+1, y+1).
 */
public class AdjacentPointsInKDimension {

    public static void main(String[] args) {
        AdjacentPointsInKDimension app = new AdjacentPointsInKDimension();
        int[] point = {0, 0, 0, 0};
        System.out.println("Recursive");
        List<List<Integer>> points = app.getAdjacentPointsRecursive(point);
        System.out.println(points.toString());

        System.out.println("Iterative");
        LinkedList<List<Integer>> points2 = app.getAdjacentPointsIterative(point);
        System.out.println(points2.toString());
    }

    public List<List<Integer>> getAdjacentPointsRecursive(int[] point) {
        List<List<Integer>> points = new ArrayList<>();
        if (point.length == 0) return points;
        List<Integer> adjacentPoint = new ArrayList<>();
        for (int i = 0; i < point.length; ++i) adjacentPoint.add(point[i]);
        getAdjacentPoints(0, point, adjacentPoint, points, false);
        return points;
    }

    private void getAdjacentPoints(int d, int[] originalPoint, List<Integer> adjacentPoint,
                                   List<List<Integer>> adjacentPoints, boolean notAllZeroes) {
        if (d == adjacentPoint.size()) {
            if (!notAllZeroes) return; // it's the original point
            adjacentPoints.add(new ArrayList<>(adjacentPoint));
            return;
        }

        for (int i = -1; i <= 1; ++i) {
            adjacentPoint.set(d, originalPoint[d] + i);
            getAdjacentPoints(d+1, originalPoint, adjacentPoint, adjacentPoints, (i != 0 || notAllZeroes));
        }
    }

    public LinkedList<List<Integer>> getAdjacentPointsIterative(int[] point) {
        LinkedList<List<Integer>> points = new LinkedList<>();
        if (point.length == 0) return points;
        List<Integer> adjacentPoint = new ArrayList<>();
        for (int i = 0; i < point.length; ++i) adjacentPoint.add(point[i]);
        points.add(adjacentPoint);
        for (int d = 0; d < point.length; ++d) {
            int n = points.size();
            for (int i = 0; i < n; ++i) {
                adjacentPoint = points.pollFirst();
                for (int j = -1; j <= 1; ++j) {
                    adjacentPoint.set(d, adjacentPoint.get(d)+j);
                    points.add(new ArrayList<>(adjacentPoint));
                    adjacentPoint.set(d, adjacentPoint.get(d)-j);
                }
            }
        }
        return points;
    }
}
