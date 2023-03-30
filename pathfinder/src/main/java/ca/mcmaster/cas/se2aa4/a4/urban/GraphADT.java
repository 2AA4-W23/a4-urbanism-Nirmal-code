package ca.mcmaster.cas.se2aa4.a4.urban;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphADT {

    Set<Edge> edges;
    Set<Node> nodes;

    public GraphADT(){
        this.edges=new HashSet<>();
        this.nodes=new HashSet<>();
    }

    public void addEdge(Edge edge){
        //Bidirectional edge
        this.edges.add(edge);
        this.edges.add(edge.flipEdge());
        edge.getN1().addEdge(edge);
        edge.getN2().addEdge(edge.flipEdge());
    }

    public void addNode(Node N){
        if (!nodes.contains(N)) {
            nodes.add(N);
        }
    }

    public Set<Node> getNodes(){
        return this.nodes;
    }

}
