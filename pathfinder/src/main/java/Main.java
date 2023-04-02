import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.DirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;
import ca.mcmaster.cas.se2aa4.a4.urban.UndirectedGraph;

import java.util.Arrays;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) {

        Node A=new Node("A",2,4.0);
        Node n0=new Node("0",2,4.0);
        Node n1=new Node("1",2,4.0);
        Node n2=new Node("2",2,4.0);
        Node n3=new Node("3",2,4.0);
        Node n4=new Node("4",2,4.0);
        Node n5=new Node("5",3,5.0);
        Node n6=new Node("6",2,4.0);
        Node n7=new Node("6",2,4.0);
        Node n8=new Node("8",2,5.0);
        Node n9=new Node("9",2,4.0);



        Edge e1=new Edge(A, n0, 9.0);
        Edge e2=new Edge(A, n8, 1.0);
        Edge e3=new Edge(A, n2, 5.0);
        Edge e4=new Edge(n2, n7, 7.0);
        Edge e5=new Edge(n7, n0, 12.0);
        Edge e7=new Edge(n0, n4, 13.0);
        Edge e8=new Edge(n0, n9, 11.0);
        Edge e9=new Edge(n9, n5, 8.0);
        Edge e10=new Edge(n9, n6, 4.0);
        Edge e11=new Edge(n6, n5, 3.0);
        Edge e12=new Edge(n5, n1, 2.0);
        Edge e13=new Edge(n5, n8, 7.0);
        Edge e14=new Edge(n8, n1, 4.0);


        UndirectedGraph new_graph=new UndirectedGraph(new HashSet<>(Arrays.asList(A,n0,n1,n2,n3,n4,n5,n6,n7,n8,n9)), new HashSet<>(Arrays.asList(e1,e2,e3,e4,e5,e7,e8,e9,e10,e11,e12,e13,e14)));


        DijkstraSP shortest=new DijkstraSP();

        shortest.generate(new_graph, A,n9);

        System.out.println(shortest.getPath());
        System.out.println(shortest.getCost());


    }
}