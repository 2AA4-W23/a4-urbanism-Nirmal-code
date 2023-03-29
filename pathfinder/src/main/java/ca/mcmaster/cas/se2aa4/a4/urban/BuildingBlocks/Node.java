package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String name;
    private List<Edge> edgeList;


    public Node(String name){
        this.edgeList=new ArrayList<>();
        this.name=name;
    }

    public void addEdge(Edge new_edge){
        this.edgeList.add(new_edge);
    }


}
