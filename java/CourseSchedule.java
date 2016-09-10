import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 7/7/16.
 * There are a total of n courses you
 * have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites,
 * for example to take course 0 you have
 * to first take course 1, which is expressed
 * as a pair: [0,1]
 *
 * Given the total number of courses and a list
 * of prerequisite pairs, is it possible for
 * you to finish all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take.
 * To take course 1 you should have finished
 * course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take.
 * To take course 1 you should have finished
 * course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 *
 * Note:
 * The input prerequisites is a graph represented
 * by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 *
 * Hints:
 * This problem is equivalent to finding if a
 * cycle exists in a directed graph. If a cycle
 * exists, no topological ordering exists and
 * therefore it will be impossible to take all courses.
 * Topological Sort via DFS - A great video
 * tutorial (21 minutes) on Coursera explaining
 * the basic concepts of Topological Sort.
 * Topological sort could also be done via BFS.
 */

public class CourseSchedule {

    public static void main(String[] args) {
        int[][] pre = {{5, 8}, {3, 5}, {1, 9}, {4, 5}, {0, 2}, {1, 9}, {7, 8}, {4, 9}};
        int numCourses = 10;
        CourseSchedule app = new CourseSchedule();
        System.out.println("DFS: " + app.canFinishDFS(numCourses, pre));
        System.out.println("BFS: " + app.canFinishBFS(numCourses, pre));
    }

    /**
     * DFS solution
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        populateGraph(prerequisites, graph);
        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            if (!visited[i] && !dfs(graph, visited, onStack, i)) return false;
        }
        return true;
    }

    private boolean dfs(HashMap<Integer, HashSet<Integer>> graph, boolean[] visited,
                        boolean[] onStack, int curCourse) {
        if (!graph.containsKey(curCourse)) return true;
        if (visited[curCourse]) return true;
        if (onStack[curCourse]) return false;
        visited[curCourse] = true;
        onStack[curCourse] = true;
        for (int course : graph.get(curCourse)) {
            if (!dfs(graph, visited, onStack, course)) return false;
        }
        onStack[curCourse] = false;
        return true;
    }

    /**
     * BFS solution: leaf pruning
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new HashSet<>());
            }
            if (!graph.get(edge[0]).contains(edge[1])) {
                graph.get(edge[0]).add(edge[1]);
                ++inDegree[edge[1]];
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; ++i) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            if (!graph.containsKey(curCourse)) continue;
            for (int course : graph.get(curCourse)) {
                if (--inDegree[course] == 0) {
                    queue.offer(course);
                }
            }
        }

        for (int i = 0; i < inDegree.length; ++i) {
            if (inDegree[i] != 0) return false;
        }

        return true;
    }

    private void populateGraph(int[][] prerequisites, HashMap<Integer, HashSet<Integer>> graph) {
        if (null == graph) return;
        for (int[] edge : prerequisites) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new HashSet<>());
            }
            graph.get(edge[0]).add(edge[1]);
        }
    }
}
