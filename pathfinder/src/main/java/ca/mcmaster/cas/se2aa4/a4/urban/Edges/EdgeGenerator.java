package ca.mcmaster.cas.se2aa4.a4.urban.Edges;

import ca.mcmaster.cas.se2aa4.a4.urban.Node;

import java.util.ArrayList;
import java.util.List;

public class EdgeGenerator {

    List<Edge> all_edges;



    public EdgeGenerator(Node N1, Node N2){
        this.all_edges=new ArrayList<>();

    }

    public void generate(Node N1, Node N2){
        Double weight=calcWeight(N1,N2);
        Edge new_edge=new Edge(N1,N2,weight);

        all_edges.add(new_edge);
    }

    private Double calcWeight(Node N1, Node N2){
        Double x1=N1.getX();
        Double x2=N2.getX();
        Double y1=N1.getY();
        Double y2=N2.getY();

        Double weight=Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return weight;
    }

}
