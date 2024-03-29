package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;


import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.*;

import java.util.Objects;

public class IslandNode extends GraphElement{
    Node node;
    VertexElement terrain_type;

    public IslandNode(String name, Double elevation, VertexElement type){
        node=new Node(name, elevation);
        this.terrain_type=type;
    }

    public void setTerrain(VertexElement new_terrain){
        node.addWeight(-1*terrain_type.getWeight());
        this.terrain_type=new_terrain;
        node.addWeight(terrain_type.getWeight());
    }

    public Node getNode(){
        return this.node;
    }

    public VertexElement getTerrain(){
        return this.terrain_type;
    }

//    @Override
//    public boolean equals(Object other)
//    {
//        return this.node.equals(((IslandNode)other).node);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(this.node.getName());
//    }


}
