package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

import java.util.Objects;

public class Edge {

    private Node N1;
    private Node N2;
    private Double weight;

    public Edge(Node n1, Node n2, Double weight){
        this.N1 =n1;
        this.N2 =n2;
        this.weight=accountElevation(weight);
    }

    public Edge flipEdge(){
        return new Edge(N2,N1,weight);
    }

    private double accountElevation(Double weight){
        double new_weight=Math.sqrt(Math.pow(weight,2)+Math.pow(Math.abs(N1.getElevation()-N2.getElevation()),2));
        return new_weight;
    };

    public Node getN1(){
        return this.N1;
    }

    public Node getN2(){
        return this.N2;
    }

    public Double getWeight(){
        return this.weight;
    }

    @Override
    public boolean equals(Object obj){
        Edge comparator=(Edge)obj;
        return comparator.getN1().equals(this.getN1()) & comparator.getN2().equals(this.getN2());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.N1,this.N2);
    }


}
