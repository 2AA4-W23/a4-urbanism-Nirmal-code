package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

import java.util.*;

public class Node{

    private String name;
    private Set<Edge> connections;

    private int size;

    private double weight;


    public Node(String name, int size, double elevation){
        this.connections =new HashSet<>();
        this.name =name;
        this.size=size;
        this.weight =elevation;
    }

    public void addEdge(Edge new_edge){
        this.connections.add(new_edge);
    }

    public Set<Edge> getEdges(){
        return this.connections;
    }

    public String getName(){
        return this.name;
    }

    public double getWeight(){
        return this.weight;
    }

    //Overide java's implementation of equals.
    //Ensures that when adding to set, two nodes that are equal won't be added.
    @Override
    public boolean equals(Object other)
    {
        return this.name.equals(((Node)other).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }


}
