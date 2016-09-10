import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 5/22/16.
 * Given n nodes labeled from 0 to n - 1 and a list of undirected
 * edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 *
 * For example:
 *
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 *
 * Hint:
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
 * According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two
 * vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

public class GraphValidTree {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}; // invalid tree
        // int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 4}}; // valid tree
        GraphValidTree graphValidTree = new GraphValidTree();
        // boolean isValid = graphValidTree.validTree(n, edges);
        // System.out.println("BFS: the graph is" + (isValid? "" : " not") + " a valid tree");

        //boolean isValid = graphValidTree.validTree(n, edges);
        //System.out.println("DFS: the graph is" + (isValid? "" : " not") + " a valid tree");

        boolean isValid = graphValidTree.validTree(n, edges);
        System.out.println("DFS: the graph is" + (isValid? "" : " not") + " a valid tree");

        isValid = graphValidTree.unionFind(n, edges);
        System.out.println("UNION_FIND: the graph is" + (isValid? "" : " not") + " a valid tree");
    }

    /**
     * First check whether the number of edges is n-1. If not, it's not a tree for n nodes.
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        // Count how many nodes we visited in the end.
        // If less than n, the graph is not connected.
        int numVisitedNodes = 0;
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        buildGraph(edges, graph);
        return dfs(n, graph);
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

    /**
     * BFS the graph, mark the nodes visited as true. If visiting again,
     * it means there is a loop. To prevent the adjacent node from looking
     * back, need to erase the node traversing from.
     * @param graph
     * @return
     */
    private boolean bfs(int n, HashMap<Integer, HashSet<Integer>> graph) {
        if (graph == null) return true;
        HashSet<Integer> visitedNodes = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int curNode = queue.peek();
            queue.poll();
            visitedNodes.add(curNode);
            for (Integer neighbor : graph.get(curNode)) {
                if (visitedNodes.contains(neighbor)) return false;
                if (!graph.containsKey(neighbor)) return false;
                graph.get(neighbor).remove(curNode);
                queue.offer(neighbor);
            }
        }
        return visitedNodes.size() == n;
    }

    /**
     * DFS the graph, need a variable preNode to prevent the curNode from
     * traversing back.
     * @param n
     * @param graph
     * @return
     */
    private boolean dfs(int n, HashMap<Integer, HashSet<Integer>> graph) {
        if (graph == null) return true;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            visited[i] = false;
        }
        boolean ret = dfsHelper(graph, 0, -1, visited);
        for (boolean v : visited) {
            if (!v) return false;
        }

        return ret;
    }

    private boolean dfsHelper(HashMap<Integer, HashSet<Integer>> graph, int curNode, int preNode, boolean[] visited) {
        if (visited[curNode]) return false;
        visited[curNode] = true;
        for (Integer neighbor : graph.get(curNode)) {
            if (neighbor != preNode) {
                if (!dfsHelper(graph, neighbor, curNode, visited)) return false;
            }
        }
        return true;
    }

    /**
     * Initialize root array to -1 indicating the nodes have not eastablished the
     * link with its root node. Loop through the edge list, for each edge, find
     * the roots for its two neighboring nodes. If roots are different, we can
     * link them together, otherwise, there is a loop.
     * @param n
     * @param edges
     * @return
     */
    private boolean unionFind(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; ++i) {
            roots[i] = -1;
        }

        for (int[] edge : edges) {
            int x = find(roots, edge[0]);
            int y = find(roots, edge[1]);
            if (x == y) return false;
            roots[x] = y;
        }

        int count = 0;
        for (int i : roots) {
            if (i == -1) ++count;
        }

        return count == 1;
    }

    private int find(int[] roots, int i) {
        while (roots[i] != -1) i = roots[i];
        return i;
    }
}
