package ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT;

import java.util.*;

public class DijkstraSP implements ShortestPath {

    HashMap<Node, Node> path;
    HashMap<Node, Double> cost;
    PriorityQueue<Node> queue;
    List<Edge> path_edge;
    Node start;
    Node end;

    Comparator<Node> new_comparator = new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return (int)(cost.get(n1)-cost.get(n2));
        }
    };

    public DijkstraSP(){
        this.path=new HashMap<>();
        this.path_edge=new ArrayList<>();
        this.cost=new HashMap<>();
        this.queue=new PriorityQueue<>(new_comparator);
    }

    public void generate(GraphADT graph, Node s, Node e) {
        this.start=s;
        this.end=e;

        for (Node n: graph.getNodes()){
            path.put(n, null);
        }
        for (Node n: graph.getNodes()){
            cost.put(n, Double.MAX_VALUE);
        }

        path.put(s,s);
        cost.put(s,0.0);
        queue.add(s);

        while (!queue.isEmpty()){
            Node temp=queue.remove();
            for (Edge edge: temp.getEdges()){
                Node next=edge.getN2();
                if (cost.get(temp)+edge.getWeight()<cost.get(next)){
                    cost.put(next, cost.get(temp)+edge.getWeight());
                    path.put(next,temp);
                    queue.add(next);
                }
            }
        }
    }

    private void findPathEdges(){

        Node curr_node=this.end;
        while (curr_node!=this.start & curr_node!=null) {
            if (path.get(curr_node)!=null){
                for (Edge e : path.get(curr_node).getEdges()) {
                    if (e.getN2() == curr_node) {
                        this.path_edge.add(0,e);
                    }
                }
            }
            curr_node = path.get(curr_node);
        }


    }
    public List<String> getPath() {
        findPathEdges();
        List<String> temp=new ArrayList<>();
        for (Edge e: this.path_edge){
            temp.add(e.getN1().getName()+e.getN2().getName());
        }

        return temp;
    }

//    public List<String> getPath() {
//        List<String> temp=new ArrayList<>();
//        Node curr_node=this.end;
//        while (curr_node!=this.start & curr_node!=null){
//            temp.add(0,curr_node.getName());
//            curr_node=path.get(curr_node);
//        }
//        temp.add(0,start.getName());
//
//        return temp;
//    }

    public Double getCost(){
        return cost.get(end);
    }
}
