package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.Objects;


public class IslandEdge extends GraphElement{
    Edge edge;
    SegmentElement terrain_type;

    public IslandEdge(IslandNode n1, IslandNode n2, double weight, SegmentElement element){
        edge=new Edge(n1.getNode(),n2.getNode(),weight);
        this.terrain_type=element;
    }

    public SegmentElement getTerrain(){
        return this.terrain_type;
    }

    public void setTerrain(SegmentElement element){
        this.terrain_type=element;
    }

    public Edge getEdge(){
        return this.edge;
    }

//    @Override
//    public boolean equals(Object obj){
//        IslandEdge comparator=(IslandEdge)obj;
//        return comparator.edge.equals(this.edge);
//    }
//
//    @Override
//    public int hashCode(){
//        return Objects.hash(this.edge);
//    }
}
