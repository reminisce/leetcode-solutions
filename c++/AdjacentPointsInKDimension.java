import java.util.ArrayList;

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
        ArrayList<ArrayList<Integer>> points = app.getAdjacentPoints(point);
        for (ArrayList<Integer> p : points) {
            System.out.println(p.toString());
        }
    }

    public ArrayList<ArrayList<Integer>> getAdjacentPoints(int[] point) {
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        if (point.length == 0) return points;
        ArrayList<Integer> adjacentPoint = new ArrayList<>();
        for (int i = 0; i < point.length; ++i) adjacentPoint.add(point[i]);
        getAdjacentPoints(0, point, adjacentPoint, points, false);
        return points;
    }

    private void getAdjacentPoints(int d, int[] originalPoint, ArrayList<Integer> adjacentPoint,
                                   ArrayList<ArrayList<Integer>> adjacentPoints, boolean notAllZeroes) {
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
}
