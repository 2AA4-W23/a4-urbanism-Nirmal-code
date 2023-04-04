package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.SegmentObserver;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.VertexObserver;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CityMesh {

    UndirectedGraph graph;

    Set<IslandNode> nodes;
    Set<IslandEdge> edges;

    public CityMesh(List<TileVertex> vertices, List<TileSegment> segments){
        nodes=setGraphNodes(vertices);
        edges=setGraphEdges(segments);
        //Cross reference Nodes to IslandNodes by simply doing nodes.find(Node node)
        graph=new UndirectedGraph(getNodes(),getEdges());
    }

    private Set<IslandNode> setGraphNodes(List<TileVertex> vertices){
        Set<IslandNode> all_nodes=new HashSet<>();
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

    private Set<IslandEdge> setGraphEdges(List<TileSegment> segments){
        Set<IslandEdge> all_segments=new HashSet<>();

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
            all_segments.add(new_edge);
        }
        return all_segments;
    }

    public Set<Node> getNodes(){
        Set<Node> all_nodes=new HashSet<>();
        for (IslandNode i: nodes){
            all_nodes.add(i.getNode());
        }
        return all_nodes;
    }

    public Set<Edge> getEdges(){
        Set<Edge> all_edges=new HashSet<>();
        for (IslandEdge i: edges){
            all_edges.add(i.getEdge());
        }
        return all_edges;
    }
}
