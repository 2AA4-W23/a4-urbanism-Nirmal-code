package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;

import java.util.*;

public class CityMesh {


    GraphGenerator generator;
    CityGenerator city_gen;

    UndirectedGraph graph;

    //List representation is easier to access a particular island node during city generation. Doesn't use set.
    List<IslandNode> nodes;
    Map<Edge, IslandEdge> edges;

    List<IslandNode> cities;

    IslandNode capitol;

    int number_cities;

    public CityMesh(int num_cities, List<TileVertex> vertices, List<TileSegment> segments){
        generator=new GraphGenerator();
        nodes=generator.setGraphNodes(vertices);
        edges=generator.setGraphEdges(segments);
        cities=new ArrayList<>();
        //Cross reference Nodes to IslandNodes by simply doing nodes.find(Node node)
        graph=new UndirectedGraph(getNodes(),getEdges());
        number_cities=num_cities;
        setCities();
    }

    private Set<Node> getNodes(){
        Set<Node> all_nodes=new HashSet<>();
        for (IslandNode i: nodes){
            all_nodes.add(i.getNode());
        }
        return all_nodes;
    }

    private Set<Edge> getEdges(){
        Set<Edge> all_edges=new HashSet<>();
        for (IslandEdge i: edges.values()){
            all_edges.add(i.getEdge());
        }
        return all_edges;
    }

    public void setPaths(){
        DijkstraSP sp=new DijkstraSP();
        List<Edge> path;

        for (IslandNode n: cities){
            sp.generate(graph, capitol.getNode(), n.getNode());
            path=sp.getPath();

            for (int i=0; i<path.size(); i++){
                Edge edge=path.get(i);

                IslandEdge edge2=edges.get(edge);

                edge2.setTerrain(SegmentElement.ROAD);
                edge2.alert();
            }

        }

    }

    private void setCities(){
        city_gen=new CityGenerator();

        city_gen.generate(graph,nodes, number_cities);

        cities=city_gen.getCities();
        capitol=city_gen.getCapitol();
    }

}
