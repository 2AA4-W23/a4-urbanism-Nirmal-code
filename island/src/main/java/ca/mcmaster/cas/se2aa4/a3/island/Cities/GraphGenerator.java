package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.SegmentObserver;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.VertexObserver;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;

import java.util.*;

public class GraphGenerator {

    private List<IslandNode> all_nodes;
    private Map<Edge,IslandEdge> all_edges;

    private UndirectedGraph graph;

    public GraphGenerator(){
        all_nodes=new ArrayList<>();
        all_edges=new HashMap<>();
    }

    public void generateGraph(List<TileVertex> vertices, List<TileSegment> segments){
        setGraphNodes(vertices);
        setGraphEdges(segments);
        graph=new UndirectedGraph(getNodes(), getEdges());
    }

    protected List<IslandNode> getIslandNodes(){
        return this.all_nodes;
    }

    protected Map<Edge, IslandEdge> getIslandEdges(){
        return this.all_edges;
    }

    protected UndirectedGraph getGraph(){
        return this.graph;
    }


    private void setGraphNodes(List<TileVertex> vertices){
        int index=0;
        double total_weight;
        VertexElement element;

        for (TileVertex v: vertices){
            element=v.getVertexElement();
            total_weight=v.getElevation()/100+element.getWeight();
            IslandNode new_node=new IslandNode(Integer.toString(index),total_weight,v.getVertexElement());

            VertexObserver new_observer=new VertexObserver(new_node);
            new_node.attatch(new_observer);
            v.setObserver(new_observer);

            all_nodes.add(new_node);
            index+=1;
        }
    }

    private void setGraphEdges(List<TileSegment> segments){

        for (TileSegment s: segments){
            TileVertex v1=s.getTileVertex1();
            TileVertex v2=s.getTileVertex2();

            VertexObserver o1=v1.getObserver();
            VertexObserver o2=v2.getObserver();

            IslandEdge new_edge=new IslandEdge(o1.getNode(),o2.getNode(),s.getLength(),s.getElement());

            SegmentObserver new_observer=new SegmentObserver(new_edge);
            new_edge.attatch(new_observer);
            s.setObserver(new_observer);

            all_edges.put(new_edge.getEdge(),new_edge);
            all_edges.put(new_edge.getEdge2(),new_edge);

        }
    }

    private Set<Node> getNodes(){
        Set<Node> nodes=new HashSet<>();
        for (IslandNode i: all_nodes){
            nodes.add(i.getNode());
        }
        return nodes;

    }
    private Set<Edge> getEdges(){
        Set<Edge> edges=new HashSet<>();
        for (IslandEdge i: all_edges.values()){
            edges.add(i.getEdge());
        }
        return edges;
    }

}
