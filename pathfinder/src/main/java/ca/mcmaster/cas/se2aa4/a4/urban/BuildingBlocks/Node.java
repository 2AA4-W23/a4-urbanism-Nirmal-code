package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;

import java.util.*;

public class Node{

    private String name;
    private Set<Edge> edgeList;


    public Node(String name){
        this.edgeList=new HashSet<>();
        this.name=name;
    }

    public void addEdge(Edge new_edge){
        this.edgeList.add(new_edge);
    }

    public Set<Edge> getEdges(){
        return this.edgeList;
    }

    public String getName(){
        return this.name;
    }


}
