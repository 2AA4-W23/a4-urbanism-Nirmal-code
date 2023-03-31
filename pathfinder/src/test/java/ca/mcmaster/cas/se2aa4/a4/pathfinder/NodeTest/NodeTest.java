package ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeTest;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class NodeTest {

    static Node N1;
    static Node N2;

    @BeforeAll
    public static void setUp(){
        N1=new Node("Toronto");
        N2=new Node("Vancouver");
    }

    @Test
    public void TestNodeName(){
        assertTrue(N1.getCity_name().equals("Toronto"));
    }

    @Test
    public void TestAddRoad(){
        Edge e=new Edge(N1,N2,5.0);
        Edge e2=new Edge(N2,N1,5.0);

        N1.addEdge(e);
        N1.addEdge(e2);

        assertTrue(N1.getEdges().size()==2);

    }


}
