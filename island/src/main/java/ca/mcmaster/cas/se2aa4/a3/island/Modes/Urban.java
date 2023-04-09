package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityMesh;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;

import java.io.IOException;

public class Urban extends Regular{

    public Urban(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxRivers, SoilTypes soil, int numAquifers, int num_cities) throws IOException {
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, maxLakes, maxRivers, soil, numAquifers);
        super.generate();

        CityMesh city=new CityMesh();
        city.generate(Math.min(num_cities, allLand.size()),centroidInfoList,neighbouringSegmentInfoList);
    }


}
