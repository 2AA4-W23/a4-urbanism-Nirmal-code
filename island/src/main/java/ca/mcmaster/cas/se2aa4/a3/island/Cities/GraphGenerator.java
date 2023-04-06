package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.SegmentObserver;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.VertexObserver;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;

import java.util.*;

public class GraphGenerator {

    protected List<IslandNode> setGraphNodes(List<TileVertex> vertices){
        List<IslandNode> all_nodes=new ArrayList<>();
        int index=0;

        for (TileVertex v: vertices){
            IslandNode new_node=new IslandNode(Integer.toString(index),v.getElevation(),v.getVertexElement());

            VertexObserver new_observer=new VertexObserver(new_node);
            new_node.attatch(new_observer);
            v.setObserver(new_observer);

            all_nodes.add(new_node);
            index+=1;
        }
        return all_nodes;
    }

    protected Map<Edge,IslandEdge> setGraphEdges(List<TileSegment> segments){
        Map<Edge,IslandEdge> all_segments=new HashMap<>();

        for (TileSegment s: segments){
            TileVertex v1=s.getTileVertex1();
            TileVertex v2=s.getTileVertex2();

            VertexObserver o1=v1.getObserver();
            VertexObserver o2=v2.getObserver();

            IslandNode n1=o1.getNode();
            IslandNode n2=o2.getNode();

            IslandEdge new_edge=new IslandEdge(n1,n2,s.getLength(),s.getElement());

            SegmentObserver new_observer=new SegmentObserver(new_edge);
            new_edge.attatch(new_observer);
            s.setObserver(new_observer);

            all_segments.put(new_edge.getEdge(),new_edge);
            all_segments.put(new_edge.getEdge2(),new_edge);

        }
        return all_segments;
    }



}
