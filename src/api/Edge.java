package api;

public class Edge implements edge_data {
    private int srcNode;
    private int destNode;
    private int edgeTag;
    private String edgeInfo;
    private double edgeWeight;


    /**
     * Constructor for edge_data with specific source node, destination node and weight.
     */
    public Edge(int src, int dest, double w){
        this.srcNode = src;
        this.destNode = dest;
        this.edgeWeight = w;
    }

    /**
     * The id of the source node of this edge.
     * @return
     */
    @Override
    public int getSrc() {
        return this.srcNode;
    }


    /**
     * The id of the destination node of this edge
     * @return
     */
    @Override
    public int getDest() {
        return this.destNode;
    }


    /**
     * @return the weight of this edge (positive value).
     */
    @Override
    public double getWeight() {
        return this.edgeWeight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     * @return
     */
    @Override
    public String getInfo() {
        return this.edgeInfo;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.edgeInfo = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    @Override
    public int getTag() {
        return this.edgeTag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.edgeTag = t;

    }
}
