package ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks;

import java.util.*;

public class Node{

    private String city_name;
    private Set<Edge> roads;

    private int size;

    private double elevation;



    public Node(String name, int size, double elevation){
        this.roads =new HashSet<>();
        this.city_name =name;
        this.size=size;
        this.elevation=elevation;
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

    public double getElevation(){
        return this.elevation;
    }


}
