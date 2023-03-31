package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

import java.util.*;

public class Node{

    private String city_name;
    private Set<Edge> roads;


    public Node(String name){
        this.roads =new HashSet<>();
        this.city_name =name;
    }

    public void addEdge(Edge new_edge){
        this.roads.add(new_edge);
    }

    public Set<Edge> getEdges(){
        return this.roads;
    }

    public String getCity_name(){
        return this.city_name;
    }


}
