package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {
    private directed_weighted_graph graph;
    private LinkedList<node_data> queue;

    // HashMap that holds the information gathered by the Dijkstra algorithm for each node
    private HashMap<Integer, DijkstraNodeData> NodeMapForDijkstra;

    /**
     * default constructor for Graph_Algo that creates a new empty graph.
     */
    public DWGraph_Algo() {
        this.graph = new DWGraph_DS();
    }


    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(directed_weighted_graph g) {
        this.graph = g;
    }


    /**
     * Return the underlying graph of which this class works.
     * @return
     */
    @Override
    public directed_weighted_graph getGraph() {
        return graph;
    }


    /**
     * Compute a deep copy of this weighted graph.
     * @return
     */
    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph new_graph = new DWGraph_DS();

        for (node_data Node : this.graph.getV()) {
            new_graph.addNode(Node);

            new_graph.getNode(Node.getKey()).setInfo(Node.getInfo());
            new_graph.getNode(Node.getKey()).setTag(Node.getTag());
            new_graph.getNode(Node.getKey()).setWeight(Node.getWeight());
        }

        for (node_data v : this.graph.getV()) {
            for (edge_data edge : this.graph.getE(v.getKey())) {
                new_graph.connect(v.getKey(), edge.getDest(), edge.getWeight());
            }
        }
        return new_graph;
    }


    /**
     * Returns true if and only if (iff) there is a valid path from each node to each.
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * If this graph is null or contains no node - returns true.
     * if there is only one node - return true.
     * @return
     */
    @Override
    public boolean isConnected() {
        if (graph == null || graph.getV().size() <= 1) {
            return true;
        }
        for (node_data node : graph.getV()) {
            Dijkstra(node.getKey(), null);
            // If the amount of nodes in the graph is not equal to the amount in the HashMap, then the graph is not connected
            if (NodeMapForDijkstra.size() != graph.nodeSize()) {
                return false;
            }
        }
        return true;
    }



    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * If src and dest are equal -return 0
     * If the graph is null or src or dest are not in the graph - returns -1
     * Returns the sum of the weights from dest node which was set to the shortest distance from src
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {

        if (graph == null || graph.getNode(src) == null || graph.getNode(dest) == null){
            return -1;
        }
        if (src == dest) {
            return 0;
        }
        // Using the Dijkstra algorithm to find the shortest path according to the weight from src to dest
        Dijkstra(src,dest);
        // If the hashMap does not contain the dest key, then it did not reach the dest node, return -1
        if (!NodeMapForDijkstra.containsKey(dest)) {
            return -1;
        }
        return NodeMapForDijkstra.get(dest).getWeightSum();
    }



    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * If the distance of the shortest path Distance is equal to -1, then there is no path,
     * Note if no such path or if the graph is empty --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {

        if (shortestPathDist(src, dest) == -1 || graph.getV().isEmpty()) {
            return null;
        }
        // List of nodes of the shortest path
        List<node_data> list = new ArrayList<>();
        //  If src and dest are equal, then return the path with the one node
        if (src == dest) {
            list.add(graph.getNode(src));
            return list;
        }

        // Adds the dest node to the list
        node_data destNode = graph.getNode(dest);
        list.add(destNode);
        boolean finished = false;
        int nextNodeIndex = 0;
        // While loop that adds the shortest path from dest -> src
        while (!finished) {
            // Gets the next node_data from the list
            node_data node = list.get(nextNodeIndex);
            // Adds the parent to the list and increment the index
            list.add(NodeMapForDijkstra.get(node.getKey()).getParent());
            nextNodeIndex++;
            // If reached the src node, then we have all the nodes from dest to src and we can end the loop
            if(list.get(list.size() - 1) == graph.getNode(src)){
                finished = true;
            }
        }
        // Using the reverse function from collections so we can get the list to be from src to dest
        Collections.reverse(list);
        return list;
    }



    /**
     * Sets all the Weights of nodes in the given graph to Integer.MAX_VALUE
     * @return directed_weighted_graph
     */
 public directed_weighted_graph initializeWeights(directed_weighted_graph g) {
     for (node_data node:g.getV()) {
         node.setWeight(Integer.MAX_VALUE);
     }
        return g;
 }


    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {

        try {
            Gson gson =new Gson();
            List<edge_data> answerList=new ArrayList<edge_data>();

            FileWriter fWriter;
            fWriter = new FileWriter(file);
            fWriter.flush();

            for (node_data node : this.graph.getV()) {
                for (edge_data neighbour : this.graph.getE(node.getKey())) {

                    answerList.add(neighbour);
                } }

            gson.toJson(answerList,fWriter);
            fWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try
        {
            GsonBuilder builder=new GsonBuilder();
            builder.registerTypeAdapter(DWGraph_DS.class, new GraphJsonDeserializer());
            Gson gson=builder.create();
            FileReader reader=new FileReader(file);
            directed_weighted_graph g=gson.fromJson(reader, DWGraph_DS.class);
            init(g);
            return true;

        }
        catch (IOException  e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * This method implements the Dijkstra algorithm.
     * https://en.wikipedia.org/wiki/Dijkstras_algorithm
     */
    private void Dijkstra(Integer src, Integer dest) {
        NodeMapForDijkstra = new HashMap<>();
        Queue<node_data> queue = new PriorityQueue<>(new WeightComparator());
        // Adds the src node to the queue
        node_data nodeSrc = graph.getNode(src);
        queue.add(nodeSrc);
        // Creates a DijkstraNodeData instance that holds the src with weight zero and puts it in the map
        NodeMapForDijkstra.put(src, new DijkstraNodeData(0, null));

        // Keep Looping while queue is not empty
        while (!queue.isEmpty()) {
            // Gets the node with the lowest weight from the priority queue
            node_data node = queue.poll();
            // Loop over all the edges of this specific node
            for (edge_data edge : graph.getE(node.getKey())) {
                // Calculate the weight sum by adding to the current weight of the edge to the previous sum weight
                double sumWeight = edge.getWeight() + NodeMapForDijkstra.get(node.getKey()).getWeightSum();
                node_data neighbor = graph.getNode(edge.getDest());
                // Check if the neighbor does not exists in the map and the queue
                if (!NodeMapForDijkstra.containsKey(neighbor.getKey()) && !queue.contains(neighbor)) {
                    // Add the neighbor to the map and to the queue
                    NodeMapForDijkstra.put(edge.getDest(), new DijkstraNodeData(sumWeight, node));
                    queue.add(neighbor);
                }
                // If the neighbor already exists in the map and the weightSum is lower, then replace it in the map
                else if (sumWeight < NodeMapForDijkstra.get(edge.getDest()).getWeightSum()) {
                    NodeMapForDijkstra.put(edge.getDest(), new DijkstraNodeData(sumWeight, node));
                }
                // If the current node is equal to dest then return
                if (dest != null && node.getKey() == dest){
                    return;
                }
            }
        }
    }


    /**
     * This private class represents node information from the Dijkstra algorithm.
     */
    private class DijkstraNodeData {
        // The summed weight from src to this node
        private double weightSum;
        // The parent of this node from src
        private node_data parent;

        // Default Constructor
        public DijkstraNodeData(double sumWeight, node_data parent) {
            this.parent = parent;
            this.weightSum = sumWeight;
        }
        public node_data getParent() {
            return parent;
        }

        public double getWeightSum(){
            return weightSum;
        }

    }


    private class WeightComparator implements Comparator<node_data> {

        /**
         * Overrides compare method by tag (weight), if node1 is greater than node2 return 1,
         * if node1 is less than node2 return -1, else return 0.
         * used in the priorityQueue.
         * @param node1 - node1 to compare
         * @param node2 - node2 to compare
         * @return If node1 is greater than node2 return 1, if node1 is less than node2 return -1, else return 0.
         */
        @Override
        public int compare(node_data node1, node_data node2) {
            double sum1 = NodeMapForDijkstra.get(node1.getKey()).getWeightSum();
            double sum2 = NodeMapForDijkstra.get(node2.getKey()).getWeightSum();
            if (sum1 > sum2) {
                return 1;
            } else if (sum1 <sum2) {
                return -1;
            }
            return 0;
        }
    }






}
