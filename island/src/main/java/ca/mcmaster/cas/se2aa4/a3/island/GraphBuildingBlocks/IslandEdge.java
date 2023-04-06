package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;

import java.util.Objects;


public class IslandEdge extends GraphElement{
    Edge edge;
    Edge edge2;
    SegmentElement terrain_type;

    IslandNode n1;
    IslandNode n2;

    public IslandEdge(IslandNode n1, IslandNode n2, double weight, SegmentElement element){
        this.n1=n1;
        this.n2=n2;
        edge=new Edge(n1.getNode(),n2.getNode(),weight);
        edge2=edge.flipEdge();
        this.terrain_type=element;
    }

    public SegmentElement getTerrain(){
        return this.terrain_type;
    }

    public void setTerrain(SegmentElement element){
        this.terrain_type=element;
        if (element.equals(SegmentElement.ROAD)){
            if (n1.getTerrain().equals(VertexElement.LAND) | n1.getTerrain().equals(VertexElement.WATER)){
                n1.setTerrain(VertexElement.ROAD);
                n1.observer.update();
            }
            if (n2.getTerrain().equals(VertexElement.LAND) | n2.getTerrain().equals(VertexElement.WATER)){
                n2.setTerrain(VertexElement.ROAD);
                n2.observer.update();
            }
        }
    }

    public Edge getEdge(){
        return this.edge;
    }

    public Edge getEdge2() {
        return this.edge2;
    }


//    @Override
//    public boolean equals(Object obj){
//        IslandEdge comparator=(IslandEdge)obj;
//        return comparator.edge.equals(this.edge);
//    }

//    @Override
//    public int hashCode(){
//        return Objects.hash(this.n1,this.n2);
//    }
}
