package ca.mcmaster.cas.se2aa4.a3.island.TilesTypes;

import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.LandComposition;

import java.awt.*;

public enum VertexElement {
    WATER(null,1.0),
    LAND(null, 1.0),
    SMALL_CITY(new Color(255, 231, 2,254),2.0),
    BIG_CITY(new Color(255, 23, 2,254),4.0),
    CAPITOL(new Color(116, 2, 255,254),7.0),
    ROAD(new Color(0, 0, 0, 255),2.0);

    public final Color vertexColor;
    public final Double vertexSize;
    private VertexElement(Color color, Double size){
        vertexColor = color;
        vertexSize=size;
    }

    public Color getColor(){
        return this.vertexColor;
    }

    public Double getSize(){
        return this.vertexSize;
    }


}
