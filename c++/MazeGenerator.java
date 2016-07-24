import java.util.*;

/**
 * Created on 7/23/16.
 * Consider a maze is a rectangular grid like
 * a chessboard. Generate the maze using
 * DFS/Recursive backtracking.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_backtracker
 */

public class MazeGenerator {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int TOP_WALL_IDX = 0;
    private final int BOTTOM_WALL_IDX = 1;
    private final int LEFT_WALL_IDX = 2;
    private final int RIGHT_WALL_IDX = 3;


    public static void main(String[] args) {
        int m = 5, n = 5;
        MazeCell[][] maze = new MazeCell[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maze[i][j] = new MazeCell();
            }
        }
        MazeGenerator app = new MazeGenerator();
        app.generate(maze, 0, 0);
        app.printMaze(maze);
    }

    private void printMaze(MazeCell[][] maze) {
        int m = maze.length;
        int n = maze[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(maze[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    public static class MazeCell {
        /**
         * Bit 1 means wall
         * walls.get(0) means top wall
         * walls.get(1) means bottom wall
         * walls.get(2) means left wall
         * walls.get(3) means right wall
         */
        public MazeCell() {
            walls = new BitSet(4);
            walls.set(0, 4);
        }

        public String toString() {
            return walls.toString();
        }

        public BitSet walls;
    }

    /**
     * Basic idea is to traverse all the maze cells.
     * Start from an initial one, choose one of its unvisited
     * neighbors and break walls between these two cells. Push
     * the current cell into a stack and treat the neighboring
     * cell as the current cell to repeat the process.
     * If no neighbors are unvisited, pop the cell from the
     * stack and repeat the above process until all the cells
     * are visited.
     * @param maze maze cell grid
     * @param i initial cell row
     * @param j initial cell col
     */
    public void generate(MazeCell[][] maze, int i, int j) {
        Random random = new Random(3);
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Stack<Integer> coordinates = new Stack<>();
        int count = 1;
        int curRow = i;
        int curCol = j;
        visited[curRow][curCol] = true;
        while (count < m * n) {
            List<Integer> neighbors = new ArrayList<>();
            for (int[] dir : dirs) {
                int r = curRow + dir[0];
                int c = curCol + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c]) continue;
                neighbors.add(r*n+c);
            }

            if (!neighbors.isEmpty()) {
                coordinates.push(curRow*n+curCol);
                int idx = random.nextInt(neighbors.size());
                int r = neighbors.get(idx) / n;
                int c = neighbors.get(idx) % n;
                removeWall(maze, curRow, curCol, r, c);
                curRow = r;
                curCol = c;
                visited[curRow][curCol] = true;
                ++count;
            } else if (!coordinates.isEmpty()){
                curRow = coordinates.peek() / n;
                curCol = coordinates.peek() % n;
                coordinates.pop();
            }
        }
    }

    /**
     * Remove the wall between cell(i1, j1) and cell(i2, j2)
     * @param maze
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     */
    private void removeWall(MazeCell[][] maze, int i1, int j1, int i2, int j2) {
        if (j1 == j2) {
            if (i1 - i2 == 1) {
                maze[i1][j1].walls.set(TOP_WALL_IDX, false);
                maze[i2][j2].walls.set(BOTTOM_WALL_IDX, false);
            } else if (i2 - i1 == 1) {
                maze[i1][j1].walls.set(BOTTOM_WALL_IDX, false);
                maze[i2][j2].walls.set(TOP_WALL_IDX, false);
            }
        } else if (i1 == i2) {
            if (j1 - j2 == 1) {
                maze[i1][j1].walls.set(LEFT_WALL_IDX, false);
                maze[i2][j2].walls.set(RIGHT_WALL_IDX, false);
            } else if (j2 - j1 == 1) {
                maze[i1][j1].walls.set(RIGHT_WALL_IDX, false);
                maze[i2][j2].walls.set(LEFT_WALL_IDX, false);
            }
        }
    }
}
