package ca.mcmaster.cas.se2aa4.a3.island.TilesTypes;

import java.awt.*;

public enum SegmentElement {

    LAND(null),
    RIVER(new Color(15,94,196,254)),
    ROAD(new Color(0, 0, 0, 255));

    public final Color segmentColor;

    private SegmentElement(Color color){
        segmentColor = color;
    }

    public Color getColor(){
        return this.segmentColor;
    }
}
