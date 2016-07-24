import java.util.*;

/**
 * Created on 7/23/16.
 * 1. Consider a maze is a rectangular grid like
 * a chessboard. Generate the maze using
 * DFS/Recursive backtracking.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_backtracker
 *
 * 2. Given a maze, find the shortest path
 * from an entry cell to an exit cell.
 * Do BFS find the shortest path length.
 * During the process, we mark cells
 * by layer numbers of BFS propagation.
 * When we reach the exit cell, we track
 * back layer number of cells in descending
 * order util reach the entry cell. At
 * the point, we find the shortest path.
 */

public class MazeUtil {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int TOP_WALL_IDX = 0;
    private final int BOTTOM_WALL_IDX = 1;
    private final int LEFT_WALL_IDX = 2;
    private final int RIGHT_WALL_IDX = 3;


    public static void main(String[] args) {
        int m = 10, n = 10;
        MazeCell[][] maze = new MazeCell[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maze[i][j] = new MazeCell();
            }
        }
        MazeUtil app = new MazeUtil();
        app.generate(maze, 0, 0);
        System.out.println("Maze plot is");
        app.printMaze(maze);
        int entryRow = 0;
        int entryCol = 0;
        int exitRow = m - 1;
        int exitCol = n - 1;
        List<Coordinate> path = app.findShortestPath(maze, entryRow, entryCol, exitRow, exitCol);
        System.out.println("Path from (" + entryRow + ", " + entryCol + ") to ("
        + exitRow + ", " + exitCol + ")");
        System.out.println(path.toString());
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

    public static class Coordinate {
        public Coordinate(int r, int c) {
            row = r;
            col = c;
        }
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
        public int row;
        public int col;
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
        Random random = new Random();
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
     * Find the shortest path from cell(i1, j1) to cell(i2, j2)
     * @param maze
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     * @return
     */
    public List<Coordinate> findShortestPath(MazeCell[][] maze, int i1, int j1, int i2, int j2) {
        List<Coordinate> path = new ArrayList<>();
        int m = maze.length;
        int n = maze[0].length;
        int[][] markers = new int[m][n];
        Queue<Coordinate> queue = new LinkedList<>();
        int layer = 1;
        markers[i1][j1] = layer;
        queue.offer(new Coordinate(i1, j1));
        boolean found = false;
        while (!queue.isEmpty()) {
            ++layer;
            int sz = queue.size();
            for (int k = 0; k < sz; ++k) {
                Coordinate curCoord = queue.poll();
                if (curCoord.row == i2 && curCoord.col == j2) {
                    found = true;
                    break;
                }
                List<Coordinate> neighbors = getNeighbors(maze, markers, curCoord.row, curCoord.col);
                for (Coordinate neighbor : neighbors) {
                    markers[neighbor.row][neighbor.col] = layer;
                    queue.offer(neighbor);
                }
            }
            if (found) break;
        }
        printMarkers(markers);

        int curRow = i2;
        int curCol = j2;
        path.add(new Coordinate(i2, j2));
        while (curRow != i1 || curCol != j1) {
            for (int[] dir : dirs) {
                int r = curRow + dir[0];
                int c = curCol + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || markers[r][c] == 0) continue;
                if (markers[r][c] + 1 == markers[curRow][curCol]) {
                    path.add(new Coordinate(r, c));
                    curRow = r;
                    curCol = c;
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

    private void printMarkers(int[][] markers) {
        System.out.println("Marker matrix is ");
        for (int[] mark : markers) {
            System.out.println(Arrays.toString(mark));
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

    /**
     * Given (i, j), find its neighboring cells that
     * have not been marked.
     * @param maze
     * @param markers
     * @param j
     * @return
     */
    private List<Coordinate> getNeighbors(MazeCell[][] maze, int[][] markers, int i, int j) {
        List<Coordinate> neighbors = new ArrayList<>();
        if (null == maze || maze.length == 0 || maze[0].length == 0
                || i < 0 || i >= maze.length || j < 0 || j >= maze[0].length) {
            return neighbors;
        }

        int m = maze.length;
        int n = maze[0].length;

        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r < 0 || r >= m || c < 0 || c >= n || markers[r][c] > 0 || hasWall(maze, i, j, r, c)) continue;
            neighbors.add(new Coordinate(r, c));
        }
        return neighbors;
    }

    /**
     * Find out whether there is a wall between cell(i1, j1) and cell(i2, j2).
     * @param maze
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     * @return
     */
    private boolean hasWall(MazeCell[][] maze, int i1, int j1, int i2, int j2) {
        if (i1 == i2) {
            if (j1 - j2 == 1) {
                return maze[i1][j1].walls.get(LEFT_WALL_IDX) || maze[i2][j2].walls.get(RIGHT_WALL_IDX);
            } else if (j2 - j1 == 1) {
                return maze[i1][j1].walls.get(RIGHT_WALL_IDX) || maze[i2][j2].walls.get(LEFT_WALL_IDX); 
            }
        } else if (j1 == j2) {
            if (i1 - i2 == 1) {
                return maze[i1][j1].walls.get(TOP_WALL_IDX) || maze[i2][j2].walls.get(BOTTOM_WALL_IDX);
            } else if (i2 - i1 == 1) {
                return maze[i1][j1].walls.get(BOTTOM_WALL_IDX) || maze[i2][j2].walls.get(TOP_WALL_IDX);
            }
        }
        return true;
    }
}
