import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class DWGraph_Algo_Test {

    DWGraph_DS graph = new DWGraph_DS();
    dw_graph_algorithms gAlgo = new DWGraph_Algo();


    @Test
    void shortestPathDist() {
        //  DWGraph_DS g1 = new DWGraph_DS();
        directed_weighted_graph g1 = graphCreator(0, 9);
        gAlgo.init(g1);

        g1.connect(0, 1, 4);
        g1.connect(0, 1, 4);
        g1.connect(0, 2, 5);
        g1.connect(1, 3, 5);
        g1.connect(3, 1, 5);
        assertEquals(4, gAlgo.shortestPathDist(0, 1));
        assertEquals(9, gAlgo.shortestPathDist(0, 3));
        assertEquals(0, gAlgo.shortestPathDist(1, 1));


    }
    @Test
    void isConnected() {

        directed_weighted_graph g1 = graphCreator(0, 4);
        gAlgo.init(g1);

        g1.connect(0, 1, 4);
        g1.connect(1, 2, 2);
        g1.connect(2, 3, 8);
        g1.connect(3, 4, 5);
        g1.connect(4, 0, 5);

        assertEquals(true, gAlgo.isConnected());

        g1.removeEdge(4,0);
        assertEquals(false, gAlgo.isConnected());

        g1.connect(4, 0, 5);
        assertEquals(true, gAlgo.isConnected());

        g1.removeNode(4);
        assertEquals(false, gAlgo.isConnected());

    }

    @Test
    void shortestPath() {
        DWGraph_DS g1 = new DWGraph_DS();
        List<node_data> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            node_data newNode = new NodeData(i);
            g1.addNode(newNode);
            list.add(newNode);
        }
        gAlgo.init(g1);
        g1.connect(0, 1, 4);
        assertEquals(list, gAlgo.shortestPath(0, 1));


    }

    @Test
    void copy() {
        dw_graph_algorithms g_algo = new DWGraph_Algo();
        directed_weighted_graph baseGraph = new DWGraph_DS();
        directed_weighted_graph copyOfWeightedGraph = new DWGraph_DS();
        for (int i = 0; i < 10; i++) {
            baseGraph.addNode(new NodeData(i));
        }
        baseGraph.connect(0, 8, 1.1);
        baseGraph.connect(0, 2, 1);
        baseGraph.connect(2, 5, 0.3);
        baseGraph.connect(5, 4, 0.3);
        baseGraph.connect(4, 7, 0.3);
        baseGraph.connect(0, 3, 0);
        baseGraph.connect(3, 7, 0);
        baseGraph.connect(3, 6, 1);
        baseGraph.connect(0, 1, 0.5);
        baseGraph.connect(5, 3, 0.1);
        baseGraph.connect(7, 8, 1);
        g_algo.init(baseGraph);
        copyOfWeightedGraph = g_algo.copy();
        Assertions.assertTrue(copyOfWeightedGraph.equals(baseGraph));

    }


    @Test
    void init() {
        directed_weighted_graph g = graphCreator(1, 4);
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.init(null);
        assertNull(ga.getGraph());
        ga.init(g);
        assertEquals(g, ga.getGraph());
    }


    // adds nodes to the graph with id's 'a' to 'b' included
    public directed_weighted_graph graphCreator(int a, int b) {
        directed_weighted_graph g = new DWGraph_DS();
        for (; a <= b; a++) {
            node_data NewNode = new NodeData(a);
            g.addNode(NewNode);
        }
        return g;
    }


    @Test
    void saveAndLoad() {
        directed_weighted_graph g = graphCreator(0, 3);
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.init(g);
        ga.save("graph.json");
        dw_graph_algorithms ha = new DWGraph_Algo();
        ha.load("graph.json");
        assertNotNull(ha.getGraph());

    }
}