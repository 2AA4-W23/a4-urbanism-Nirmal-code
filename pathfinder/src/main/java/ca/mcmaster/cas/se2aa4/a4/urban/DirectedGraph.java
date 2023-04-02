package ca.mcmaster.cas.se2aa4.a4.urban;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.Set;

public class DirectedGraph extends GraphADT{

    public DirectedGraph(Set<Node> nodes, Set<Edge> edges){
        this.nodes=nodes;
        for (Edge e: edges){
            addEdge(e);
        }
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
        updateNode(edge.getN1(), edge);
    }

    private void updateNode(Node N, Edge e) {
        addNode(N);
        N.addEdge(e);
    }


}
