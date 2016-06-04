import java.util.*;

/**
 * Created on 5/31/16.
 * There is a new alien language which uses the latin alphabet. However,
 * the order among letters are unknown to you. You receive a list of words
 * from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 *
 * For example,
 * Given the following words in dictionary,
 *
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 *
 *
 * The correct order is: "wertf".
 *
 * Note:
 *
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 *
 */

public class AlienDictionary {

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt", "acbrt"};
        AlienDictionary app = new AlienDictionary();
        String order = app.alienOrder(words);
        System.out.println("DFS result: " + order);
    }

    /**
     * Given the word list, we can build a dependency list by comparing
     * words[i] with words[i+1]. Such dependency list forms a digraph.
     * We need to apply topological sort to the digraph to get the correct
     * order. If there is a loop in the digraph, we return an empty string.
     * There are two ways to do topological sort: DFS and BFS.
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        ArrayList<ArrayList<Character>> dependencyList = new ArrayList<>();
        buildDependencyList(words, dependencyList);
        HashSet<Character> characters = new HashSet<>();
        buildCharacterSet(words, characters);
        return alienOrderDFS(characters, dependencyList);
    }

    /**
     * Apply DFS to the digraph to get the topological sort result.
     * @param characters
     * @param dependencyList
     * @return
     */
    private String alienOrderDFS(HashSet<Character> characters, ArrayList<ArrayList<Character>> dependencyList) {
        boolean[] visited = new boolean[26];
        boolean[] onStack = new boolean[26];

        for (int i = 0; i < 26; ++i) {
            visited[i] = false;
            onStack[i] = false;
        }

        HashMap<Character, ArrayList<Character>> digraph = new HashMap<>();
        buildDigraph(dependencyList, digraph);

        StringBuilder res = new StringBuilder();
        for (char c : characters) {
            if (!visited[c-'a']) {
                if (!dfs(c, digraph, visited, onStack, res)) return "";
            }
        }

        StringBuilder str = new StringBuilder(res);
        return str.reverse().toString();
    }

    boolean dfs(char c, HashMap<Character, ArrayList<Character>> digraph,
                boolean[] visited, boolean[] onStack, StringBuilder res) {
        int index = (int)(c-'a');
        if (onStack[index]) return false;
        visited[index] = true;
        if (!digraph.containsKey(c)) {
            res.append(c);
            return true;
        }
        onStack[index] = true;
        for (char neighbor : digraph.get(c)) {
            if (visited[neighbor-'a']) continue;
            if (!dfs(neighbor, digraph, visited, onStack, res)) return false;
        }
        onStack[index] = false;
        res.append(c);
        return true;
    }

    private void buildDigraph(ArrayList<ArrayList<Character>> dependencyList,
                              HashMap<Character, ArrayList<Character>> digraph) {
        if (null == dependencyList) return;
        if (null == digraph) digraph = new HashMap<>();
        for (ArrayList<Character> pair : dependencyList) {
            if (!digraph.containsKey(pair.get(0))) {
                digraph.put(pair.get(0), new ArrayList<>());
            }
            digraph.get(pair.get(0)).add(pair.get(1));
        }
    }

    private void buildCharacterSet(String[] words, HashSet<Character> characterSet) {
        if (characterSet == null) {
            characterSet = new HashSet<>();
        }
        for (String word : words) {
            for (int i = 0; i < word.length(); ++i) {
                characterSet.add(word.charAt(i));
            }
        }
    }

    private void buildDependencyList(String[] words, ArrayList<ArrayList<Character>> dependencyList) {
        if (dependencyList == null) {
            dependencyList = new ArrayList<>();
        }
        for (int i = 0; i < words.length-1; ++i) {
            int j = 0;
            while (j < words[i].length() && j < words[i+1].length() && words[i].charAt(j) == words[i+1].charAt(j)) ++j;
            if (j < words[i].length() && j < words[i+1].length()) {
                ArrayList<Character> pair = new ArrayList<>();
                pair.add(words[i].charAt(j));
                pair.add(words[i+1].charAt(j));
                dependencyList.add(pair);
            }
        }
    }
}
