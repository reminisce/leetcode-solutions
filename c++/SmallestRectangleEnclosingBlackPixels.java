import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 7/5/16.
 * An image is represented by a binary matrix with 0
 * as a white pixel and 1 as a black pixel. The black
 * pixels are connected, i.e., there is only one black
 * region. Pixels are connected horizontally and vertically.
 * Given the location (x, y) of one of the black pixels,
 * return the area of the smallest (axis-aligned)
 * rectangle that encloses all black pixels.
 *
 * For example, given the following image:
 *
 * [
 * "0010",
 * "0110",
 * "0100"
 * ]
 * and x = 0, y = 2,
 *
 * Return 6.
 */

public class SmallestRectangleEnclosingBlackPixels {

    public static void main(String[] args) {
        int[][] image = {{0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0}};
        SmallestRectangleEnclosingBlackPixels app = new SmallestRectangleEnclosingBlackPixels();
        System.out.println(app.minAreaBinarySearch(image, 0, 2));
    }

    /**
     * If there is only one black pixel area, the projected black area in row and column
     * determines the size of the smallest enclosing rectangle. So we can do binary
     * search to find the left-most column that has black pixel(s). To check whether
     * a column has black pixels is O(m), and the binary search along the column is log(n).
     * So in total, it's mlogn + nlogm.
     * One trick to use here is that when we need to find the right-most column with black pixels,
     * we in fact find the left-most column with white pixels. So we can reuse the function.
     */
    public int minAreaBinarySearch(int[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        int left = binarySearch(image, 0, y, 0, m, true, true);
        int right = binarySearch(image, y+1, n, 0, m, true, false);
        int top = binarySearch(image, 0, x, left, right, false, true);
        int bottom = binarySearch(image, x+1, m, left, right, false, false);
        return (right - left) * (bottom - top);
    }

    /**
     * binary search for leftmost black and white pixels in row
     * binary search for topmost black and white pixels in column
     * @param image input image
     * @param low binary search lowest index
     * @param high binary search highest index
     * @param min
     * @param max
     * @param isHorizontal
     * @param searchForBlackPixel
     * @return
     */
    private int binarySearch(int[][] image, int low, int high, int min, int max,
                             boolean isHorizontal, boolean searchForBlackPixel) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean foundBlackPixel = false;
            for (int k = min; k < max; ++k) {
                if ((isHorizontal? image[k][mid] : image[mid][k]) == 1) {
                    foundBlackPixel = true;
                    break;
                }
            }
            if (searchForBlackPixel == foundBlackPixel) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int minAreaBFS(int[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;
        int m = image.length, n = image[0].length;
        int[] dims = new int[4]; // left, right, top, bottom
        dims[0] = x;
        dims[1] = x;
        dims[2] = y;
        dims[3] = y;
        boolean[] visited = new boolean[m * n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x*n+y);

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int i = queue.peek() / n;
            int j = queue.peek() % n;
            visited[queue.peek()] = true;
            queue.poll();
            dims[0] = Math.min(dims[0], i);
            dims[1] = Math.max(dims[1], i);
            dims[2] = Math.min(dims[2], j);
            dims[3] = Math.max(dims[3], j);
            for (int[] dir : dirs) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii < 0 || ii >= m || jj < 0 || jj >= n || visited[ii*n+jj] || image[ii][jj] == 0) continue;
                queue.offer(ii*n+jj);
            }
        }

        return (dims[1]-dims[0]+1) * (dims[3]-dims[2]+1);
    }
}
