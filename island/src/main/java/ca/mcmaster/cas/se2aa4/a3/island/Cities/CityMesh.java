package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.SegmentObserver;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.VertexObserver;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;

import java.util.*;

public class CityMesh {

    Random rand=new Random();

    int num_small=10;
    int num_big=5;

    UndirectedGraph graph;

    //List representation is easier to access a particular island node during city generation. Doesn't use set.
    List<IslandNode> nodes;
    List<IslandEdge> edges;

    List<IslandNode> cities;

    IslandNode capitol;

    public CityMesh(List<TileVertex> vertices, List<TileSegment> segments){
        nodes=setGraphNodes(vertices);
        edges=setGraphEdges(segments);
        cities=new ArrayList<>();
        //Cross reference Nodes to IslandNodes by simply doing nodes.find(Node node)
        graph=new UndirectedGraph(getNodes(),getEdges());
    }

    private List<IslandNode> setGraphNodes(List<TileVertex> vertices){
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

    private List<IslandEdge> setGraphEdges(List<TileSegment> segments){
        List<IslandEdge> all_segments=new ArrayList<>();

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

    private Set<Node> getNodes(){
        Set<Node> all_nodes=new HashSet<>();
        for (IslandNode i: nodes){
            all_nodes.add(i.getNode());
        }
        return all_nodes;
    }

    private Set<Edge> getEdges(){
        Set<Edge> all_edges=new HashSet<>();
        for (IslandEdge i: edges){
            all_edges.add(i.getEdge());
        }
        return all_edges;
    }

    public void setSmallCity(){
        int subject;
        IslandNode node;
        for (int i=0; i<num_small;){
            subject=rand.nextInt(0,nodes.size());
            node=nodes.get(subject);
            if (node.getTerrain().equals(VertexElement.LAND)){
                node.setTerrain(VertexElement.SMALL_CITY);
                node.alert();
                cities.add(node);
                i++;
            }
        }

    }


    public void setBigCity(){
        int subject;
        IslandNode node;
        for (int i=0; i<num_big;){
            subject=rand.nextInt(0,nodes.size());
            node=nodes.get(subject);
            if (node.getTerrain().equals(VertexElement.LAND)){
                node.setTerrain(VertexElement.BIG_CITY);
                node.alert();
                cities.add(node);
                i++;
            }
        }
    }

    private IslandNode findCapitol(){
        DijkstraSP sp=new DijkstraSP();

        IslandNode capitol=cities.get(0);
        double smallest_cost=Double.MAX_VALUE;

        for (int i=0; i<cities.size(); i++){
            double max_cost=0;
            for (int j=0; j<cities.size(); j++){
                if (i!=j){
                    sp.generate(graph, cities.get(i).getNode(), cities.get(j).getNode());
                    if (sp.getCost()>max_cost){
                        max_cost=sp.getCost();
                    }
                }
            }
            if (max_cost<smallest_cost){
                smallest_cost=max_cost;
                capitol=cities.get(i);
            }
        }
        return capitol;
    }


    public void setCapitol(){
        this.capitol=findCapitol();
        cities.remove(capitol);
        capitol.setTerrain(VertexElement.CAPITOL);
        capitol.alert();
    }

    public void setPaths(){
        DijkstraSP sp=new DijkstraSP();
        List<Node> path;

        for (IslandNode n: cities){
            sp.generate(graph, capitol.getNode(), n.getNode());
            path=sp.getPath();

            for (int i=0; i<path.size()-1; i++){
                IslandNode n1=findNode(path.get(i));
                IslandNode n2=findNode(path.get(i+1));

                if (i!=0){
                    n1.setTerrain(VertexElement.ROAD);
                    n1.alert();
                }

                if ((i+1)<path.size()-1){
                    n2.setTerrain(VertexElement.ROAD);
                    n2.alert();
                }


                IslandEdge edge=findEdge(n1.getNode(),n2.getNode());
                edge.setTerrain(SegmentElement.ROAD);

                edge.alert();

            }

        }
    }

    private IslandNode findNode(Node n){
        for (IslandNode i: nodes){
            if (i.getNode().equals(n)){
                return i;
            }
        }
        return null;
    }

    private IslandEdge findEdge(Node n1, Node n2){
        for (IslandEdge e: edges){
            Edge edge=e.getEdge();
            if (edge.getN1().equals(n1) & edge.getN2().equals(n2) | edge.getN1().equals(n2) & edge.getN2().equals(n1)){
                return e;
            }
        }
        return null;
    }

}
