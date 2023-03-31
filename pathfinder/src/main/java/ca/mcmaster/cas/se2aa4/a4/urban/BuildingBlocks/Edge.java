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

    public Node getN1(){
        return this.N1;
    }

    public Node getN2(){
        return this.N2;
    }

    public Double getWeight(){
        return this.weight;
    }

}
