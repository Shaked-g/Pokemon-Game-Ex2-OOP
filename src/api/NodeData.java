package api;

public class NodeData implements node_data{

    private int key;
    static int nextID = 0;
    private String info;
    private int tag;
    private double weight;
    private geo_location position ;

    /**
     * Default Constructor for NodeData.
     */
    public NodeData(){
        this.tag = 0 ;
        this.info = "";
        this.key = nextID++;
    }

    /**
     * Constructor for NodeInfo with specific key and weight.
     */
    public NodeData(int key){
        this.tag = 0;
        this.info = "";
        this.nextID = key;
        this.key = nextID++;


    }

    /**
     * Returns the key (id) associated with this node.
     * @return
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /** Returns the location of this node, if
     * none return null.
     *
     * @return
     */
    @Override
    public geo_location getLocation() {
        return this.position;
    }

    /** Allows changing this node's location.
     * @param p - new new location  (position) of this node.
     */
    @Override
    public void setLocation(geo_location p) {
        this.position = p;
    }

    /**
     * Returns the weight associated with this node.
     * @return
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Allows changing this node's weight.
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;

    }
}