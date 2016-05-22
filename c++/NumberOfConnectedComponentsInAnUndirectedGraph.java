import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 5/22/16.
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to find the number of
 * connected components in an undirected graph.
 *
 *
 * Example 1:
 *
 * 0          3
 *
 * |          |
 *
 * 1 --- 2    4
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 * Example 2:
 *
 * 0           4
 *
 * |           |
 *
 * 1 --- 2 --- 3
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 *
 * Note:
 *
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0]
 * and thus will not appear together in edges.
 */

public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public static void main(String[] args) {
        int[][] edges = {{2, 1}, {0, 1}, {3, 2}, {5, 4}, {6, 1}};
        int n = 7;
        NumberOfConnectedComponentsInAnUndirectedGraph app = new NumberOfConnectedComponentsInAnUndirectedGraph();
        int count = app.countComponents2(n, edges);
        System.out.println("There are " + count + " connected components in the graph");
    }

    /**
     * A space efficient solution.
     * Build a link table, represented by an array root[n], where
     * root[i] presents the node i's root. In the beginning, root[i] is
     * initialized to i, assuming all nodes are disconnected. When traverse
     * the edge list, for each edge, we find the root for its two end nodes.
     * In this process, if root[i] != i, we know that the node i is connected
     * with another node and we traverse the link by setting i = root[i] until
     * root[i] = i. Then, we can connect one the end points with the root of another
     * one and know they belong to the same connect component.
     * @param n
     * @param edges
     * @return
     */
    public int countComponents2(int n, int[][] edges) {
        int count = n;
        int[] root = new int[n];
        for (int i = 0; i < n; ++i) root[i] = i;
        for (int[] edge : edges) {
            int x = find(root, edge[0]);
            int y = find(root, edge[1]);
            if (x != y) {
                --count;
                root[y] = x;
            }
        }
        return count;
    }

    private int find(int[] root, int i) {
        while (root[i] != i) i = root[i];
        return i;
    }

    public int countComponents(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            visited[i] = false;
        }
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        buildGraph(edges, graph);

        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                bfs(i, graph, visited);
                ++count;
            }
        }
        return count;
    }

    private void buildGraph(int[][] edges, HashMap<Integer, HashSet<Integer>> graph) {
        if (graph == null) {
            graph = new HashMap<>();
        }

        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new HashSet<>());
            }
            graph.get(edge[0]).add(edge[1]);

            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new HashSet<>());
            }
            graph.get(edge[1]).add(edge[0]);
        }
    }

    private void bfs(int node, HashMap<Integer, HashSet<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int curNode = queue.peek();
            queue.poll();
            visited[curNode] = true;
            for (Integer neighbor : graph.get(curNode)) {
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                }
            }
        }
    }
}

