package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.GraphADT;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;

import java.util.List;
import java.util.Map;

public class PathPaver {


    public void generate(List<IslandNode> cities, GraphADT graph, IslandNode capitol, Map<Edge, IslandEdge> edges){
        DijkstraSP sp=new DijkstraSP();
        List<Edge> path;

        for (IslandNode n: cities){
            sp.generate(graph, capitol.getNode(), n.getNode());
            path=sp.getPath();

            for (int i=0; i<path.size(); i++){
                Edge road=path.get(i);

                IslandEdge island_road=edges.get(road);

                island_road.setTerrain(SegmentElement.ROAD);
                island_road.alert();
            }

        }

    }
}
