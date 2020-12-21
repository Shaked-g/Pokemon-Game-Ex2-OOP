package api;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {

    private int mc_count;
    private int edge_count;

    private HashMap<Integer, node_data> nodeMap;
    private HashMap<Integer, HashMap<node_data, edge_data>> edgesMap;

    /**
     * Constructor for WGraph_DS class initializing it with
     * nodeMap for <node index,node> pairing
-     * neighbourMap for <node index,<neighbour index,neighbour node>>
     * edgesMap for <node index,<neighbour node_data,connection Edge>>
     */
    public DWGraph_DS() {
        nodeMap = new HashMap<Integer, node_data>() ;
        edgesMap = new  HashMap<Integer, HashMap<node_data, edge_data>> ();

        this.mc_count = 0;
        this.edge_count = 0;
    }


    /**
     * returns the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if (nodeMap.containsKey(key)) {
            return nodeMap.get(key);
        }
        return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        return edgesMap.get(src).get(nodeMap.get(dest));
    }


    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        if (!nodeMap.containsKey(n.getKey())){
            nodeMap.put(n.getKey(),n);
       //     neighbourMap.put(n.getKey(),new HashMap<Integer, node_data>());
            edgesMap.put(n.getKey(), new HashMap<node_data, edge_data>());
            mc_count++;
        }
    }


    /**
     * Connects an edge with weight w between node src to node dest.
     * Note: this method runs in O(1) time.
     * checks to see if the nodes exists in the graph and that the weight is positive.
     * also checks if the edge already exists.
     * creates a new Edge between the nodes, and adds the edge to edgesMap.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (w>=0 && nodeMap.containsKey(src) && nodeMap.containsKey(dest) && !hasEdge(src,dest) ){
            edge_data NewEdge = new Edge(src, dest, w);
            edgesMap.get(src).put(getNode(dest),NewEdge);

            mc_count++;
            edge_count++;

        }
    }


    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return nodeMap.values();
    }


    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * Note: this method should run in O(k) time, k being the collection size.
     * @return Collection<edge_data>
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        return edgesMap.get(node_id).values();
    }



    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method runs in O(k), V.degree=k, as all the edges should be removed.
     * go over all the neighbours and check if they have the Node as Edge
     * if its had edge, sends it to the function "removeEdge".
     * @return the data of the removed node (null if none).
     * @param key
     */
    @Override
    public node_data removeNode(int key) {
        if (nodeMap.containsKey(key)) {

            // deletes the edge if given key is the Dest node
            for (int srcLookout : nodeMap.keySet()) {
                if (edgesMap.get(srcLookout).containsKey(getNode(key))) {
                    removeEdge(srcLookout, key);
                }
            }

            // deletes the neighbors of the key node when he is the Src node

            for (node_data DestNodeToDelete : new HashSet<node_data>(edgesMap.get(key).keySet())) {
               int dest = DestNodeToDelete.getKey();
                removeEdge(key, dest);
            }

            edgesMap.remove(key);
            return nodeMap.remove(key);
        }
        else return null;
    }


    /**
     * Deletes the edge from the graph,
     * removes the edge from the Hashmap of neighbours of src node in edgeMap
     * Note: this method runs in O(1) time.
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    @Override
    public edge_data removeEdge(int src, int dest) {

        if (edgesMap.containsKey(src) && edgesMap.get(src).containsKey(getNode(dest))){
        edge_count--;
        mc_count++;
        return edgesMap.get(src).remove(nodeMap.get(dest));
        }
        else return null;
    }


    /** Returns the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */
    @Override
    public int nodeSize() {
        return nodeMap.size();
    }


    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method runs in O(1) time.
     * @return
     */
    @Override
    public int edgeSize() {
        return this.edge_count;
    }


    /**
     * Returns the Mode Count - for testing changes in the graph.
     * @return
     */
    @Override
    public int getMC() {
        return this.mc_count;
    }


    /**
     * Function that checks if the edge already exists in the graph.
     * @return true- if edge already exists, false if not.
     */
    public boolean hasEdge (int src, int dest){
      
        return edgesMap.get(src).containsKey(getNode(dest));
    }
}
