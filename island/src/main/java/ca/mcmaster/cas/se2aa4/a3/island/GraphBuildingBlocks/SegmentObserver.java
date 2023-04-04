package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;

public class SegmentObserver extends Observer{
    SegmentElement terrain_type;
    IslandEdge observee;

    public SegmentObserver(IslandEdge element){
        this.observee=element;
    }
    public void update(){
        this.terrain_type=observee.getTerrain();
    }

    public SegmentElement getTerrain(){
        return this.terrain_type;
    }
}
