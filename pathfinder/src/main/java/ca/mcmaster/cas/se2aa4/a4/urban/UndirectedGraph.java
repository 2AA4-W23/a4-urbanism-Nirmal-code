package ca.mcmaster.cas.se2aa4.a4.urban;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.Set;

public class UndirectedGraph extends GraphADT{

    public UndirectedGraph(Set<Node> nodes, Set<Edge> edges){
        this.nodes=nodes;
        for (Edge e: edges){
            addEdge(e);
        }
    }

    public void addEdge(Edge edge) {
        //Bidirectional edge
        this.edges.add(edge);
        this.edges.add(edge.flipEdge());

        updateNode(edge.getN1(), edge.getN2(), edge);
    }

    private void updateNode(Node N1, Node N2, Edge e) {
        addNode(N1);
        addNode(N2);

        N1.addEdge(e);
        N2.addEdge(e.flipEdge());
    }

}
