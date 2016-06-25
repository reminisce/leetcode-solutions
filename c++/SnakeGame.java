import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created on 6/20/16.
 * Design a Snake game that is played on a device with screen
 * size = width x height. Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order.
 * When a snake eats the food, its length and the game's score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second
 * food will not appear until the first food was eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it
 * will not appear on a block occupied by the snake.
 *
 * Example:
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right
 * after that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

public class SnakeGame {

    public static void main(String[] args) {
        int[][] food = {{1, 2}, {0, 1}, {0, 0}};
        SnakeGame snake = new SnakeGame(3, 3, food);
        String step = "R";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "D";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "R";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "U";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "L";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "L";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "D";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "R";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();

        step = "R";
        System.out.println("Moved " + step + ", Score = " + snake.move(step));
        snake.printSnake();
    }

    /**
     * Initialize your data structure here.
     * @param width - screen width
     * @param height - screen height
     * @param food - A list of food positions
     * E.g food = [[1,1], [1,0]] means the first food is
     * positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.nextFoodIdx = 0;
        this.grid = new int[height][width];
        this.grid[0][0] = 1; // 1 means the cell is occupied by the snake's body
        this.snake = new LinkedList<>();
        this.snake.addFirst(0);
    }

    /**
     * Moves the snake.
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        int headCol = getCol(snake.getFirst());
        int headRow = getRow(snake.getFirst());
        if (direction.equals("U")) {
            --headRow;
        } else if (direction.equals("L")) {
            --headCol;
        } else if (direction.equals("R")) {
            ++headCol;
        } else if (direction.equals("D")) {
            ++headRow;
        } else {
            return nextFoodIdx;
        }

        if (headCol < 0 || headCol >= width || headRow < 0
                || headRow >= height || grid[headRow][headCol] == 1) {
            return -1;
        } else if (nextFoodIdx < food.length && headRow == food[nextFoodIdx][0] && headCol == food[nextFoodIdx][1]) {
            ++nextFoodIdx;
            grid[headRow][headCol] = 1;
            snake.addFirst(getPosition(headRow, headCol));
            return nextFoodIdx;
        } else {
            int tailCol = getCol(snake.getLast());
            int tailRow = getRow(snake.getLast());
            grid[tailRow][tailCol] = 0;
            grid[headRow][headCol] = 1;
            snake.removeLast();
            snake.addFirst(getPosition(headRow, headCol));
            return nextFoodIdx;
        }
    }

    public void printSnake() {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    private int getCol(int pos) {
        return pos % width;
    }

    private int getRow(int pos) {
        return pos / width;
    }

    private int getPosition(int row, int col) {
        return row * width + col;
    }

    private LinkedList<Integer> snake;
    private int width;
    private int height;
    private int nextFoodIdx;
    private int[][] food;
    private int[][] grid;
}
