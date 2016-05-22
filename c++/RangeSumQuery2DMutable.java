/**
 * Created on 5/21/16.
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its
 * upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and
 * (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 *
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

class SegmentTreeNode2D {
    int sum;
    int topRow;
    int bottomRow;
    int leftCol;
    int rightCol;
    SegmentTreeNode2D[] children = new SegmentTreeNode2D[4];
}

public class RangeSumQuery2DMutable {

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        RangeSumQuery2DMutable rangeSumQuery2DMutable = new RangeSumQuery2DMutable(matrix);
        System.out.println("sumRegion(2, 1, 4, 3) = " + rangeSumQuery2DMutable.sumRegion(2, 1, 4, 3));
        System.out.println("update(3, 2, 2)");
        rangeSumQuery2DMutable.update(3, 2, 2);
        System.out.println("sumRegion(2, 1, 4, 3) = " + rangeSumQuery2DMutable.sumRegion(2, 1, 4, 3));
    }

    public RangeSumQuery2DMutable(int[][] matrix) {
        this.matrix = matrix;
        buildSegmentTree2D();
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRegionHelper(root, row1, col1, row2, col2);
    }

    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        updateHelper(root, row, col, diff);
    }

    private void updateHelper(SegmentTreeNode2D node, int row, int col, int diff) {
        if (node == null) return;
        if (node.topRow <= row && node.bottomRow >= row && node.leftCol <= col && node.rightCol >= col) {
            node.sum += diff;
            for (SegmentTreeNode2D childNode : node.children) {
                if (childNode != null) {
                    updateHelper(childNode, row, col, diff);
                }
            }
        }
    }

    private int sumRegionHelper(SegmentTreeNode2D node, int row1, int col1, int row2, int col2) {
        if (node == null) return 0;
        if (row1 > node.bottomRow || row2 < node.topRow || col1 > node.rightCol || col2 < node.leftCol) return 0;
        if (row1 <= node.topRow && row2 >= node.bottomRow && col1 <= node.leftCol && col2 >= node.rightCol) {
            return node.sum;
        }
        int sum = 0;
        for (SegmentTreeNode2D childNode : node.children) {
            if (null != childNode) {
                sum += sumRegionHelper(childNode, row1, col1, row2, col2);
            }
        }
        return sum;
    }

    private void buildSegmentTree2D() {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        root = buildSegmentTree2DHelper(0, 0, m-1, n-1);
    }

    private SegmentTreeNode2D buildSegmentTree2DHelper(int row1, int col1, int row2, int col2) {
        if (row1 > row2 || col1 > col2) return null;
        SegmentTreeNode2D node = new SegmentTreeNode2D();
        node.sum = 0;
        node.topRow = row1;
        node.bottomRow = row2;
        node.leftCol = col1;
        node.rightCol = col2;
        if (row1 == row2 && col1 == col2) {
            node.sum = matrix[row1][col1];
        } else {
            int midRow = row1 + (row2 - row1) / 2;
            int midCol = col1 + (col2 - col1) / 2;
            node.children[0] = buildSegmentTree2DHelper(row1, col1, midRow, midCol);
            node.children[1] = buildSegmentTree2DHelper(row1, midCol+1, midRow, col2);
            node.children[2] = buildSegmentTree2DHelper(midRow+1, col1, row2, midCol);
            node.children[3] = buildSegmentTree2DHelper(midRow+1, midCol+1, row2, col2);
            for (SegmentTreeNode2D childNode : node.children) {
                if (childNode != null) {
                    node.sum += childNode.sum;
                }
            }
        }
        return node;
    }

    private int[][] matrix;
    private SegmentTreeNode2D root;
}
