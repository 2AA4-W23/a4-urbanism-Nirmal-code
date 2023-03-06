package ca.mcmaster.cas.se2aa4.a3.island.Tiles;
import java.awt.Color;


public enum TileTypes {
    UNDETERMINEDLAND(new Color(252,228,162,254)),
    Ocean(new Color(0,0,153,254)),
    LAGOON(new Color(51,153,255, 254));



    public final Color tileColor;
    private TileTypes(Color color){
        tileColor = color;
    }
    public Color getColor(){
        return tileColor;
    }
}