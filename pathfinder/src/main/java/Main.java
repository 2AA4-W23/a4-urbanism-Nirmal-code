import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;

public class Main {
    public static void main(String[] args) {


        //Will be converted to test cases.
        //Will get rid of DW RELAX
        GraphADT new_graph=new GraphADT();

        Node A=new Node("A");
        Node n0=new Node("0");
        Node n1=new Node("1");
        Node n2=new Node("2");
        Node n3=new Node("3");
        Node n4=new Node("4");
        Node n5=new Node("5");
        Node n6=new Node("6");
        Node n7=new Node("6");
        Node n8=new Node("8");
        Node n9=new Node("9");

        new_graph.addNode(A);
        new_graph.addNode(n0);
        new_graph.addNode(n1);
        new_graph.addNode(n2);
        new_graph.addNode(n3);
        new_graph.addNode(n4);
        new_graph.addNode(n5);
        new_graph.addNode(n6);
        new_graph.addNode(n7);
        new_graph.addNode(n8);
        new_graph.addNode(n9);





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


        new_graph.addEdge(e1);
        new_graph.addEdge(e2);
        new_graph.addEdge(e3);
        new_graph.addEdge(e4);
        new_graph.addEdge(e5);
        new_graph.addEdge(e7);
        new_graph.addEdge(e8);
        new_graph.addEdge(e9);
        new_graph.addEdge(e10);
        new_graph.addEdge(e11);
        new_graph.addEdge(e12);
        new_graph.addEdge(e13);
        new_graph.addEdge(e14);



        DijkstraSP shortest=new DijkstraSP();

        shortest.generate(new_graph, A,n9);

        System.out.println(shortest.getPath());
        System.out.println(shortest.getCost());


    }
}