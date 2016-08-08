import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 8/7/16.
 * For a undirected graph with tree characteristics, we can choose any node as the root.
 * The result graph is then a rooted tree. Among all possible rooted trees, those with minimum
 * height are called minimum height trees (MHTs). Given such a graph, write a function to find
 * all the MHTs and return a list of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n
 * and a list of undirected edges (each edge is a pair of labels).
 *
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Example 1:
 *
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 * 0
 * |
 * 1
 * / \
 * 2   3
 * return [1]
 *
 * Example 2:
 *
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 * 0  1  2
 * \ | /
 * 3
 * |
 * 4
 * |
 * 5
 * return [3, 4]
 *
 * Hint:
 *
 * How many MHTs can a graph have at most?
 * Note:
 *
 * (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any
 * two vertices are connected by exactly one path. In other words, any connected graph without simple
 * cycles is a tree.”
 *
 * (2) The height of a rooted tree is the number of edges on the longest downward path between the
 * root and a leaf.
 */

public class MinimumHeightTrees {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        MinimumHeightTrees app = new MinimumHeightTrees();
        System.out.println(app.findMinHeightTrees(n, edges).toString());
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n]; // in degree of each node
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            ++degree[edge[0]];
            graph.get(edge[0]).add(edge[1]);
            ++degree[edge[1]];
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> leafQueue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1) leafQueue.offer(i);
        }

        while (n > 2) {
            int sz = leafQueue.size();
            for (int i = 0; i < sz; ++i) {
                --n;
                int curNode = leafQueue.poll();
                --degree[curNode];
                List<Integer> neighbors = graph.get(curNode);
                for (Integer neighbor : neighbors) {
                    if (degree[neighbor] > 0 && --degree[neighbor] == 1) {
                        leafQueue.offer(neighbor);
                    }
                }
            }
        }

        while (!leafQueue.isEmpty()) {
            res.add(leafQueue.poll());
        }
        return res;
    }
}
