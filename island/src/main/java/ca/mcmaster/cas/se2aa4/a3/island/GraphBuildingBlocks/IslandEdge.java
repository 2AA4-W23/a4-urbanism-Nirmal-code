package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;


public class IslandEdge extends GraphElement{
    Edge edge;
    SegmentElement terrain_type;

    public IslandEdge(Node n1, Node n2, double weight, SegmentElement element){
        edge=new Edge(n1,n2,weight);
        this.terrain_type=element;
    }

    public SegmentElement getTerrain(){
        return this.terrain_type;
    }
}
