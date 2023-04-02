package ca.mcmaster.cas.se2aa4.a4.urban;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.*;

public abstract class GraphADT {

    Set<Edge> edges;
    Set<Node> nodes;

    public GraphADT(){
        this.edges=new HashSet<>();
        this.nodes=new HashSet<>();
    }

    public abstract void addEdge(Edge edge);

    public Set<Edge> getEdges(){
        return this.edges;
    }


    public void addNode(Node N){
        nodes.add(N);
    }

    public Set<Node> getNodes(){
        return this.nodes;
    }

}
