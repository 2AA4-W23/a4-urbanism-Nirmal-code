package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;


import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.*;

public class IslandNode extends GraphElement{
    Node node;
    VertexElement terrain_type;

    public IslandNode(String name, Double elevation, VertexElement type){
        node=new Node(name, elevation);
        this.terrain_type=type;
    }

    public VertexElement getTerrain(){
        return this.terrain_type;
    }


}
