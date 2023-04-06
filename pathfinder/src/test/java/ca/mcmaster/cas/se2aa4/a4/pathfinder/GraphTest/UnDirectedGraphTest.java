package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphTest;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnDirectedGraphTest {
    static UndirectedGraph graph;

    @BeforeAll
    public static void setUp(){

        Node A=new Node("100",4.0);
        Node n0=new Node("0",4.0);
        Node n2=new Node("2",4.0);
        Node n7=new Node("6",4.0);

        Edge e1=new Edge(A, n0, 9.0);
        Edge e3=new Edge(A, n2, 5.0);
        Edge e4=new Edge(n2, n7, 7.0);
        Edge e5=new Edge(n7, n0, 12.0);

        graph=new UndirectedGraph(new HashSet<>(Arrays.asList(A,n0,n2,n7)),new HashSet<>(Arrays.asList(e1,e3,e4,e5)));

    }

    @Test
    public void updateNodeTest(){
        Node n3=new Node("3", 4.0);
        Node n4=new Node("4",4.0);

        graph.addEdge(new Edge(n3,n4,5.0));

        //In undirected, both n3 and n4 should be updated with their associated edges.
        // Internally, the undirected graph adds (n3,n4) and (n4,n3) as edges.

        int n3_expected_size=1;
        int n3_actual_size=n3.getEdges().size();

        int n4_expected_size=1;
        int n4_actual_size=n4.getEdges().size();

        assertTrue(n3_expected_size==n3_actual_size & n4_expected_size==n4_actual_size);
    }

    @Test
    public void addNodeTest(){
        Node n3=new Node("3",  4.0);

        graph.addNode(n3);

        int expected_size=5;
        int actual_size=graph.getNodes().size();

        assertTrue(expected_size==actual_size);
    }

    @Test
    public void duplicateNode(){
        Node B=new Node("100",  4.0);

        graph.addNode(B);

        int expected_size=4;
        int actual_size=graph.getNodes().size();

        assertTrue(expected_size==actual_size);
    }

    @Test
    public void duplicateFlippedEdge(){
        Node B=new Node("100",4.0);
        Node C=new Node("0",4.0);
        //Since we previously added (A,0) to the edges, adding (0,A) should do nothing since (0,A) would automatically already be in the set.
        Edge e=new Edge(C, B, 9.0);

        int expected_result=graph.getEdges().size();
        int n1=graph.getNodes().size();

        graph.addEdge(e);

        int actual_result=graph.getEdges().size();
        int n2=graph.getNodes().size();


        assertTrue(expected_result==actual_result & n1==n2);
    }

}
