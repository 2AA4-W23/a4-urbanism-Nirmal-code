package ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT;

import java.util.List;

public interface ShortestPath{

    public void generate(GraphADT graph, Node s, Node e);

    public List<String> getPath();

    public Double getCost();


}
