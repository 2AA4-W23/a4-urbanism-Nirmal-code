package ca.mcmaster.cas.se2aa4.a4.urban;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.ArrayList;
import java.util.List;

public class GraphADT {

    List<Edge> edges=new ArrayList<>();
    List<Node> nodes=new ArrayList<>();

    public void addEdge(Edge edge){
        //Bidirectional edge
        this.edges.add(edge);
        this.edges.add(edge.flipEdge());
    }

    public void addNode(Node N){
        if (!nodes.contains(N)) {
            nodes.add(N);
        }
    }

}
