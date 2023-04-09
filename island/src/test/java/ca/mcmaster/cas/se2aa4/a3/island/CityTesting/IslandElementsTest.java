package ca.mcmaster.cas.se2aa4.a3.island.CityTesting;

import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IslandElementsTest {

    @Test
    public void IslandNodeTest(){
        Node node=new Node("A",0.0);
        IslandNode i_node=new IslandNode("A",0.0, VertexElement.BIG_CITY);

        assertTrue(node.equals(i_node.getNode()));
    }

    @Test
    public void IslandEdgeTest(){
        Node node=new Node("A",0.0);
        IslandNode i_node=new IslandNode("A",0.0, VertexElement.BIG_CITY);
        Node node2=new Node("B",0.0);
        IslandNode i_node2=new IslandNode("B",0.0, VertexElement.SMALL_CITY);

        Edge edge=new Edge(node,node2,1.0);
        IslandEdge i_edge=new IslandEdge(i_node,i_node2,1.0, SegmentElement.ROAD);

        assertTrue(edge.equals(i_edge.getEdge()));
    }

}
