import api.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DWGraph_DS_Test {


// Checks adding and removing nodes, connecting and size of edges.
    @Test
    void edgeSizeTest(){
        directed_weighted_graph g = new DWGraph_DS();
        int size=g.edgeSize();
        assertEquals(0,size);

        for (int i = 1; i < 5; i++) {
            node_data NewNode = new NodeData(i);
            g.addNode(NewNode);
        }

        g.connect(1,2,1.2);
        g.connect(1,2,1.2);
        size=g.edgeSize();
        assertEquals(1,size);

        g.removeEdge(1,2);
        size=g.edgeSize();
        assertEquals(0,size);

        g.connect(1,2,1.2);
        g.removeNode(1);
        size=g.edgeSize();
        size=0;
        assertEquals(0,size);
    }


    @Test
    void getV() {
        directed_weighted_graph g =  graphCreator(0, 3);

        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.connect(0,1,1);
        Collection<node_data> v = g.getV();
        Iterator<node_data> iter = v.iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            assertNotNull(n);
        }
    }

    @Test
    void removeNode() {
        directed_weighted_graph g =  graphCreator(0, 3);

        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.removeNode(0);

        int e = g.edgeSize();
        assertEquals(0,e);
        assertEquals(3,g.nodeSize());
    }

    @Test
    void edge(){
        directed_weighted_graph g =  graphCreator(0, 1);
        g.connect(0,1,20.2);
        edge_data edgeForTest= g.getEdge(0,1);
        int a = edgeForTest.getSrc();
        int b = edgeForTest.getDest();
        node_data node1 = g.getNode(a);
        node_data node2 = g.getNode(b);

        node_data arrayTest[] = new node_data[2];
        Collection<node_data> v = g.getV();
        Iterator<node_data> iter = v.iterator();
        int i = 0;
        while (iter.hasNext()) {

            node_data n = iter.next();
            arrayTest[i] = n;
            i++;


        }
        assertEquals(arrayTest[0],node1);
        assertEquals(arrayTest[1],node2);



    }





    // adds nodes to the graph with id's 'a' to 'b' included
    public directed_weighted_graph graphCreator(int a, int b){
        directed_weighted_graph g = new DWGraph_DS();
        for (; a <= b; a++) {
            node_data NewNode = new NodeData(a);
            g.addNode(NewNode);
        }
        return g;
    }






}
