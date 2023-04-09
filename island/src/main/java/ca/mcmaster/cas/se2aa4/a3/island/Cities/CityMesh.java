package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;

import java.util.*;

public class CityMesh {

    final GraphGenerator generator=new GraphGenerator();
    final CityGenerator city_gen=new CityGenerator();
    final PathPaver paver=new PathPaver();

    private UndirectedGraph layout;
    //List representation is easier to access a particular island node during city generation. Doesn't use set.
    private List<IslandNode> future_cities;
    private Map<Edge, IslandEdge> future_roads;
    private List<IslandNode> cities;
    private IslandNode capitol;

    public CityMesh(){
        this.future_cities =new ArrayList<>();
        this.future_roads =new HashMap<>();
        this.cities=new ArrayList<>();
    }

    public void generate(int num_cities, List<TileVertex> vertices, List<TileSegment> segments){

        generator.generateGraph(vertices,segments);
        future_cities =generator.getIslandNodes();
        future_roads =generator.getIslandEdges();
        layout =generator.getGraph();

        city_gen.generate(layout, future_cities, num_cities);
        cities=city_gen.getCities();
        capitol=city_gen.getCapitol();

        paver.generate(cities, layout, capitol, future_roads);
    }

}
