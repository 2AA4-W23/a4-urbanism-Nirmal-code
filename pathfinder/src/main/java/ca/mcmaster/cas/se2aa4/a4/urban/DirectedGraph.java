package ca.mcmaster.cas.se2aa4.a4.urban;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

public class DirectedGraph extends GraphADT{

    public void addEdge(Edge edge) {
        //Bidirectional edge
        this.edges.add(edge);
        updateNode(edge.getN1(), edge);
    }

    private void updateNode(Node N, Edge e) {
        addNode(N);
        N.addEdge(e);
    }


}
