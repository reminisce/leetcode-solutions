/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest
 * lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller
 * lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */

import java.util.*;

public class ItineraryReconstructor {
    /**
     * Start from JFK and do DFS, once the arriving terminals are empty
     * for a departing terminal in the travel graph, retract back and put
     * that departing terminal in the itinerary.
     * @param tickets
     * @return
     */
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, ArrayList<String>> travelGraph = buildTravelGraph(tickets);

        String startTerminal = "JFK";

        ArrayList<String> itinerary = new ArrayList<>(); // the final itinerary
        ArrayList<String> terminals = new ArrayList<>(); // the terminals we are visiting
        terminals.add("JFK");
        while (!terminals.isEmpty()) {
            String departingTerminal = terminals.get(terminals.size()-1);
            if (travelGraph.containsKey(departingTerminal) && !travelGraph.get(departingTerminal).isEmpty()) { // DFS
                ArrayList<String> arrivingTerminals = travelGraph.get(departingTerminal);
                terminals.add(arrivingTerminals.get(0));
                arrivingTerminals.remove(0);
            } else { // retract
                itinerary.add(departingTerminal);
                terminals.remove(terminals.size()-1);
            }
        }

        Collections.reverse(itinerary);
        return itinerary;
    }

    /**
     * build a travel graph with key as departing terminal and value as
     * a alphabetically sorted list of arriving terminals.
     * @param tickets
     * @return
     */
    private HashMap<String, ArrayList<String>> buildTravelGraph(String[][] tickets) {
        if (tickets == null || tickets.length == 0 || tickets[0].length != 2) {
            return null;
        }

        HashMap<String, ArrayList<String>> travelGraph = new HashMap<>();

        for (String[] ticket : tickets) {
            if (travelGraph.containsKey(ticket[0])) {
                travelGraph.get(ticket[0]).add(ticket[1]);
            } else {
                ArrayList<String> arrivingTerminals = new ArrayList<>();
                arrivingTerminals.add(ticket[1]);
                travelGraph.put(ticket[0], arrivingTerminals);
            }
        }

        for (ArrayList<String> arrivalTerminals : travelGraph.values()) {
            Collections.sort(arrivalTerminals);
        }

        return travelGraph;
    }
}
