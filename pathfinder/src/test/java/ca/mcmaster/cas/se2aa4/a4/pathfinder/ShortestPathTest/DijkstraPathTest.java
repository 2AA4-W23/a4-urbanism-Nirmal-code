package ca.mcmaster.cas.se2aa4.a4.pathfinder.ShortestPathTest;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DijkstraPathTest {

    static UndirectedGraph undirected;


    @BeforeAll
    public static void setUp(){
        Node s=new Node("A",0.0);
        Node n0=new Node("0",0.0);
        Node n1=new Node("1",0.0);
        Node n2=new Node("2",0.0);
        Node n3=new Node("3",0.0);
        Node n4=new Node("4",0.0);
        Node n5=new Node("5",0.0);
        Node n6=new Node("6",0.0);
        Node n7=new Node("6",0.0);
        Node n8=new Node("8",0.0);
        Node e=new Node("9",0.0);

        Edge e1=new Edge(s, n0, 9.0);
        Edge e2=new Edge(s, n8, 1.0);
        Edge e3=new Edge(s, n2, 5.0);
        Edge e4=new Edge(n2, n7, 7.0);
        Edge e5=new Edge(n7, n0, 12.0);
        Edge e7=new Edge(n0, n4, 13.0);
        Edge e8=new Edge(n0, e, 11.0);
        Edge e9=new Edge(e, n5, 8.0);
        Edge e10=new Edge(e, n6, 4.0);
        Edge e11=new Edge(n6, n5, 3.0);
        Edge e12=new Edge(n5, n1, 2.0);
        Edge e13=new Edge(n5, n8, 7.0);
        Edge e14=new Edge(n8, n1, 4.0);

        undirected=new UndirectedGraph(new HashSet<>(Arrays.asList(s,n0,n1,n2,n3,n4,n5,n6,n7,n8,e)), new HashSet<>(Arrays.asList(e1,e2,e3,e4,e5,e7,e8,e9,e10,e11,e12,e13,e14)));

    }

    @Test
    public void UnDirectedTest(){
        DijkstraSP sp=new DijkstraSP();

        Node s=new Node("A", 4.0);
        Node e=new Node("9",4.0);
        sp.generate(undirected,s,e);

        List<Edge> actual_path=sp.getPath();

        List<Node> path=new ArrayList<>();

        for (Edge edge: actual_path){
            if (!path.contains(edge.getN1())){
                path.add(edge.getN1());
            }
            if (!path.contains(edge.getN2())){
                path.add(edge.getN2());
            }
        }

        Node A=new Node("A",5.0);
        Node n8=new Node("8",5.0);
        Node n1=new Node("1",5.0);
        Node n5=new Node("5",5.0);
        Node n6=new Node("6",5.0);
        Node n9=new Node("9",5.0);

        List<Node> expected_result=new ArrayList<>(Arrays.asList(A,n8,n1,n5,n6,n9));

        boolean check=true;

        for (int i=0; i<=actual_path.size(); i++){
            if (!path.get(i).equals(expected_result.get(i))){
                check=false;
            }
        }
        assertTrue(check);
    }


}
