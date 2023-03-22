package ca.mcmaster.cas.se2aa4.a3.island.Terrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.util.ArrayList;
import java.util.List;

public class AquiferGenerator {
    private int numAquifers;
    List<Tile> tiles;
    List<Aquifer> aquifers;

    public AquiferGenerator(List<Tile> tiles, int numAquifers){
        this.tiles = tiles;
        this.numAquifers = numAquifers;
        this.aquifers = new ArrayList<>();
    }

    public void createAquifers(){
        Tile currentTile = null;
        Aquifer aquifer;

        for (int i = 0; i < numAquifers; i++){
            currentTile = tiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0,tiles.size()));
            aquifer = new Aquifer(currentTile);
            tiles.remove(currentTile);
            aquifers.add(aquifer);
        }

        for(Aquifer aquiferTemp: aquifers){
            Tile tile = aquiferTemp.getAquiferTile();
            tile.setTileType(TileTypes.AQUIFERS);
        }
    }

    public List<Aquifer> getAquifers(){return this.aquifers;}
}
