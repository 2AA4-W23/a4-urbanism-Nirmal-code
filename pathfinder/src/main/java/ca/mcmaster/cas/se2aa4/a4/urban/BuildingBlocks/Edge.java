package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

public class Edge {

    private Node N1;

    private Node N2;

    private Double weight;

    public Edge(Node n1, Node n2, Double weight){
        this.N1 =n1;
        this.N2 =n2;
        this.weight=weight;
    }

    public Edge flipEdge(){
        return new Edge(N2,N1,weight);
    }

}
