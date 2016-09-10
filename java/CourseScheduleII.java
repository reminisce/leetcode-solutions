import java.util.*;

/**
 * Created on 7/7/16.
 * There are a total of n courses you have to
 * take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example
 * to take course 0 you have to first take course
 * 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list
 * of prerequisite pairs, return the ordering
 * of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you
 * just need to return one of them. If it is
 * impossible to finish all courses, return an empty array.
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To
 * take course 1 you should have finished course
 * 0. So the correct course order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take
 * course 3 you should have finished both courses 1
 * and 2. Both courses 1 and 2 should be taken after
 * you finished course 0. So one correct course order
 * is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 *
 * Note:
 * The input prerequisites is a graph represented by
 * a list of edges, not adjacency matrices. Read more
 * about how a graph is represented.
 *
 * Hints:
 * This problem is equivalent to finding the topological
 * order in a directed graph. If a cycle exists, no
 * topological ordering exists and therefore it will
 * be impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial
 * (21 minutes) on Coursera explaining the basic concepts
 * of Topological Sort.
 * Topological sort could also be done via BFS.
 */
public class CourseScheduleII {

    public static void main(String[] args) {
        int[][] pre = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int numCourses = 4;
        CourseScheduleII app = new CourseScheduleII();
        int[] order = app.findOrder(numCourses, pre);
        System.out.println(Arrays.toString(order));
    }

    /**
     * Note here we build the digraph with arrow toward the prerequisites
     * from the dependent course. So we don't need to reverse the topOrder
     * array in the end. If we build the digraph with the arrow the other
     * way round, we need to reverse the topOrder in the end.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        populateGraph(numCourses, prerequisites, graph);
        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];

        List<Integer> topOrder = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            if (!visited[i]) {
                if (!dfs(graph, visited, onStack, i, topOrder)) {
                    return new int[0];
                }
            }
        }
        int[] res = new int[topOrder.size()];
        for (int i = 0; i < topOrder.size(); ++i) {
            res[i] = topOrder.get(i);
        }
        return res;
    }

    private boolean dfs(HashMap<Integer, HashSet<Integer>> graph, boolean[] visited,
                        boolean[] onStack, int curCourse, List<Integer> topOrder) {
        if (onStack[curCourse]) return false;
        if (visited[curCourse]) return true;
        visited[curCourse] = true;
        onStack[curCourse] = true;
        for (int course : graph.get(curCourse)) {
            if (!dfs(graph, visited, onStack, course, topOrder)) return false;
        }
        onStack[curCourse] = false;
        topOrder.add(curCourse);
        return true;
    }

    private void populateGraph(int numCourses, int[][] prerequisites, HashMap<Integer, HashSet<Integer>> graph) {
        if (null == graph) return;
        for (int i = 0; i < numCourses; ++i) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
    }
}
