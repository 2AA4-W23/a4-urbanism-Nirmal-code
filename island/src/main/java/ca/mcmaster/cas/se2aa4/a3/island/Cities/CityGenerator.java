package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.UndirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath.DijkstraSP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CityGenerator {

    Random rand=new Random();

    IslandNode capitol;
    List<IslandNode> cities;
    List<IslandNode> all_nodes;

    UndirectedGraph graph;


    public void generate(UndirectedGraph graph, List<IslandNode> nodes, int num_small, int num_big){
        this.cities=new ArrayList<>();
        this.graph=graph;
        this.all_nodes=nodes;
        setCities(VertexElement.SMALL_CITY, num_small);
        setCities(VertexElement.BIG_CITY, num_big);
        setCapitol();
    }

    public List<IslandNode> getCities(){
        return this.cities;
    }

    public IslandNode getCapitol(){
        return this.capitol;
    }

    private void setCities(VertexElement type, int num_elements){
        int subject;
        IslandNode node;
        for (int i=0; i<num_elements;){
            subject=rand.nextInt(0,all_nodes.size());
            node=all_nodes.get(subject);
            if (node.getTerrain().equals(VertexElement.LAND)){
                node.setTerrain(type);
                node.getNode().setWeight(type.getWeight());
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

    private void setCapitol(){
        this.capitol=findCapitol();
        cities.remove(capitol);
        capitol.setTerrain(VertexElement.CAPITOL);
        capitol.alert();
    }



}
