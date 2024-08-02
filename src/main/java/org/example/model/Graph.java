package org.example.model;

import org.example.exceptions.InvalidInputFormatException;
import org.example.utils.ReadInput;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Graph {

    private final Map<String, List<Node>> nodes;

    public Graph(String inputFilePath) {
        this.nodes = new HashMap<>();

        this.loadGraph(inputFilePath);
    }


    public void printNodesMap() {
        for (Map.Entry<String, List<Node>> entry : nodes.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            for (Node node : entry.getValue()) {
                System.out.println("  Node: " + node.toString()); // Assuming Node has a toString() method
            }
        }
    }

    // Loads the graph from the provided inputFilePath
    private void loadGraph(String inputFilePath) {
        try {
            List<String> rawData = ReadInput.readInputFile(inputFilePath);
            rawData.forEach(this::splitRawData);

        } catch (InvalidInputFormatException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Splits a rawDataLine, read from the input file
    private void splitRawData(String rawDataLine) {
        String[] splitRawData = rawDataLine.split("");

        if(splitRawData.length == 1) {
            addNewNode(splitRawData[0]);
        } else {
            String[] weightValues = Arrays.copyOfRange(splitRawData, 2, splitRawData.length);
            addNewNode(
                splitRawData[0],
                splitRawData[1],
                Integer.parseInt(String.join("", weightValues))
            );
        }
    }

    // Adds a node into the graph without any child node
    public void addNewNode(String nodeName) {
        if(!nodes.containsKey(nodeName))
            nodes.put(nodeName, new ArrayList<>());
    }

    // Adds a node into the graph that contains a child node and also a weight for that connection
    public void addNewNode(String nodeName, String destinationNodeName, int weight) {
        if(!nodes.containsKey(nodeName))
            nodes.put(nodeName, new ArrayList<>());

        Node newNode = new Node(destinationNodeName, weight);
        nodes.get(nodeName).add(newNode);
    }


    // --------------------------------------------------------------------------------
    // Find the distance of a route if the route is valid

    /*
        the exercise requires that we return a String since it asks that if the route doesn't
        exist we have to return the value "NO SUCH ROUTE", so inside of this method we have
        to convert the output of the helper method in to the corresponding string
    */
    public String calculateRouteDistance(String[] route) {
        int distance = calculateRouteDistanceHelper(route, 0, 0);
        return distance == -1 ? "NO SUCH ROUTE" : String.valueOf(distance);
    }

    /*
        Recursive method that finds the distance between a provided path if the path is valid
        Time Complexity => O(R * N)
        Where:
            - R : Number of nodes inside the route
            - N : Number of neighbour nodes inside each node of the route
    */
    private int calculateRouteDistanceHelper(String[] route, int currentNodeIndex, int totalDistance) {
        if (currentNodeIndex >= route.length - 1)
            return totalDistance;

        String currentNode = route[currentNodeIndex], nextNode = route[currentNodeIndex + 1];

        if (!nodes.containsKey(currentNode))
            return -1;

        for (Node neighbour : nodes.get(currentNode)) {
            if (nextNode.equals(neighbour.name()))
                return calculateRouteDistanceHelper(
                    route,
                    currentNodeIndex + 1,
                    totalDistance + neighbour.weight()
                );
        }

        return -1;
    }


    // --------------------------------------------------------------------------------
    // Find the number of trips starting from node A to B with maximum X stops

    Map<String, Map<Integer, Integer>> memoA = new HashMap<>();

    public int countTripsWithMaxStops(String start, String end, int maxStops) {
        return countTripsWithMaxStopsHelper(start, end, maxStops, 0);
    }

    /*
        Recursive method that counts the quantity of trips with less than the provided number of stops
        Time Complexity => O(V * E * M)
        Where:
            - V : Number of nodes in the graph
            - E : Number of edges in the graph
            - M : Number of maximum stops
     */
    private int countTripsWithMaxStopsHelper(String currentNode, String endNode, int exactStops, int currentStops) {
        // If the currentStops are bigger than the maxStops, then return 0
        if(currentStops > exactStops)
            return 0;

        // If the "memory" HashMap, contains the current node and inside the current node
        // we have previously stored the "currentStops" quantity, we can return the previously
        // memorized result as the "count" value, and we don't have the necessity of processing again
        // the result
        if(memoA.containsKey(currentNode) && memoA.get(currentNode).containsKey(currentStops))
            return memoA.get(currentNode).get(currentStops);

        int count = 0;

        // In case that the current stops are bigger or equal to 1, and the current node is equal
        // to the end node, then we can add one to count since we have founded a valid path to the end node
        // with exactly the number of stops provided, this avoids the case where we count the starting
        // node as a valid path since at least we need to make 1 stop for being able to count the trip as valid
        if(currentStops >= 1 && currentNode.equals(endNode))
            count++;

        // Then we loop over each one of the possible paths from the actual node and make a recursive call
        // to the "countTripsWithExactStopsHelper" method providing the new node data and add the result
        // value to the current "count" variable that represents the number of valid trips
        for (Node neighbour: nodes.get(currentNode)) {
            count += countTripsWithMaxStopsHelper(
                neighbour.name(),
                endNode,
                exactStops,
                currentStops + 1
            );
        }

        // We store the value if is not previously stored into the "memory" HashMap for
        // being able to use it if the case is repeated
        memoA.computeIfAbsent(currentNode, k -> new HashMap<>()).put(currentStops, count);

        return count;
    }


    // --------------------------------------------------------------------------------
    // Find the number of trips starting from node A to B with exactly X stops

    Map<String, Map<Integer, Integer>> memoB = new HashMap<>();

    public int countTripsWithExactStops(String start, String end, int exactStops) {
        return countTripsWithExactStopsHelper(start, end, exactStops, 0);
    }

    /*
        Recursive method that counts the quantity of trips with exactly the provided number of stops
        Time Complexity => O(V * E * M)
        Where:
            - V : Number of nodes in the graph
            - E : Number of edges in the graph
            - M : Number of maximum stops
     */
    private int countTripsWithExactStopsHelper(String currentNode, String endNode, int exactStops, int currentStops) {
        // If the currentStops are bigger than the maxStops, then return 0
        if(currentStops > exactStops)
            return 0;

        // If the "memory" HashMap, contains the current node and inside the current node
        // we have previously stored the "currentStops" quantity, we can return the previously
        // memorized result as the "count" value, and we don't have the necessity of processing again
        // the result
        if(memoB.containsKey(currentNode) && memoB.get(currentNode).containsKey(currentStops))
            return memoB.get(currentNode).get(currentStops);

        int count = 0;

        // In case that the current stops are or equal to the maxStops, and the current node is equal
        // to the end node, then we can add one to count since we have founded a valid path to the end node
        // with exactly the number of stops provided
        if(currentStops == exactStops && currentNode.equals(endNode))
            count++;

        // Then we loop over each one of the possible paths from the actual node and make a recursive call
        // to the "countTripsWithExactStopsHelper" method providing the new node data and add the result
        // value to the current "count" variable that represents the number of valid trips
        for (Node neighbour: nodes.get(currentNode)) {
            count += countTripsWithExactStopsHelper(
                neighbour.name(),
                endNode,
                exactStops,
                currentStops + 1
            );
        }

        // We store the value if is not previously stored into the "memory" HashMap for
        // being able to use it if the case is repeated
        memoB.computeIfAbsent(currentNode, k -> new HashMap<>()).put(currentStops, count);

        return count;
    }


    // --------------------------------------------------------------------------------
    // Find the length of the shortest route ( in terms of distance travel )

    /*
        Simple implementation of the Dijkstra's algorithm for finding the closest path between a start node
        and the rest of nodes in the graph
        Time complexity => O(V log V)
        Where:
            - V = Number of vertices in the graph
    */
    public int findShortestPath(String startNode, String endNode) {
        // Insert the "startNode" into the distances HashMap with a starting weight of zero
        Map<String, Integer> distances = new HashMap<>();
        distances.put(startNode, Integer.MAX_VALUE);

        // Insert the "startNode" into the priority queue with a starting value of zero
        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::weight));
        priorityQueue.add(new Node(startNode, 0));

        // While the priority queue is not empty we are going to continue looping
        while(!priorityQueue.isEmpty()) {
            // Extract the node on the head of the priority queue and retrieve the current node distance
            Node currentNode= priorityQueue.poll();

            // If currentNodeDistance is equal to Integer.MAX_VALUE, we need to set the current distance to zero.
            // This is because the initial distance for the startNode stored in the HashMap is Integer.MAX_VALUE.
            // As the algorithm runs, it will update this distance with a lower value.
            // This adjustment covers edge cases, such as going from node X to the same node X, ensuring the
            // algorithm always returns zero for these cases.
            int currentNodeDistance = distances.get(currentNode.name());
            int currentDistance = currentNodeDistance == Integer.MAX_VALUE ? 0 : currentNodeDistance;

            // We iterate over all the children of the "currentNode"
            for (Node neighbour: nodes.get(currentNode.name())) {
                // We calculate the new distance between the currentDistance and the selected neighbour
                int newDistance = currentDistance + neighbour.weight();

                // We try to retrieve the distance value of the selected neighbour, if is not possible, we retrieve the
                // Integer.MAX_VALUE, and we compare the retrieved value with the new calculated distance,
                // in case that the newDistance is lower, we can store it as new or replace the older one,
                // and we can also insert the node into the priority queue
                if(newDistance < distances.getOrDefault(neighbour.name(), Integer.MAX_VALUE)) {
                    distances.put(neighbour.name(), newDistance);
                    priorityQueue.offer(new Node(neighbour.name(), newDistance));
                }
            }
        }

        // Finally we can return the value stored inside the "distances" HashMap with the key referent to
        // the "endNode" or if is not stored we can return -1
        return distances.getOrDefault(endNode, -1);
    }

    // --------------------------------------------------------------------------------
    // Find the number of different routes from A to C with a distance of less than X

    public int countRoutesWithMaxDistance(String startNode, String endNode, int maxDistance) {
        return countRoutesWithMaxDistanceHelper(startNode, endNode, maxDistance, 0);
    }

    /*
        Recursive method that counts all the routes inside a maximum distance
        Time Complexity in the worst case scenario => O(V * E * M)
        Where:
            - V : Number of nodes in the graph
            - E : Number of neighbours in the node
            - M : Maximum distance allowed
    * */
    private int countRoutesWithMaxDistanceHelper(String start, String end, int maxDistance, int currentDistance) {
        // If the current distance is bigger or equal to the max distance we return zero
        if(currentDistance >= maxDistance)
            return 0;

        int count = 0;
        // If the currentDistance is bigger than one and the start node is equal to the end node
        // then we can add one to the actual count since we have found a successful path, we make the
        // validation (currentDistance >= 1) for the edge cases where we have to start in node X and go to node X
        // if we don't perform this validation is going to count X as a successful route
        if(currentDistance >= 1 && start.equals(end))
            count++;

        // We can loop all the node neighbours and then perform a recursive call to the "countRoutesWithMaxDistanceHelper"
        // method providing the selected neighbour data and updating the current distance value with the neighbour distance
        for (Node neighbour: nodes.get(start)) {
            count += countRoutesWithMaxDistanceHelper(
                neighbour.name(),
                end,
                maxDistance,
                currentDistance + neighbour.weight()
            );
        }

        return count;
    }

}
