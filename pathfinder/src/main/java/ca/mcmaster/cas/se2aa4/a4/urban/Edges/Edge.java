package ca.mcmaster.cas.se2aa4.a4.urban.Edges;

import ca.mcmaster.cas.se2aa4.a4.urban.Node;

public class Edge {

    private Node node_1;

    private Node node_2;

    private Double weight;

    public Edge(Node n1, Node n2, Double weight){
        this.node_1=n1;
        this.node_2=n2;
        this.weight=weight;
    }

}
