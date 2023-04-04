package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;

public class VertexObserver extends Observer {

    VertexElement terrain_type;
    IslandNode observee;

    public VertexObserver(IslandNode element){
        this.observee=element;
    }
    public void update(){
        this.terrain_type=observee.getTerrain();
    }

    public VertexElement getTerrain(){
        return this.terrain_type;
    }
}
